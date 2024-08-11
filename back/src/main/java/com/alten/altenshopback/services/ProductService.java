package com.alten.altenshopback.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import com.alten.altenshopback.dto.ProductPatchDto;
import com.alten.altenshopback.dto.ProductRequestDto;
import com.alten.altenshopback.dto.ProductResponseDto;
import com.alten.altenshopback.exceptions.AltenShopNotFoundException;
import com.alten.altenshopback.mappers.ProductMapper;
import com.alten.altenshopback.models.Category;
import com.alten.altenshopback.models.InventoryStatus;
import com.alten.altenshopback.models.Product;
import com.alten.altenshopback.repositories.CategorieRepository;
import com.alten.altenshopback.repositories.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class ProductService {
	
	
	private final  ProductRepository productRepository;
	
	private final ProductMapper productMapper;
	
	private final CategorieRepository categorieRepository;
	
	public ProductService(ProductRepository productRepository, ProductMapper productMapper, CategorieRepository categorieRepository) {
		this.productRepository = productRepository;
		this.productMapper = productMapper;
		this.categorieRepository = categorieRepository;
	}
    
	
	public ProductResponseDto saveProduct(ProductRequestDto productDto){
		Product productEntity=productRepository.save(productMapper.requestDtoToEntity(productDto));
		return productMapper.entityToResponseDto(productEntity);
	}
	
	public List<ProductResponseDto> listAllProduct(){
		return productMapper.entityToResponseDto(productRepository.findAll());
	}
	
	public ProductResponseDto findProduct(Integer id){
	 Product productEntity=	productRepository.findById(id)
			 .orElseThrow(()->new AltenShopNotFoundException(String.format("No Product Found with Id %d", id)));
		return productMapper.entityToResponseDto(productEntity);
	}
	
	public ProductResponseDto patchProduct(Integer id,ProductPatchDto patchDto) {
		Product productEntity=productRepository.findById(id).orElseThrow(()->new AltenShopNotFoundException(String.format("No Product Found with Id %d", id)));
		String code = patchDto.getCode()!=null ? patchDto.getCode():productEntity.getCode();
		String name=patchDto.getName()!=null ? patchDto.getName():productEntity.getName();
		String description=patchDto.getDescription()!=null? patchDto.getName():productEntity.getDescription();
		Integer quantity=patchDto.getQuantity()!=null ? patchDto.getQuantity():productEntity.getQuantity();
		String inventoryStatus=patchDto.getInventoryStatus()!=null ? patchDto.getInventoryStatus():productEntity.getInventoryStatus().value;
		String categorie=patchDto.getCategory()!=null ? patchDto.getCategory(): productEntity.getCategory().getLibelle();
		Integer rating=patchDto.getRating()!=null? patchDto.getRating():productEntity.getRating();
		Double price=patchDto.getPrice()!=null ? patchDto.getPrice():productEntity.getPrice();
		String image=patchDto.getImage()!=null? patchDto.getImage():productEntity.getImage();
		Category category=categorieRepository.findByLibelle(categorie).orElseThrow(()->new AltenShopNotFoundException(String.format("No Category Found with libelle %s", categorie)));
		if(patchDto.getImage()!=null) {
			uploadImage();
		}
		Product updatedProductEntity=Product
				.builder()
				.code(code)
				.description(description)
				.id(productEntity.getId())
				.image(image)
				.inventoryStatus(InventoryStatus.valueOf(inventoryStatus))
				.price(price)
				.quantity(quantity)
				.rating(rating)
				.name(name)
				.category(category)
				.build();
		return productMapper.entityToResponseDto(productRepository.save(updatedProductEntity));
	}
	
	public void deleteProduct(Integer id) {
		Product productEntity=productRepository.findById(id).orElseThrow(()->new AltenShopNotFoundException(String.format("No Product Found with Id %d", id)));
	    productRepository.delete(productEntity);   
	}
	
	public void uploadImage() {
		
	}
}
