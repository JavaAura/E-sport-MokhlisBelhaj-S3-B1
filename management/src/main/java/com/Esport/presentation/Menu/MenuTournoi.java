package com.Esport.presentation.Menu;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.Esport.Modele.Equipe;
import com.Esport.Modele.Jeu;
import com.Esport.Modele.Tournoi;
import com.Esport.Modele.Enum.StatutTournoi;
import com.Esport.Service.interfaces.EquipeService;
import com.Esport.Service.interfaces.JeuService;
import com.Esport.Service.interfaces.TournoiService;
import com.Esport.Util.InputValidator;
import com.Esport.Util.LoggerUtil;

public class MenuTournoi {

	private final TournoiService tournoiService;
	private final JeuService jeuService;
	private final EquipeService equipeService;

	public MenuTournoi(TournoiService tournoiService , JeuService jeuService,EquipeService equipeService) {
		this.tournoiService = tournoiService;
		this.jeuService = jeuService;
		this.equipeService = equipeService;
	}

	public void afficherMenuTournoi() {
		boolean condition = true;
		while (condition) {
			LoggerUtil.info("Menu Tournoi");
			LoggerUtil.info("1. Afficher tous les tournois");
			LoggerUtil.info("2. Afficher un tournoi");
			LoggerUtil.info("3. Ajouter un tournoi");
			LoggerUtil.info("4. Modifier un tournoi");
			LoggerUtil.info("5. Ajouter une équipe à un tournoi");
			LoggerUtil.info("6. Retirer une équipe d'un tournoi");
			LoggerUtil.info("7. Retourner au menu principal");
			int choix = InputValidator.validateMenuChoice(1, 7);
			switch (choix) {
			case 1:
				afficherTournois();
				break;
			case 2:
				findTournoiById();
				break;
			case 3:
				ajouterTournoi();
				break;
			case 4:
				modifierTournoi();
				break;
			case 5:
				ajouterEquipeToTournoi();
				break;
			case 6:
				retirerEquipeFromTournoi();
				break;
			case 7:
				condition = false;
				break;
			}
		}

	}

	public void afficherTournois() {
		try {
			List<Tournoi> tournois = tournoiService.findAll();
			if (tournois.isEmpty()) {
				LoggerUtil.info("Aucun tournoi trouvé");
			} else {
				// Table header
				LoggerUtil.info(String.format(
						"%-5s | %-20s | %-15s | %-12s | %-12s | %-10s | %-15s | %-10s | %-10s | %-10s", "ID", "Titre",
						"Jeu", "Date Début", "Date Fin", "Spectateurs", "Durée Est.", "Pause", "Cérémonie", "Statut"));
				LoggerUtil.info(
						"------------------------------------------------------------------------------------------------------------------------------------");

				// Table rows
				for (Tournoi tournoi : tournois) {
					LoggerUtil.info(String.format(
							"%-5d | %-20s | %-15s | %-12s | %-12s | %-10d | %-15s | %-10s | %-10s | %-10s",
							tournoi.getId(), tournoi.getTitre(), tournoi.getJeu().getNom(), tournoi.getDateDebut(),
							tournoi.getDateFin(), tournoi.getNombreSpectateurs(),
							formatDuration(tournoi.getDureeEstimee()),
							formatDuration(tournoi.getTempsPauseEntreMatchs()),
							formatDuration(tournoi.getTempsCeremonie()), tournoi.getStatut().toString()));
				}
				LoggerUtil.info(
						"------------------------------------------------------------------------------------------------------------------------------------");
			}
		} catch (Exception e) {
			LoggerUtil.error(e.getMessage());
		}

	}

	private String formatDuration(Duration duration) {
		// Check for null or zero duration
		if (duration == null || duration.isZero()) {
			return "N/A";
		}

		long totalSeconds = duration.getSeconds();
		long hours = totalSeconds / 3600;
		long minutes = (totalSeconds % 3600) / 60;
		long seconds = totalSeconds % 60;

		return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}

