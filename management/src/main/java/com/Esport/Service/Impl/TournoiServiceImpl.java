package com.Esport.Service.Impl;

import com.Esport.Repository.interfaces.TournoiRepository;
import com.Esport.Modele.Tournoi;
import com.Esport.Service.interfaces.TournoiService;
import java.util.List;
import java.util.Optional;

public class TournoiServiceImpl implements TournoiService {

    private TournoiRepository tournoiRepository;

    public TournoiServiceImpl(TournoiRepository tournoiRepository) {
        this.tournoiRepository = tournoiRepository;
    }

    @Override
    public List<Tournoi> findAll() {
        return tournoiRepository.findAll();
    }

    @Override
    public Optional<Tournoi> findById(Long id) {
        return tournoiRepository.findById(id);
    }

    @Override
    public boolean create(Tournoi tournoi) {
        return tournoiRepository.create(tournoi);
    }

    @Override
    public boolean update(Tournoi tournoi) {
        return tournoiRepository.update(tournoi);
    }

  

    @Override
    public boolean addEquipeToTournoi(Long tournoiId, Long equipeId) {
        return tournoiRepository.addEquipe(tournoiId, equipeId);
    }

    @Override
    public boolean removeEquipeFromTournoi(Long tournoiId, Long equipeId) {
        return tournoiRepository.removeEquipe(tournoiId, equipeId);
    }

}
