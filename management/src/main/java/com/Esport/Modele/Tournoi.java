package com.Esport.Modele;

import java.time.LocalDate;
import java.time.Duration;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;

import com.Esport.Modele.Enum.StatutTournoi;

@Entity
@Table(name = "tournoi")
public class Tournoi {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Le titre ne peut pas être vide")
	@Size(max = 100, message = "Le titre ne peut pas dépasser 100 caractères")
	@Column(name = "titre", nullable = false)
	private String titre;

	@NotNull(message = "La date de début ne peut pas être nulle")
	@Future(message = "La date de début doit être dans le futur")
	@Column(name = "date_debut", nullable = false)
	private LocalDate dateDebut;

	@NotNull(message = "La date de fin ne peut pas être nulle")
	@Future(message = "La date de fin doit être dans le futur")
	@Column(name = "date_fin", nullable = false)
	private LocalDate dateFin;

	@Min(value = 0, message = "Le nombre de spectateurs ne peut pas être négatif")
	@Column(name = "nombre_spectateurs")
	private int nombreSpectateurs;

	@NotNull(message = "La durée estimée ne peut pas être nulle")
	@Positive(message = "La durée estimée doit être positive")
	@Column(name = "duree_estimee", nullable = false)
	private Duration dureeEstimee;

	@NotNull(message = "Le temps de pause entre les matchs ne peut pas être nul")
	@PositiveOrZero(message = "Le temps de pause entre les matchs doit être positif ou zéro")
	@Column(name = "temps_pause_entre_matchs", nullable = false)
	private Duration tempsPauseEntreMatchs;

	@NotNull(message = "Le temps de cérémonie ne peut pas être nul")
	@PositiveOrZero(message = "Le temps de cérémonie doit être positif ou zéro")
	@Column(name = "temps_ceremonie", nullable = false)
	private Duration tempsCeremonie;

	@Enumerated(EnumType.STRING)
	private StatutTournoi statut;

	@ManyToOne
	@JoinColumn(name = "jeu_id")
	private Jeu jeu;

	@ManyToMany
	@JoinTable(name = "tournoi_equipe", joinColumns = @JoinColumn(name = "tournoi_id"), inverseJoinColumns = @JoinColumn(name = "equipe_id"))
	private List<Equipe> equipes;

	// constructeur with all attributes
	public Tournoi(Long id, String titre, LocalDate dateDebut, LocalDate dateFin, int nombreSpectateurs,
			Duration dureeEstimee, Duration tempsPauseEntreMatchs, Duration tempsCeremonie, StatutTournoi statut,
			Jeu jeu, List<Equipe> equipes) {
		this.id = id;
		this.titre = titre;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nombreSpectateurs = nombreSpectateurs;
		this.dureeEstimee = dureeEstimee;
		this.tempsPauseEntreMatchs = tempsPauseEntreMatchs;
		this.tempsCeremonie = tempsCeremonie;
		this.statut = statut;
		this.jeu = jeu;
		this.equipes = equipes;
	}

	// constructeur without id
	public Tournoi(String titre, LocalDate dateDebut, LocalDate dateFin, int nombreSpectateurs, Duration dureeEstimee,
			Duration tempsPauseEntreMatchs, Duration tempsCeremonie, StatutTournoi statut, Jeu jeu,
			List<Equipe> equipes) {
		this.titre = titre;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nombreSpectateurs = nombreSpectateurs;
		this.dureeEstimee = dureeEstimee;
		this.tempsPauseEntreMatchs = tempsPauseEntreMatchs;
		this.tempsCeremonie = tempsCeremonie;
		this.statut = statut;
		this.jeu = jeu;
		this.equipes = equipes;
	}

	// empty constructeur
	public Tournoi() {
	}

	// getters and setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public int getNombreSpectateurs() {
		return nombreSpectateurs;
	}

	public void setNombreSpectateurs(int nombreSpectateurs) {
		this.nombreSpectateurs = nombreSpectateurs;
	}

	public Duration getDureeEstimee() {
		return dureeEstimee;
	}

	public void setDureeEstimee(Duration dureeEstimee) {
		this.dureeEstimee = dureeEstimee;
	}

	public Duration getTempsPauseEntreMatchs() {
		return tempsPauseEntreMatchs;
	}

	public void setTempsPauseEntreMatchs(Duration tempsPauseEntreMatchs) {
		this.tempsPauseEntreMatchs = tempsPauseEntreMatchs;
	}

	public Duration getTempsCeremonie() {
		return tempsCeremonie;
	}

	public void setTempsCeremonie(Duration tempsCeremonie) {
		this.tempsCeremonie = tempsCeremonie;
	}

	public StatutTournoi getStatut() {
		return statut;
	}

	public void setStatut(StatutTournoi statut) {
		this.statut = statut;
	}

	public Jeu getJeu() {
		return jeu;
	}

	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}

	public List<Equipe> getEquipes() {
		return equipes;
	}

	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}

}