	public void findTournoiById() {
		try {
			LoggerUtil.info("Entrer l'ID du tournoi : ");
			long id = InputValidator.validateLongInput();
			Optional<Tournoi> tournoi = tournoiService.findById(id);
			if (tournoi.isPresent()) {
				// Table header
				LoggerUtil.info(String.format(
						"%-5s | %-20s | %-15s | %-12s | %-12s | %-10s | %-15s | %-10s | %-10s | %-10s", "ID", "Titre",
						"Jeu", "Date Début", "Date Fin", "Spectateurs", "Durée Est.", "Pause", "Cérémonie", "Statut"));
				LoggerUtil.info(
						"------------------------------------------------------------------------------------------------------------------------------------");

				// Table row
				LoggerUtil.info(String.format(
						"%-5d | %-20s | %-15s | %-12s | %-12s | %-10d | %-15s | %-10s | %-10s | %-10s",
						tournoi.get().getId(), tournoi.get().getTitre(), tournoi.get().getJeu().getNom(),
						tournoi.get().getDateDebut(), tournoi.get().getDateFin(), tournoi.get().getNombreSpectateurs(),
						formatDuration(tournoi.get().getDureeEstimee()),
						formatDuration(tournoi.get().getTempsPauseEntreMatchs()),
						formatDuration(tournoi.get().getTempsCeremonie()), tournoi.get().getStatut().toString()));

				LoggerUtil.info(
						"------------------------------------------------------------------------------------------------------------------------------------");

				if (tournoi.get().getEquipes().isEmpty()) {
					LoggerUtil.info("Aucune équipe trouvée");
				} else {
					LoggerUtil.info("Liste des équipes : ");

					for (Equipe equipe : tournoi.get().getEquipes()) {
						LoggerUtil.info(equipe.getNom());
						LoggerUtil.info(String.valueOf(equipe.getClassement()));
						LoggerUtil.info(String.valueOf(equipe.getJoueurs().size()));
					}
				}
			} else {
				LoggerUtil.error("Tournoi non trouvé");
			}
		} catch (Exception e) {
			LoggerUtil.error(e.getMessage());
		}
	}

