package com.Esport.Modele;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;

public class Joueur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Le pseudo ne peut pas être vide")
	@Size(min = 3, max = 30, message = "Le pseudo doit contenir entre 3 et 30 caractères")
	private String pseudo;

	@Min(value = 16, message = "L'âge minimum est de 16 ans")
	@Max(value = 50, message = "L'âge maximum est de 50 ans")
	private int age;

	@ManyToOne
	@JoinColumn(name = "equipe_id")
	private Equipe equipe;

	// constructeur with all attributes
	public Joueur(Long id, String pseudo, int age, Equipe equipe) {
		this.id = id;
		this.pseudo = pseudo;
		this.age = age;
		this.equipe = equipe;
	}
	// constructeur without id
	public Joueur(String pseudo, int age, Equipe equipe) {
		this.pseudo = pseudo;
		this.age = age;
		this.equipe = equipe;
	}

	// empty constructeur
	public Joueur() {
	}

	// Getters and setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		if (pseudo == null || pseudo.trim().isEmpty()) {
			throw new IllegalArgumentException("Le pseudo ne peut pas être vide");
		}
		if (pseudo.length() < 3 || pseudo.length() > 30) {
			throw new IllegalArgumentException("Le pseudo doit contenir entre 3 et 30 caractères");
		}
		this.pseudo = pseudo;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if (age < 16 || age > 50) {
			throw new IllegalArgumentException("L'âge doit être compris entre 16 et 50 ans");
		}
		this.age = age;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

}
