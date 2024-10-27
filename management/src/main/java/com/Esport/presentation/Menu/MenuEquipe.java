package com.Esport.presentation.Menu;

import java.util.List;
import java.util.Optional;

import com.Esport.Modele.Equipe;
import com.Esport.Modele.Joueur;
import com.Esport.Service.interfaces.EquipeService;
import com.Esport.Util.InputValidator;
import com.Esport.Util.LoggerUtil;

public class MenuEquipe {

	private final EquipeService equipeService;

	public MenuEquipe(EquipeService equipeService) {
		this.equipeService = equipeService;
	}

	public void afficherMenuEquipe() {
		boolean condition = true;
		while (condition) {
			LoggerUtil.info("Menu Equipe");
			LoggerUtil.info("1. Ajouter une équipe");
			LoggerUtil.info("2. Modifier une équipe");
			LoggerUtil.info("3. Afficher les équipes");
			LoggerUtil.info("4. Afficher une équipe");
			LoggerUtil.info("5. Ajouter un joueur à une équipe");
			LoggerUtil.info("6. Retirer un joueur d'une équipe");
			LoggerUtil.info("7. Retourner au menu principal");
			switch (InputValidator.validateMenuChoice(1, 7)) {
			case 1:
				ajouterEquipe();
				break;
			case 2:
				modifierEquipe();
				break;
			case 3:
				afficherEquipes();
				break;
			case 4:
				afficherEquipe();
				break;
			case 5:
				ajouterJoueurEquipe();
				break;
			case 6:
				retirerJoueurEquipe();
				break;
			case 7:
				condition = false;
				break;
			}
		}

	}

	private void ajouterEquipe() {
		LoggerUtil.info("Ajouter une équipe");
		Equipe equipe = new Equipe();
		LoggerUtil.info("Entrez le nom de l'équipe : ");
		String equipeNam = InputValidator.validateStringInput();
		equipe.setNom(equipeNam);
		equipeService.create(equipe);
	}

    public void modifierEquipe() {
        try {
            LoggerUtil.info("Modifier une équipe");
            LoggerUtil.info("Entrez l'id de l'équipe à modifier : ");
            Long id = InputValidator.validateLongInput();
            Optional<Equipe> equipe = equipeService.findById(id);
            if (equipe.isPresent()) {
            LoggerUtil.info("Voulez-vous modifier le nom de l'équipe ? 1. Oui 2. Non");
            int choice = InputValidator.validateMenuChoice(1, 2);
            if (choice == 1) {
                LoggerUtil.info("Entrez le nouveau nom de l'équipe : ");
                String equipeNam = InputValidator.validateStringInput();
                equipe.get().setNom(equipeNam);
            }
            LoggerUtil.info("Voulez-vous modifier le classement de l'équipe ? 1. Oui 2. Non");
            choice = InputValidator.validateMenuChoice(1, 2);
            if (choice == 1) {
                LoggerUtil.info("Entrez le nouveau classement de l'équipe : ");
                int classement = InputValidator.validateIntInput();
                equipe.get().setClassement(classement);
            }
            boolean result = equipeService.update(equipe.get());
            if (result) {
                LoggerUtil.info("L'équipe a été modifiée avec succès");
            } else {
                LoggerUtil.error("Erreur lors de la modification de l'équipe");
            }
        } else {
            LoggerUtil.error("L'équipe avec l'id " + id + " n'existe pas");
        }
        } catch (Exception e) {
            LoggerUtil.error("Erreur lors de la modification de l'équipe : " + e.getMessage());
        }
    }

    public void afficherEquipes() {
        try {
        	
            LoggerUtil.info("Afficher une équipe");
            List<Equipe> equipes = equipeService.findAll();
            if (equipes.isEmpty()) {
                LoggerUtil.info("Aucune équipe trouvée");
            } else {
                LoggerUtil.info("Liste des équipes");
                LoggerUtil.info("--------------------------------");
                for (Equipe equipe : equipes) {
                    LoggerUtil.info("ID : " + equipe.getId());
                    LoggerUtil.info("Nom : " + equipe.getNom());
                    LoggerUtil.info("Classement : " + equipe.getClassement());
                    if (equipe.getTournoi() != null) {
                        LoggerUtil.info("Tournoi : " + equipe.getTournoi().getTitre());
                    } else {
                        LoggerUtil.info("Tournoi : Aucun tournoi associé");
                    }
                    if (equipe.getJoueurs() != null) {
                        LoggerUtil.info("Joueurs : " + equipe.getJoueurs().size());
                    } else {
                        LoggerUtil.info("Joueurs : 0");
                    }
                    LoggerUtil.info("--------------------------------");
                }
            }
        } catch (Exception e) {
            LoggerUtil.error("Erreur lors de l'affichage des équipes : " + e);
        }
    }

