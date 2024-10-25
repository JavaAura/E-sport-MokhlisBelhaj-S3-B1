package com.Esport.Modele;

import java.time.Duration;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;

import com.Esport.Modele.Enum.JeuDifficulte;


public class Jeu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Le nom du jeu ne peut pas être vide")
	@Size(max = 100, message = "Le nom du jeu ne peut pas dépasser 100 caractères")
	@Column(name = "nom", nullable = false)
	private String nom;

	@NotBlank(message = "La difficulté ne peut pas être vide")
	@Pattern(regexp = "^(Facile|Moyen|Difficile)$", message = "La difficulté doit être Facile, Moyen ou Difficile")
	@Column(name = "difficulte", nullable = false)
	private JeuDifficulte difficulte;

	@NotNull(message = "La durée moyenne du match ne peut pas être nulle")
	@Positive(message = "La durée moyenne du match doit être positive")
	@Column(name = "duree_moyenne_match", nullable = false)
	private Duration dureeMoyenneMatch;

	@OneToMany(mappedBy = "jeu")
	private List<Tournoi> tournois;

	// constructeur with all attributes
	public Jeu(Long id, String nom, JeuDifficulte difficulte, Duration dureeMoyenneMatch) {
		this.id = id;
		this.nom = nom;
		this.difficulte = difficulte;
		this.dureeMoyenneMatch = dureeMoyenneMatch;
	}

	// constructeur without id
	public Jeu(String nom, JeuDifficulte difficulte, Duration dureeMoyenneMatch) {
		this.nom = nom;
		this.difficulte = difficulte;
		this.dureeMoyenneMatch = dureeMoyenneMatch;
	}

	// empty constructeur
	public Jeu() {
	}

	// getters and setters

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

	public JeuDifficulte getDifficulte() {
		return difficulte;
	}

	public void setDifficulte(JeuDifficulte difficulte) {
		this.difficulte = difficulte;
	}

	public Duration getDureeMoyenneMatch() {
		return dureeMoyenneMatch;
	}

	public void setDureeMoyenneMatch(Duration dureeMoyenneMatch) {
		this.dureeMoyenneMatch = dureeMoyenneMatch;
	}

	public List<Tournoi> getTournois() {
		return tournois;
	}

	public void setTournois(List<Tournoi> tournois) {
		this.tournois = tournois;
	}

}
