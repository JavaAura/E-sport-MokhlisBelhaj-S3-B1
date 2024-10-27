package com.Esport.presentation.Menu;

import com.Esport.Service.interfaces.JoueurService;
import com.Esport.Util.LoggerUtil;
public class MenuJoueur {

    private final JoueurService joueurService;

    public MenuJoueur(JoueurService joueurService) {
        this.joueurService = joueurService;
    }

    public static void afficherMenuJoueur(){
        LoggerUtil.info("Menu Joueur");
    }

}