    public void afficherEquipe() {
        try {
            LoggerUtil.info("Afficher une équipe");
            LoggerUtil.info("Entrez l'id de l'équipe à afficher : ");
            Long id = InputValidator.validateLongInput();
            Optional<Equipe> equipeOpt = equipeService.findById(id);
            if (equipeOpt.isPresent()) {
                Equipe equipe = equipeOpt.get();
                LoggerUtil.info("--------------------------------");
                LoggerUtil.info("Détails de l'équipe");
                LoggerUtil.info("--------------------------------");
                LoggerUtil.info("ID : " + equipe.getId());
                LoggerUtil.info("Nom : " + equipe.getNom());
                LoggerUtil.info("Classement : " + equipe.getClassement());
                if (equipe.getTournoi() != null) {
                    LoggerUtil.info("Tournoi : " + equipe.getTournoi().getTitre());
                } else {
                    LoggerUtil.info("Tournoi : Aucun tournoi associé");
                }
                LoggerUtil.info("--------------------------------");
                LoggerUtil.info("Liste des joueurs");
                LoggerUtil.info("--------------------------------");
                if (equipe.getJoueurs() != null && !equipe.getJoueurs().isEmpty()) {
                    LoggerUtil.info("+---------------+-------+");
                    LoggerUtil.info("|    Pseudo     |  Age  |");
                    LoggerUtil.info("+---------------+-------+");
                    for (Joueur joueur : equipe.getJoueurs()) {
                        String formattedLine = String.format("| %-13s | %-5d |", 
                            joueur.getPseudo(), 
                            joueur.getAge());
                        LoggerUtil.info(formattedLine);
                    }
                    LoggerUtil.info("+---------------+-------+");
                } else {
                    LoggerUtil.info("Aucun joueur dans cette équipe");
                }
            } else {
                LoggerUtil.error("L'équipe avec l'id " + id + " n'existe pas");
            }
        } catch (Exception e) {
            LoggerUtil.error("Erreur lors de l'affichage de l'équipe : " + e.getMessage());
        }
    }




    public void ajouterJoueurEquipe() {
        try {
            LoggerUtil.info("Ajouter un joueur à une équipe");
            LoggerUtil.info("Entrez l'id de l'équipe : ");
            Long equipeId = InputValidator.validateLongInput();
            LoggerUtil.info("Entrez l'id du joueur à ajouter : ");
            Long joueurId = InputValidator.validateLongInput();
            boolean result = equipeService.addJoueurToEquipe(equipeId, joueurId);
            if (result) {
                LoggerUtil.info("Le joueur a été ajouté à l'équipe avec succès");
            } else {
                LoggerUtil.error("Erreur lors de l'ajout du joueur à l'équipe");
            }
        } catch (Exception e) {
            LoggerUtil.error("Une erreur est survenue lors de l'ajout du joueur à l'équipe : " + e.getMessage());
        }
    }
    public void retirerJoueurEquipe() {
        try {
            LoggerUtil.info("Retirer un joueur d'une équipe");
            LoggerUtil.info("Entrez l'id de l'équipe : ");
            Long equipeId = InputValidator.validateLongInput();
            LoggerUtil.info("Entrez l'id du joueur à retirer : ");
            Long joueurId = InputValidator.validateLongInput();
            boolean result = equipeService.removeJoueurFromEquipe(equipeId, joueurId);
            if (result) {
                LoggerUtil.info("Le joueur a été retiré de l'équipe avec succès");
            } else {
                LoggerUtil.error("Erreur lors de la suppression du joueur de l'équipe");
            }
        } catch (Exception e) {
            LoggerUtil.error("Une erreur est survenue lors du retrait du joueur de l'équipe : " + e.getMessage());
        }
    }






}
