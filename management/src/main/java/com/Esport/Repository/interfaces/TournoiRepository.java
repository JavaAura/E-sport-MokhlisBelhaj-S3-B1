package com.Esport.Repository.interfaces;

import java.util.List;
import java.util.Optional;

import com.Esport.Modele.Tournoi;

public interface TournoiRepository {

    public List<Tournoi> findAll();
    public Optional<Tournoi> findById(Long id);
    public boolean create(Tournoi tournoi);
    public boolean update(Tournoi tournoi);
  
}
