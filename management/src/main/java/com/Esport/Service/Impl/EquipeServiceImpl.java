package com.Esport.Service.Impl;

import java.util.List;
import java.util.Optional;

import com.Esport.Repository.interfaces.EquipeRepository;
import com.Esport.Repository.interfaces.JoueurRepository;   
import com.Esport.Modele.Equipe;
import com.Esport.Modele.Joueur;
import com.Esport.Service.interfaces.EquipeService;
import com.Esport.Util.LoggerUtil;

public class EquipeServiceImpl implements EquipeService  {

    private EquipeRepository equipeRepository;
    private JoueurRepository joueurRepository;

    public EquipeServiceImpl(EquipeRepository equipeRepository, JoueurRepository joueurRepository) {
        this.equipeRepository = equipeRepository;   
        this.joueurRepository = joueurRepository;

    }

    @Override
    public List<Equipe> findAll() {
        return equipeRepository.findAll();
    }

    @Override
    public Optional<Equipe> findById(Long id) {
        return equipeRepository.findById(id);
    }

    @Override
    public boolean create(Equipe equipe) {
        return equipeRepository.create(equipe);
    }

    @Override
    public boolean update(Equipe equipe) {
        return equipeRepository.update(equipe);
    }

 

    @Override
    public boolean addJoueurToEquipe(Long equipeId, Long joueurId) {
        Optional<Joueur> joueur = joueurRepository.findById(joueurId);

        if (joueur.get().getEquipe() != null) {
            LoggerUtil.error("Joueur already in an equipe");
            return false;
        }
        joueur.get().setEquipe(equipeRepository.findById(equipeId).get());
        return joueurRepository.update(joueur.get());
    }

    @Override
    public boolean removeJoueurFromEquipe(Long equipeId, Long joueurId) {
        Optional<Joueur> joueur = joueurRepository.findById(joueurId);
        if (joueur.get().getEquipe().getId() != equipeId) {
            LoggerUtil.error("Joueur not in this equipe");
            return false;
        } else {
            joueur.get().setEquipe(null);
            return joueurRepository.update(joueur.get());
            
        }
    }

}
