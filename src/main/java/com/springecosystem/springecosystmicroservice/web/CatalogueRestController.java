package com.springecosystem.springecosystmicroservice.web;


import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.springecosystem.springecosystmicroservice.models.Produit;
import com.springecosystem.springecosystmicroservice.repository.ProduitRepository;

@RestController
@CrossOrigin("*")
public class CatalogueRestController {

	@Autowired
	private ProduitRepository produitRepository;
	
	@GetMapping(path = "/photoProduit/{id}",produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] getPhoto(@PathVariable("id") Long id) throws Exception {
		Produit p = produitRepository.findById(id).get();
		return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/ecom/produits/"+p.getPhotoName()));
	}
	
	@PostMapping(path = "/uploadPhoto/{id}")
	public void uploadPhoto(MultipartFile file,@PathVariable Long id) throws Exception {
		Produit p = produitRepository.findById(id).get();
		p.setPhotoName(file.getOriginalFilename());
		Files.write(Paths.get(System.getProperty("user.home")+"/ecom/produits/"+p.getPhotoName()), file.getBytes());
	    produitRepository.save(p);
	}
}
