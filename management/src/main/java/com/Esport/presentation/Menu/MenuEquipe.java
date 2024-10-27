package com.Esport.presentation.Menu;

import com.Esport.Service.interfaces.EquipeService;
import com.Esport.Util.LoggerUtil;

public class MenuEquipe {

    private final EquipeService equipeService;

    public MenuEquipe(EquipeService equipeService) {
        this.equipeService = equipeService;
    }

    public static void afficherMenuEquipe(){
        LoggerUtil.info("Menu Equipe");
    }

}
