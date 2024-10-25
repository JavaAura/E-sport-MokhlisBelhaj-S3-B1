package com.Esport.Modele;

import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Equipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Le nom de l'équipe ne peut pas être vide")
	@Size(max = 100, message = "Le nom de l'équipe ne peut pas dépasser 100 caractères")
	private String nom;

	@Min(value = 1, message = "Le classement doit être au moins 1")
	private int classement;

	@OneToMany(mappedBy = "equipe")
	private List<Joueur> joueurs;

	@ManyToMany(mappedBy = "equipes")
	private List<Tournoi> tournois;

	// constructeur with all attributes
	public Equipe(Long id, String nom, int classement, List<Joueur> joueurs, List<Tournoi> tournois) {
		this.id = id;
		this.nom = nom;
		this.classement = classement;
		this.joueurs = joueurs;
		this.tournois = tournois;
	}

		// constructeur without id
	public Equipe(String nom, int classement) {
		this.nom = nom;
		this.classement = classement;
	}

	// empty constructeur
	public Equipe() {
	}

	// Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getClassement() {
		return classement;
	}

	public void setClassement(int classement) {
		this.classement = classement;
	}

	public List<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(List<Joueur> joueurs) {
		this.joueurs = joueurs;
	}

	public List<Tournoi> getTournois() {
		return tournois;
	}

	public void setTournois(List<Tournoi> tournois) {
		this.tournois = tournois;
	}

}
