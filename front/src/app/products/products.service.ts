import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Product } from './product.class';
import { environment } from '../../environment/environment';
@Injectable({
  providedIn: 'root'
})
export class ProductsService {

    public static productslist: Product[] = null;
    private products$: BehaviorSubject<Product[]> = new BehaviorSubject<Product[]>([]);
    constructor(private http: HttpClient) { }

    getProducts(): Observable<Product[]> {
        if( ! ProductsService.productslist )
        {
            this.http.get<any>(environment.apiBaseUrl+environment.apiVersion+'/products').subscribe(data => {
                ProductsService.productslist = data;
                this.products$.next(ProductsService.productslist);
            });
        }
        else
        {
            this.products$.next(ProductsService.productslist);
        }

        return this.products$;
    }

    create(prod: Product): Observable<Product> {
       return  this.http.post<Product>(environment.apiBaseUrl+environment.apiVersion+'/products',prod);
    }

    update(prod: Product): Observable<Product>{
      return this.http.patch<Product>(environment.apiBaseUrl+environment.apiVersion+'/products/'+prod.id,prod);
    }


    delete(id: number): Observable<Product[]>{
        ProductsService.productslist = ProductsService.productslist.filter(value => { return value.id !== id } );
        this.products$.next(ProductsService.productslist);
        return this.products$;
    }
}