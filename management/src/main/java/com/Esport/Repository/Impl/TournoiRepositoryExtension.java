package com.Esport.Repository.Impl;

import com.Esport.Modele.Tournoi;
import com.Esport.Modele.Enum.JeuDifficulte;
import com.Esport.Repository.interfaces.TournoiRepository;

import java.time.Duration;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;






public class TournoiRepositoryExtension implements TournoiRepository  {
    
    private final TournoiRepositoryImpl tournoiDao;

    public TournoiRepositoryExtension(TournoiRepositoryImpl tournoiDao) {
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
    tournoi.setDureeEstimee(callCalculateDureeEstimee(tournoi));
    return tournoiDao.create(tournoi);
}

@Override
public boolean update(Tournoi tournoi) {
    tournoi.setDureeEstimee(callCalculateDureeEstimee(tournoi));
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
    int dureeMoyenneMatch =(int) tournoi.getJeu().getDureeMoyenneMatch().toMinutes(); 
    int difficultéJeu = Stream.of(JeuDifficulte.values())
        .filter(diff -> diff == tournoi.getJeu().getDifficulte())
        .mapToInt(diff -> diff.ordinal() + 1)
        .findFirst()
        .orElse(1); // Default to 1 (FACILE) if not found

    int tempsPauseEntreMatchs = (int) tournoi.getTempsPauseEntreMatchs().toMinutes();
    int tempsCeromonie = (int) tournoi.getTempsCeremonie().toMinutes();
    Duration dureeEstimee = Duration.ofMinutes((nombreEquipes * dureeMoyenneMatch * difficultéJeu) + tempsPauseEntreMatchs + tempsCeromonie);
        return dureeEstimee;
    }


}
