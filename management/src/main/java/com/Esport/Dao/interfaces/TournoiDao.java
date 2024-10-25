package com.Esport.Dao.interfaces;

import java.util.List;
import java.util.Optional;

import com.Esport.Modele.Tournoi;
public interface TournoiDao {

    public List<Tournoi> findAll();
    public Optional<Tournoi> findById(Long id);
    public boolean create(Tournoi tournoi);
    public boolean update(Tournoi tournoi);
    public boolean delete(Long id);
    public boolean addEquipe(Long idTournoi, Long idEquipe);
    public boolean removeEquipe(Long idTournoi, Long idEquipe);

}
