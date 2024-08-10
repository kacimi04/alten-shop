package com.alten.altenshopback.mappers;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.springframework.beans.factory.annotation.Autowired;

import com.alten.altenshopback.dto.CategoryDto;
import com.alten.altenshopback.exceptions.AltenShopNotFoundException;
import com.alten.altenshopback.models.Category;
import com.alten.altenshopback.repositories.CategorieRepository;

@Mapper(componentModel = ComponentModel.SPRING)
public abstract class  CategorieMapper {
	
	
	
	 CategorieMapper() {
		 super();
	}
	
	 
	public abstract CategoryDto entityToDto(Category category);
	
	
	public abstract Category dtoToEnity(CategoryDto categoryDto);
	
	
	
	
	
	
	
	
}
