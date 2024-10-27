package com.Esport.Repository.Impl;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import com.Esport.Dao.Impl.TournoiDaoImpl;
import com.Esport.Dao.interfaces.TournoiDao;
import com.Esport.Modele.Tournoi;
import com.Esport.Repository.interfaces.TournoiRepository;
import com.Esport.Util.LoggerUtil;


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


  

    public Duration callCalculateDureeEstimee(Tournoi tournoi) {

        LoggerUtil.info("Calcule de base de la durée estimée   ");
    	int nombreEquipes;
        if (tournoi.getEquipes() == null) {
            nombreEquipes = 0;
        } else {
            nombreEquipes = tournoi.getEquipes().size();
        }

        int dureeMoyenneMatch = (int) tournoi.getJeu().getDureeMoyenneMatch().getSeconds();
        int tempsPauseEntreMatchs = (int) tournoi.getTempsPauseEntreMatchs().getSeconds();
        Duration dureeEstimee = Duration.ofSeconds((nombreEquipes * dureeMoyenneMatch) + tempsPauseEntreMatchs);
        return dureeEstimee;
    }
}
