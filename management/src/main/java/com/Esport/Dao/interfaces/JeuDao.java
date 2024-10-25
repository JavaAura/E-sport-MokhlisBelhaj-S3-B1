package com.Esport.Dao.interfaces;

import java.util.List;
import java.util.Optional;
import com.Esport.Modele.Jeu;

public interface JeuDao {

    List<Jeu> findAll();
    Optional<Jeu> findById(Long id);
    boolean create(Jeu jeu);
    boolean update(Jeu jeu);
    boolean delete(Long id);

}
