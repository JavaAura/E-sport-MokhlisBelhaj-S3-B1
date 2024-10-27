package com.Esport.presentation.Menu;

import java.util.List;
import java.util.Optional;

import com.Esport.Modele.Joueur;
import com.Esport.Service.interfaces.JoueurService;
import com.Esport.Util.InputValidator;
import com.Esport.Util.LoggerUtil;

public class MenuJoueur {

	private final JoueurService joueurService;

	public MenuJoueur(JoueurService joueurService) {
		this.joueurService = joueurService;
	}

	public  void afficherMenuJoueur() {
		boolean condition = true;
		while (condition) {
			LoggerUtil.info("Menu Joueur");
			LoggerUtil.info("1. Afficher les joueurs");
			LoggerUtil.info("2. Chercher un joueur");
			LoggerUtil.info("3. Ajouter un joueur");
			LoggerUtil.info("4. Modifier un joueur");
			LoggerUtil.info("5. Supprimer un joueur");
			LoggerUtil.info("6. Retour au menu principal");
			LoggerUtil.info("Choisissez une option : ");

			int choice = InputValidator.validateMenuChoice(1, 6);

			switch (choice) {
			case 1:
				afficherJoueurs();
				break;
			case 2:
				chercherJoueur();
				break;
			case 3:
				ajouterJoueur();
				break;
			case 4:
				modifierJoueur();
				break;
			case 5:
				supprimerJoueur();
				break;
			case 6:
				condition = false;
				break;
			}
		}
	}
	

	public void afficherJoueurs() {
		List<Joueur> joueurs = joueurService.findAll();
		if (joueurs.isEmpty()) {
			LoggerUtil.info("Aucun joueur trouvé.");
			return;
		} else {
			
			LoggerUtil.info(String.format("%-20s %-20s %-20s", "ID", "Pseudo", "Age", "Equipe"));
			LoggerUtil.info("--------------------------------------------------------------------------------");
			
			for (Joueur joueur : joueurs) {
				
				String EquipeName = "sans équipe";
                
                if(joueur.getEquipe() != null){
                    EquipeName = joueur.getEquipe().getNom();
                }
						
				LoggerUtil.info(String.format("%-20s %-20s %-20s %-20s", joueur.getId(), joueur.getPseudo(),
						joueur.getAge(), EquipeName));
			}
		}
	}

	public void chercherJoueur() {
		LoggerUtil.info("Entrer l'id du joueur à chercher : ");
		long id = InputValidator.validateLongInput();
		Optional<Joueur> joueur = joueurService.findById(id);
		if (joueur.isPresent()) {
			LoggerUtil.info(String.format("%-20s %-20s %-20s %-20s", joueur.get().getId(), joueur.get().getPseudo(),
					joueur.get().getAge(),
					joueur.get().getEquipe() != null ? joueur.get().getEquipe().getNom() : "sans équipe"));
		} else {
			LoggerUtil.info("Aucun joueur trouvé avec cet id.");
		}

	}

	public void ajouterJoueur() {
		try {
			Joueur joueur = new Joueur();
			LoggerUtil.info("Entrer le pseudo du joueur : ");
			String pseudo = InputValidator.validateStringInput();
			LoggerUtil.info("Entrer l'age du joueur : ");
			int age = InputValidator.validateAgeInput();
			joueur.setPseudo(pseudo);
			joueur.setAge(age);
			boolean res = joueurService.create(joueur);
			if (res) {
				LoggerUtil.info("Joueur ajouté avec succès.");
			} else {
				LoggerUtil.info("Erreur lors de l'ajout du joueur.");
			}
		} catch (Exception e) {
			LoggerUtil.error("Erreur lors de l'ajout du joueur : " + e.getMessage());
		}
	}

	public void modifierJoueur() {
		try {
			LoggerUtil.info("Entrer l'id du joueur à modifier : ");
			long id = InputValidator.validateLongInput();
			Optional<Joueur> joueurOptional = joueurService.findById(id);

			if (joueurOptional.isPresent()) {
				Joueur joueur = joueurOptional.get();
				boolean modified = false;

				if (modifierChamp("pseudo", joueur.getPseudo())) {
					String newPseudo = InputValidator.validateStringInput();
					joueur.setPseudo(newPseudo);
					modified = true;
				}

				if (modifierChamp("age", String.valueOf(joueur.getAge()))) {
					int newAge = InputValidator.validateAgeInput();
					joueur.setAge(newAge);
					modified = true;
				}

				if (modified) {
					boolean res = joueurService.update(joueur);
					if (res) {
						LoggerUtil.info("Joueur modifié avec succès.");
					} else {
						LoggerUtil.info("Erreur lors de la modification du joueur.");
					}
				} else {
					LoggerUtil.info("Aucune modification effectuée.");
				}
			} else {
				LoggerUtil.info("Aucun joueur trouvé avec cet id.");
			}
		} catch (Exception e) {
			LoggerUtil.error("Erreur lors de la modification du joueur : " + e.getMessage());
		}
	}

	private boolean modifierChamp(String nomChamp, String valeurActuelle) {
		LoggerUtil.info(
				"Voulez-vous modifier " + nomChamp + " (" + valeurActuelle + ") du joueur ? 1 pour oui, 2 pour non : ");
		int choice = InputValidator.validateMenuChoice(1, 2);
		if (choice == 1) {
			LoggerUtil.info("Entrer le nouveau " + nomChamp + " : ");
			return true;
		}
		return false;
	}

	public void supprimerJoueur() {
		LoggerUtil.info("Entrer l'id du joueur à supprimer : ");
		long id = InputValidator.validateLongInput();
		boolean res = joueurService.delete(id);
		if (res) {
			LoggerUtil.info("Joueur supprimé avec succès.");
		} else {
			LoggerUtil.info("Erreur lors de la suppression du joueur.");
		}
	}
}
