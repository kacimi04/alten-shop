package com.alten.altenshopback.mappers;

import java.util.List;
import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.springframework.beans.factory.annotation.Autowired;

import com.alten.altenshopback.dto.ProductRequestDto;
import com.alten.altenshopback.dto.ProductResponseDto;
import com.alten.altenshopback.exceptions.AltenShopNotFoundException;
import com.alten.altenshopback.models.Category;
import com.alten.altenshopback.models.Product;
import com.alten.altenshopback.repositories.CategorieRepository;

@Mapper(componentModel = ComponentModel.SPRING , uses = {CategorieMapper.class})
public abstract class  ProductMapper {
	
	@Autowired
	private  CategorieRepository categorieRepository;
	
	@Mapping(target = "category",expression = "java(libelleToCategorie(productDto.getCategory()))")
	@Mapping(target = "id" , ignore = true)
	public abstract Product requestDtoToEntity(ProductRequestDto productDto);
	
	@Mapping(target = "category",expression = "java(categorieToLibelle(product.getCategory()))")
	public abstract ProductResponseDto entityToResponseDto(Product product);
	
	
	public abstract List<ProductResponseDto> entityToResponseDto(List<Product> products);
	
	
	protected Category libelleToCategorie(String libelle) {
		Optional<Category> optionalCategory=categorieRepository.findByLibelle(libelle);
		if(optionalCategory.isPresent())
			return optionalCategory.get();
		else 
			throw new AltenShopNotFoundException(String.format("No Category Found for the title %s", libelle));
	}
	
	
	protected String categorieToLibelle(Category categorie) {
		return categorie!=null ?  categorie.getLibelle():null;
	}

}
