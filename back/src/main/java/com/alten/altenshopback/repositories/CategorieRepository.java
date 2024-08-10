package com.alten.altenshopback.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alten.altenshopback.models.Category;

@Repository
public interface CategorieRepository extends JpaRepository<Category,Integer> {
	
	@Query("select cat from Category cat where cat.libelle=:libelle")
	public Optional<Category> findByLibelle(String libelle);

}
