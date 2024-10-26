package com.Esport.Repository.interfaces;

import com.Esport.Modele.Jeu;
import java.util.List;
import java.util.Optional;

public interface JeuRepository {

    List<Jeu> findAll();
    Optional<Jeu> findById(Long id);
    boolean create(Jeu jeu);
    boolean update(Jeu jeu);
    boolean delete(Long id);

}
