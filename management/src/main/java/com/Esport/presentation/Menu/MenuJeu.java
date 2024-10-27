package com.Esport.presentation.Menu;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import com.Esport.Modele.Jeu;
import com.Esport.Modele.Enum.JeuDifficulte;
import com.Esport.Service.interfaces.JeuService;
import com.Esport.Util.InputValidator;
import com.Esport.Util.LoggerUtil;

public class MenuJeu {

    private final JeuService jeuService;

    public MenuJeu(JeuService jeuService) {
        this.jeuService = jeuService;
    }

    public void afficherMenuJeu() {
        boolean condition = true;
        while (condition) {
            LoggerUtil.info("Menu Jeu");
            LoggerUtil.info("1. Afficher les jeux");
            LoggerUtil.info("2. Chercher un jeu");
            LoggerUtil.info("3. Ajouter un jeu");
            LoggerUtil.info("4. Modifier un jeu");
            LoggerUtil.info("5. Supprimer un jeu");
            LoggerUtil.info("6. Retour au menu principal");
            LoggerUtil.info("Choisissez une option : ");
            int choice = InputValidator.validateMenuChoice(1, 6);

            switch (choice) {
                case 1:
                    displayJeu();
                    break;
                case 2:
                    searchJeu();
                    break;
                case 3:
                    addJeu();
                    break;
                case 4:
                    updateJeu();
                    break;
                case 5:
                    deleteJeu();
                    break;
                case 6:
                    condition = false;
                    break;
            }
        }
    }

    public void displayJeu() {
        List<Jeu> jeux = jeuService.findAll();

        if (jeux.isEmpty()) {
            LoggerUtil.info("Aucun jeu trouvé.");
            return;
        }
        
        // Print table header
        LoggerUtil.info(String.format("%-20s %-20s %-20s %-20s", 
           "ID", "Nom",  "Difficulté", "Durée moyenne"));
        LoggerUtil.info("--------------------------------------------------------------------------------");
        
        // Print table rows
        for (Jeu jeu : jeux) {
            LoggerUtil.info(String.format("%-20s %-20s %-20s %-20s",
                truncate(jeu.getId().toString(), 20),
                truncate(jeu.getNom(), 20),
                truncate(jeu.getDifficulte().toString(), 20),
                truncate(jeu.getDureeMoyenneMatch().toString(), 20)));
        }
    }

    // Helper method to truncate strings
    private static String truncate(String str, int length) {
        return str.length() > length ? str.substring(0, length - 3) + "..." : str;
    }

    public void searchJeu() {
        LoggerUtil.info("Rechercher un jeu");
        long id = InputValidator.validateLongInput();
        Optional<Jeu> jeu = jeuService.findById(id);
        if (jeu.isPresent()) {
            LoggerUtil.info(jeu.get().getNom());
            LoggerUtil.info(jeu.get().getDifficulte().toString());
            LoggerUtil.info(jeu.get().getDureeMoyenneMatch().toString());
        } else {
            LoggerUtil.info("Aucun jeu trouvé avec cet id.");
        }
    }

    public void addJeu() {
        LoggerUtil.info("Ajouter un jeu");
        String nom = InputValidator.validateStringInput();
        LoggerUtil.info("Choisissez la difficulté du jeu (1. Facile, 2. Moyen, 3. Difficile)  ");
        int choice = InputValidator.validateMenuChoice(1, 3);
        JeuDifficulte difficulte = null;
        switch (choice) {
            case 1:
                difficulte = JeuDifficulte.FACILE;
                break;
            case 2:
                difficulte = JeuDifficulte.MOYEN;
                break;
            case 3:
                difficulte = JeuDifficulte.DIFFICILE;
                break;
        }
        Duration dureeMoyenneMatch = InputValidator.validateDurationInput();
        Jeu jeu = new Jeu(nom, difficulte, dureeMoyenneMatch);
        
        jeuService.create(jeu);
       
    }

    public void updateJeu() {
      LoggerUtil.info("Modifier un jeu");
      LoggerUtil.info("Entrer l'id du jeu à modifier : ");
      long id = InputValidator.validateLongInput();
      Optional<Jeu> jeu = jeuService.findById(id);

      if (jeu.isPresent()) {
        LoggerUtil.info("Voulez vous modifier le nom du jeu ? (1. Oui, 2. Non) ");
        int choice = InputValidator.validateMenuChoice(1, 2);
        if (choice == 1) {
          LoggerUtil.info("Entrer le nouveau nom du jeu : ");
          String nom = InputValidator.validateStringInput();
          jeu.get().setNom(nom);
        }

        LoggerUtil.info("Voulez vous modifier la difficulté du jeu ? (1. Oui, 2. Non) ");
        choice = InputValidator.validateMenuChoice(1, 2);
        if (choice == 1) {
          LoggerUtil.info("Choisissez la nouvelle difficulté du jeu (1. Facile, 2. Moyen, 3. Difficile)  ");
          JeuDifficulte difficulte = null;
          choice = InputValidator.validateMenuChoice(1, 3);
          switch (choice) {
            case 1:
                difficulte = JeuDifficulte.FACILE;
                break;
            case 2:
                difficulte = JeuDifficulte.MOYEN;
                break;
            case 3:
                difficulte = JeuDifficulte.DIFFICILE;
                break;
          }
          jeu.get().setDifficulte(difficulte);
        }

        LoggerUtil.info("Voulez vous modifier la durée moyenne du match du jeu ? (1. Oui, 2. Non) ");
        choice = InputValidator.validateMenuChoice(1, 2);
        if (choice == 1) {
          Duration dureeMoyenneMatch = InputValidator.validateDurationInput();
          jeu.get().setDureeMoyenneMatch(dureeMoyenneMatch);
        }
        boolean result = jeuService.update(jeu.get());
        if (result) {
          LoggerUtil.info("Jeu modifié avec succès.");
        } else {
          LoggerUtil.info("Erreur lors de la modification du jeu.");
        }
      } else {
        LoggerUtil.info("Aucun jeu trouvé avec cet id.");
      }
    }

    public void deleteJeu() {
        LoggerUtil.info("Supprimer un jeu");
        LoggerUtil.info("Entrer l'id du jeu à supprimer : ");
        long id = InputValidator.validateLongInput();
        Optional<Jeu> jeu = jeuService.findById(id);
        if (jeu.isPresent()) {
           boolean result = jeuService.delete(jeu.get().getId());
           if (result) {
            LoggerUtil.info("Jeu supprimé avec succès.");
           } else {
            LoggerUtil.info("Erreur lors de la suppression du jeu.");
           }
        } else {
            LoggerUtil.info("Aucun jeu trouvé avec cet id.");
        }
    }
}
