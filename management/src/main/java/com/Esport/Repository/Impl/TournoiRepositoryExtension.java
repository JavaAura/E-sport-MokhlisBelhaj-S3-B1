package com.Esport.Repository.Impl;

import com.Esport.Dao.Impl.TournoiDaoImpl;
import com.Esport.Dao.interfaces.TournoiDao;
import com.Esport.Modele.Tournoi;
import com.Esport.Modele.Enum.JeuDifficulte;
import com.Esport.Repository.interfaces.TournoiRepository;
import com.Esport.Util.LoggerUtil;

import java.time.Duration;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;






public class TournoiRepositoryExtension implements TournoiRepository  {
    
	  private TournoiDao tournoiDao;
	    

	    public TournoiRepositoryExtension(TournoiDaoImpl tournoiDao) {
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
    tournoi.setDureeEstimee(callCalculateDureeEstimee(tournoi));
    return tournoiDao.update(tournoi);
}





public Duration callCalculateDureeEstimee(Tournoi tournoi) {
    LoggerUtil.info("Calcule de la durée estimée avancée");
    int nombreEquipes;
    if (tournoi.getEquipes() == null) {
        nombreEquipes = 0;
    } else {
        nombreEquipes = tournoi.getEquipes().size();
    }

    int dureeMoyenneMatch =(int) tournoi.getJeu().getDureeMoyenneMatch().toMinutes(); 
    int difficultéJeu = Stream.of(JeuDifficulte.values())
        .filter(diff -> diff == tournoi.getJeu().getDifficulte())
        .mapToInt(diff -> diff.ordinal() + 1)
        .findFirst()
        .orElse(1); 

    int tempsPauseEntreMatchs = (int) tournoi.getTempsPauseEntreMatchs().getSeconds();
    int tempsCeromonie = (int) tournoi.getTempsCeremonie().getSeconds();
    Duration dureeEstimee = Duration.ofSeconds((nombreEquipes * dureeMoyenneMatch * difficultéJeu) + tempsPauseEntreMatchs + tempsCeromonie);
        return dureeEstimee;
    }


}
