import { Component, OnInit } from '@angular/core';
import { Product, ProductPayload } from './product.class';
import {PRODUCT_TABLE_CONF} from './products-admin-table.conf';
import { ProductsService } from './products.service';
import {MessageService} from '../../app/shared/utils/serverErrorSahringService'
import { BehaviorSubject, Observable,Subject } from 'rxjs';
import {Router} from '@angular/router';
import { BaseTableLoader } from 'app/shared/ui/table/base-table-loader.class';

import { CrudItemOptions } from 'app/shared/utils/crud-item-options/crud-item-options.model';

@Component({
  selector: 'app-products-admin',
  templateUrl: './products-admin.component.html',
  styleUrls: ['./products-admin.component.scss']
})
export class ProductsAdminComponent extends BaseTableLoader implements OnInit {

  public payload$: BehaviorSubject<ProductPayload> = new BehaviorSubject<ProductPayload>({products:[],total:0});
  public conf: CrudItemOptions[] = PRODUCT_TABLE_CONF;
  public entity = Product;
  private subject = new Subject();
  constructor(
    private readonly productsService: ProductsService,
    private messageService:MessageService,private router:Router
  ) {
    super();
  }

  ngOnInit(): void {

    // Display data table
    this.productsService.getProducts().subscribe(products => 
    {
      
      this.payload$.next({products: products, total: products.length})
    });

  }

  public onDeleteProduct(ids: number[]): void {
    this.delete(ids[0]);
  }

  public onSave(product: Product): void {
    product.id ? this.update(product) : this.create(product);
  }

  private create(product: Product): void {
    this.messageService.clearMessages();
    this.productsService.create(product).subscribe({
      next:(next=>{
        this.messageService.sendMessage("close");
       ProductsService.productslist.push(next);
      }),
      error:(err=>{
        this.messageService.sendMessage(err.error.violations);
      })
    })
    
  }

  private update(product: Product): void {
    this.productsService.update(product)
    .subscribe({ 
      next:(next=>{
      this.messageService.sendMessage("close");
      let index=ProductsService.productslist.findIndex(x=>x.id===product.id);
      ProductsService.productslist[index]=product;
    }),
    error:(err=>{
      this.messageService.sendMessage(err.error.violations);
    })
  })
    ;
  }

  private delete(id: number): void {
    this.handleReload(this.productsService.delete(id));
  }
}
