package com.springecosystem.springecosystmicroservice;

import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import net.bytebuddy.utility.RandomString;
import com.springecosystem.springecosystmicroservice.models.Category;
import com.springecosystem.springecosystmicroservice.models.Produit;
import com.springecosystem.springecosystmicroservice.repository.CategoryRepository;
import com.springecosystem.springecosystmicroservice.repository.ProduitRepository;

@SpringBootApplication
public class SpringEcosystMicroserviceApplication implements CommandLineRunner {

	@Autowired
	private ProduitRepository produitRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(SpringEcosystMicroserviceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		repositoryRestConfiguration.exposeIdsFor(Produit.class,Category.class);
		categoryRepository.save(new Category(null, "computer",null,null, null));
		categoryRepository.save(new Category(null, "printers",null,null, null));
		categoryRepository.save(new Category(null, "smartphones", null,null, null));
		Random rnd = new Random();
		categoryRepository.findAll().forEach(c -> {
			for (int i = 0; i < 10; i++) {
				Produit p = new Produit();
				p.setName(RandomString.make(18));
				p.setCurrentPrice(100 + rnd.nextInt(10000));
				p.setAvailable(rnd.nextBoolean());
				p.setPromotion(rnd.nextBoolean());
				p.setSelected(rnd.nextBoolean());
				p.setCategory(c);
				p.setPhotoName("unknown.png");
				produitRepository.save(p);
			}

		});
	}

}
