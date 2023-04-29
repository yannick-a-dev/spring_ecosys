package com.springecosystem.springecosystmicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.springecosystem.springecosystmicroservice.models.Produit;

@RepositoryRestResource
@CrossOrigin("*")
public interface ProduitRepository extends JpaRepository<Produit, Long> {

	@RestResource(path = "/selectedProduits")
	public List<Produit> findBySelectedIsTrue();
	
	//autre solution de findByNameContains
//	@RestResource(path = "/produitsByKeyword")   
//	@Query("select p from Produit p where p.name like :x")
//	public List<Produit> chercher(@Param("x") String mc);
	
	@RestResource(path = "/produitsByKeyword")
	public List<Produit> findByNameContains(@Param("mc") String mc);
	
	@RestResource(path = "/promoProduits")
	public List<Produit> findByPromotionIsTrue();
	
	@RestResource(path = "/dispoProduits")
	public List<Produit> findByAvailableIsTrue();
}
