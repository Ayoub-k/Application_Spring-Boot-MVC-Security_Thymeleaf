package org.elay.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

import org.thymeleaf.expression.Sets;





@Entity
//@Table(name="produitList")
public class Produit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@Column(length = 50,nullable = false)
	@NotNull(message = "Please input your designation.")
	@Size(min=1, max=30)
	private String designation;
	
	@NotNull(message = "Please input your prix.")
	@Min(0)
    @Max(50000000)
	private double prix;
	@NotNull(message = "Please input your quantite.")
	private double quantite;
	private int score;
	public Produit() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getScore() {
		return score;
	}
	
	public Produit(String designation, double prix, double quantite,int score) {
		super();
		this.score=score;
		this.designation = designation;
		this.prix = prix;
		this.quantite = quantite;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public double getQuantite() {
		return quantite;
	}
	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	
	

}
