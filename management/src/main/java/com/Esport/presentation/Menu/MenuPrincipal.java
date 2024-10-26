package com.Esport.presentation.Menu;

import com.Esport.Util.InputValidator;
import com.Esport.Util.LoggerUtil;

public class MenuPrincipal {
	
	public void afficherMenuPrincipal() {

		// i want chaw this menu in a loop until the user choose to exit
		while (true) {
			LoggerUtil.info("Menu Principal");
			LoggerUtil.info("1. Menu Tournoi");
			LoggerUtil.info("2. Menu Equipe");
			LoggerUtil.info("3. Menu Joueur");
			LoggerUtil.info("4. Menu Jeu");
			LoggerUtil.info("5. Exit");
			LoggerUtil.info("Choisissez une option : ");
			InputValidator.validateMenuPrincipalInput();

			switch (InputValidator.validateMenuPrincipalInput()) {
				case 1:
					MenuTournoi menuTournoi = new MenuTournoi();
					menuTournoi.afficherMenuTournoi();
					break;
				case 2:
					MenuEquipe menuEquipe = new MenuEquipe();
					menuEquipe.afficherMenuEquipe();
					break;
				case 3:
					MenuJoueur menuJoueur = new MenuJoueur();
					menuJoueur.afficherMenuJoueur();
					break;
				case 4:
					MenuJeu menuJeu = new MenuJeu();
					menuJeu.afficherMenuJeu();
					break;
				case 5:
					LoggerUtil.info("Exiting the program...");
					System.exit(0);
			}
	
			
		}
	}
}
