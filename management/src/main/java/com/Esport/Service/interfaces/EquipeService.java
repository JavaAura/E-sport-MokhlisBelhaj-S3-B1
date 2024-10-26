package com.Esport.Service.interfaces;

import java.util.List;
import java.util.Optional;

import com.Esport.Modele.Equipe;

public interface EquipeService {

    public List<Equipe> findAll();
    public Optional<Equipe> findById(Long id);
    public boolean create(Equipe equipe);
    public boolean update(Equipe equipe);
    public boolean delete(Long id);
    public boolean addJoueurToEquipe(Long equipeId, Long joueurId);
    public boolean removeJoueurFromEquipe(Long equipeId, Long joueurId);

}
