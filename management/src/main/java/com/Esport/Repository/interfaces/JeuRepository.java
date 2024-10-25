package com.Esport.Repository.interfaces;

import java.util.List;
import java.util.Optional;

import com.Esport.Modele.Jeu;

public interface JeuRepository {

    public List<Jeu> findAll();
    public Optional<Jeu> findById(Long id);
    public boolean create(Jeu jeu);
    public boolean update(Jeu jeu);
    public boolean delete(Long id);

}
