package org.elay;

import org.elay.Dao.ProduitRepository;
import org.elay.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication(scanBasePackages = "org.elay")
public class MyCataloqueMvcApplication implements CommandLineRunner{

	@Autowired
	private ProduitRepository produitrepository;
	
	public static void main(String[] args) {
		SpringApplication.run(MyCataloqueMvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("***********************");
		
//		produitrepository.save(new Produit("USB", 150, 1000,10));
//		produitrepository.save(new Produit("HC", 20000, 50,25));
//		produitrepository.save(new Produit("Phone", 2000, 500,10));
//		produitrepository.save(new Produit("SD", 80, 100,1));
//		produitrepository.save(new Produit("Key", 60, 50,25));
//		produitrepository.save(new Produit("Cam", 1500, 500,12));
//		produitrepository.save(new Produit("Book", 300, 1000,10));
//		produitrepository.save(new Produit("Mac", 50000, 50,14));
//		produitrepository.save(new Produit("Intel", 2000, 500,13));
//		Page<Produit> produits=produitrepository.findByDesignationContains("H",PageRequest.of(0,2));
		Page<Produit> produits=produitrepository.chercher("%H%", 100, PageRequest.of(0,2));
		System.out.println(produits.getSize());
		produits.forEach(p->{
			System.out.println(p.getDesignation());
		});
		
	}

} 
