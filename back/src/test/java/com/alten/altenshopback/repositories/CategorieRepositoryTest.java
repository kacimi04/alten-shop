package com.alten.altenshopback.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.alten.altenshopback.models.Category;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;

@DBRider 
@SpringBootTest
@DBUnit(cacheConnection = false, leakHunter = true)
@ActiveProfiles("test")
class CategorieRepositoryTest {
  
	@Autowired
	private CategorieRepository categorieRepository;
	
	@Test
	@DataSet(value = "category.yml")
	void findByLibelle(){
		Optional<Category> categoryOptional= categorieRepository.findByLibelle("category1");
		assertTrue(categoryOptional.isPresent());
		assertEquals("category1", categoryOptional.get().getLibelle());
		assertEquals(1, categoryOptional.get().getId());
	}
}
