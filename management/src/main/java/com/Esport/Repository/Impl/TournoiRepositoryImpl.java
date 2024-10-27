package com.Esport.Repository.Impl;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import com.Esport.Dao.Impl.TournoiDaoImpl;
import com.Esport.Dao.interfaces.TournoiDao;
import com.Esport.Modele.Tournoi;
import com.Esport.Repository.interfaces.TournoiRepository;


public class TournoiRepositoryImpl implements TournoiRepository {

    private TournoiDao tournoiDao;
    

    public TournoiRepositoryImpl(TournoiDaoImpl tournoiDao) {
        this.tournoiDao = tournoiDao;
    }

    @Override
    public List<Tournoi> findAll() {
        return tournoiDao.findAll();
    }
    
    @Override
    public Optional<Tournoi> findById(Long id) {
        return tournoiDao.findById(id);
    }

    @Override
    public boolean create(Tournoi tournoi) {
        Duration dureeEstimee = callCalculateDureeEstimee(tournoi);
        tournoi.setDureeEstimee(dureeEstimee);
        return tournoiDao.create(tournoi);
    }


    @Override
    public boolean update(Tournoi tournoi) {
        Duration dureeEstimee = callCalculateDureeEstimee(tournoi);
        tournoi.setDureeEstimee(dureeEstimee);
        return tournoiDao.update(tournoi);
    }


    @Override
    public boolean addEquipe(Long idTournoi, Long idEquipe) {
        return tournoiDao.addEquipe(idTournoi, idEquipe);
    }

    @Override
    public boolean removeEquipe(Long idTournoi, Long idEquipe) {
        return tournoiDao.removeEquipe(idTournoi, idEquipe);
    }

    public Duration callCalculateDureeEstimee(Tournoi tournoi) {
        int nombreEquipes = tournoi.getEquipes().size();
        int dureeMoyenneMatch = (int) tournoi.getJeu().getDureeMoyenneMatch().toMinutes();
        int tempsPauseEntreMatchs = (int) tournoi.getTempsPauseEntreMatchs().toMinutes();
        Duration dureeEstimee = Duration.ofMinutes((nombreEquipes * dureeMoyenneMatch) + tempsPauseEntreMatchs);
        return dureeEstimee;
    }
}