	public void ajouterTournoi() {
		try {  
			Tournoi tournoi = new Tournoi();
			LoggerUtil.info("Entrer le titre du tournoi : ");
			String titre = InputValidator.validateStringInput();
			LoggerUtil.info("Entrer l'ID du jeu : ");
			long idJeu = InputValidator.validateLongInput();
			Optional<Jeu> jeu = jeuService.findById(idJeu);
			
			if (!jeu.isPresent()) {
				LoggerUtil.error("Le jeu avec l'ID " + idJeu + " n'existe pas");
				return;
			}
			
			LoggerUtil.info("Entrer la date de début du tournoi : ");
			LocalDate dateDebut = InputValidator.validateDateInput();
			LoggerUtil.info("Entrer la date de fin du tournoi : ");
			LocalDate dateFin;
			do {
				dateFin = InputValidator.validateDateInput();
				if (dateFin.isBefore(dateDebut)) {
					LoggerUtil.error("La date de fin doit être postérieure à la date de début");
				}
			} while (dateFin.isBefore(dateDebut));
			
			LoggerUtil.info("Entrer le nombre de spectateurs : ");
			int nombreSpectateurs = InputValidator.validateIntInput();
			LoggerUtil.info("Entrer la durée de la pause entre matchs : ");
			Duration tempsPauseEntreMatchs = InputValidator.validateDurationInput();
			LoggerUtil.info("Entrer la durée de la cérémonie : ");
			Duration tempsCeremonie = InputValidator.validateDurationInput();
			LoggerUtil.info("Entrer le statut du tournoi (1.PLANIFIE, 2.EN_COURS, 3.TERMINE, 4.ANNULE) : ");
			int choice = InputValidator.validateMenuChoice(1, 4);
			
			switch (choice) {
				case 1:
					tournoi.setStatut(StatutTournoi.PLANIFIE);
					break;
				case 2:
					tournoi.setStatut(StatutTournoi.EN_COURS);
					break;
				case 3:
					tournoi.setStatut(StatutTournoi.TERMINE);
					break;
				case 4:
					tournoi.setStatut(StatutTournoi.ANNULE);
					break;
			}
         
           
			
			tournoi.setTitre(titre);
			tournoi.setJeu(jeu.get());
			tournoi.setDateDebut(dateDebut);
			tournoi.setDateFin(dateFin);
			tournoi.setNombreSpectateurs(nombreSpectateurs);
			tournoi.setDureeEstimee(Duration.ZERO); 
			tournoi.setTempsPauseEntreMatchs(tempsPauseEntreMatchs);
			tournoi.setTempsCeremonie(tempsCeremonie);
            LoggerUtil.info("Tournoi : " + tournoi.getId()+" "+tournoi.getTitre()+" "+tournoi.getJeu().getNom()+" "+tournoi.getDateDebut()+" "+tournoi.getDateFin()+" "+tournoi.getNombreSpectateurs()+" "+formatDuration(tournoi.getDureeEstimee())+" "+formatDuration(tournoi.getTempsPauseEntreMatchs())+" "+formatDuration(tournoi.getTempsCeremonie())+" "+tournoi.getStatut().toString());
			
			boolean result = tournoiService.create(tournoi);
			if (result) {
				LoggerUtil.info("Tournoi créé avec succès");
			} else {
				LoggerUtil.error("Erreur lors de la création du tournoi");
			}
		} catch (Exception e) {
			LoggerUtil.error("Erreur lors de l'ajout du tournoi : " + e.getMessage());
		}

	}


