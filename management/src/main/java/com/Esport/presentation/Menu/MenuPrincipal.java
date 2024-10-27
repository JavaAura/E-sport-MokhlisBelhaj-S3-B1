package com.Esport.presentation.Menu;

import com.Esport.Util.InputValidator;
import com.Esport.Util.LoggerUtil;

// Import other necessary services

public class MenuPrincipal {

    private final MenuJeu menuJeu;
    private final MenuTournoi menuTournoi;
    private final MenuEquipe menuEquipe;
    private final MenuJoueur menuJoueur;

    public MenuPrincipal(MenuJeu menuJeu, MenuTournoi menuTournoi, MenuEquipe menuEquipe, MenuJoueur menuJoueur) {
        this.menuJeu = menuJeu;
        this.menuTournoi = menuTournoi;
        this.menuEquipe = menuEquipe;
        this.menuJoueur = menuJoueur;
    }

    public void afficherMenuPrincipal() {
        while (true) {
            LoggerUtil.info("Menu Principal");
            LoggerUtil.info("1. Menu Tournoi");
            LoggerUtil.info("2. Menu Equipe");
            LoggerUtil.info("3. Menu Joueur");
            LoggerUtil.info("4. Menu Jeu");
            LoggerUtil.info("5. Exit");
            LoggerUtil.info("Choisissez une option : ");
            int choice = InputValidator.validateMenuChoice(1, 5);

            switch (choice) {
                case 1:
                    menuTournoi.afficherMenuTournoi();
                    break;
                case 2:
                    menuEquipe.afficherMenuEquipe();
                    break;
                case 3:
                    menuJoueur.afficherMenuJoueur();
                    break;
                case 4:
                    menuJeu.afficherMenuJeu();
                    break;
                case 5:
                    LoggerUtil.info("Exiting the program...");
                    System.exit(0);
            }
        }
    }
}
