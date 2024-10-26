package com.Esport.Service.interfaces;

import java.util.List;
import java.util.Optional;

import com.Esport.Modele.Tournoi;



public interface TournoiService {

    public List<Tournoi> findAll();
    public Optional<Tournoi> findById(Long id);
    public boolean create(Tournoi tournoi);
    public boolean update(Tournoi tournoi);
    public boolean delete(Long id);
    public boolean addEquipeToTournoi(Long tournoiId, Long equipeId);
    public boolean removeEquipeFromTournoi(Long tournoiId, Long equipeId);
}