    public void modifierTournoi() {
        try {
        LoggerUtil.info("Entrer l'ID du tournoi à modifier : ");
        long id = InputValidator.validateLongInput();
        Optional<Tournoi> tournoi = tournoiService.findById(id);
        if (tournoi.isPresent()) {
            LoggerUtil.info("Voulez vous modifier le titre du tournoi ? (1.Oui, 2.Non) :");
            int choice = InputValidator.validateMenuChoice(1, 2);
            if (choice == 1) {
                LoggerUtil.info("Entrer le nouveau titre du tournoi : ");
                String titre = InputValidator.validateStringInput();
                tournoi.get().setTitre(titre);
            }
            LoggerUtil.info("Voulez vous modifier la date de début du tournoi et la date de fin ? (1.Oui, 2.Non) :");
            int choice2 = InputValidator.validateMenuChoice(1, 2);
            if (choice2 == 1) {
                LoggerUtil.info("Entrer la nouvelle date de début du tournoi : ");
                LocalDate dateDebut = InputValidator.validateDateInput();
                tournoi.get().setDateDebut(dateDebut);
                LocalDate dateFin;
                do {
                    LoggerUtil.info("Entrer la nouvelle date de fin du tournoi : ");
                     dateFin = InputValidator.validateDateInput();
                    if (dateFin.isBefore(dateDebut)) {
                        LoggerUtil.error("La date de fin doit être postérieure à la date de début");
                    }
                } while (dateFin.isBefore(dateDebut));
                tournoi.get().setDateFin(dateFin);
            }
            LoggerUtil.info("Voulez vous modifier le nombre de spectateurs ? (1.Oui, 2.Non) :");
            int choice3 = InputValidator.validateMenuChoice(1, 2);
            if (choice3 == 1) {
                LoggerUtil.info("Entrer le nouveau nombre de spectateurs : ");
                int nombreSpectateurs = InputValidator.validateIntInput();
                tournoi.get().setNombreSpectateurs(nombreSpectateurs);
            }
            LoggerUtil.info("Voulez vous modifier la durée de la pause entre matchs ? (1.Oui, 2.Non) :");
            int choice4 = InputValidator.validateMenuChoice(1, 2);
            if (choice4 == 1) {
                LoggerUtil.info("Entrer la nouvelle durée de la pause entre matchs : ");
                Duration tempsPauseEntreMatchs = InputValidator.validateDurationInput();
                tournoi.get().setTempsPauseEntreMatchs(tempsPauseEntreMatchs);
            }
            LoggerUtil.info("Voulez vous modifier la durée de la cérémonie ? (1.Oui, 2.Non) :");
            int choice5 = InputValidator.validateMenuChoice(1, 2);
            if (choice5 == 1) {
                LoggerUtil.info("Entrer la nouvelle durée de la cérémonie : ");
                Duration tempsCeremonie = InputValidator.validateDurationInput();
                tournoi.get().setTempsCeremonie(tempsCeremonie);
            }
            LoggerUtil.info("Voulez vous modifier le statut du tournoi ? (1.Oui, 2.Non) :");
            int choice6 = InputValidator.validateMenuChoice(1, 2);
            if (choice6 == 1) {
                LoggerUtil.info("Entrer le nouveau statut du tournoi (1.PLANIFIE, 2.EN_COURS, 3.TERMINE, 4.ANNULE) : ");
                int choiceStatut = InputValidator.validateMenuChoice(1, 4);
                switch (choiceStatut) {
                    case 1:
                     tournoi.get().setStatut(StatutTournoi.PLANIFIE);
                      break;
                    case 2:
                     tournoi.get().setStatut(StatutTournoi.EN_COURS); 
                     break;
                    case 3: 
                     tournoi.get().setStatut(StatutTournoi.TERMINE); 
                     break;
                    case 4: 
                     tournoi.get().setStatut(StatutTournoi.ANNULE); 
                     break;
                }
            }

           boolean result = tournoiService.update(tournoi.get());
           if (result) {
            LoggerUtil.info("Tournoi modifié avec succès");
           } else {
            LoggerUtil.error("Erreur lors de la modification du tournoi");
           }
        } else {
            LoggerUtil.error("Tournoi non trouvé");
        }
        } catch (Exception e) {
            LoggerUtil.error("Erreur lors de la modification du tournoi : " + e.getMessage());
        }
    }

    public void ajouterEquipeToTournoi() {
        try {
            LoggerUtil.info("Entrer l'ID du tournoi : ");
            long tournoiId = InputValidator.validateLongInput();
            LoggerUtil.info("Entrer l'ID de l'équipe à ajouter : ");
            long equipeId = InputValidator.validateLongInput();
            boolean result = tournoiService.addEquipeToTournoi(tournoiId, equipeId);
            if (result) {
                LoggerUtil.info("Équipe ajoutée au tournoi avec succès");
            } else {
                LoggerUtil.error("Erreur lors de l'ajout d'une équipe au tournoi");
            }
        } catch (Exception e) {
            LoggerUtil.error("Erreur lors de l'ajout d'une équipe au tournoi : " + e.getMessage());
        }
    }

    public void retirerEquipeFromTournoi() {
        try {
            LoggerUtil.info("Entrer l'ID du tournoi : ");
            long tournoiId = InputValidator.validateLongInput();
            LoggerUtil.info("Entrer l'ID de l'équipe à retirer : ");
            long equipeId = InputValidator.validateLongInput();
            boolean result = tournoiService.removeEquipeFromTournoi(tournoiId, equipeId);
            if (result) {
                LoggerUtil.info("Équipe retirée du tournoi avec succès");
            } else {
                LoggerUtil.error("Erreur lors de la suppression d'une équipe du tournoi");
            }
        } catch (Exception e) {
            LoggerUtil.error("Erreur lors de la suppression d'une équipe du tournoi : " + e.getMessage());
        }
    }
}
