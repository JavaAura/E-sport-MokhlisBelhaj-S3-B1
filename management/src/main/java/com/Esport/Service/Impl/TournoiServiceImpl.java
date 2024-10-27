package com.Esport.Service.Impl;

import com.Esport.Repository.interfaces.EquipeRepository;
import com.Esport.Repository.interfaces.TournoiRepository;
import com.Esport.Modele.Equipe;
import com.Esport.Modele.Tournoi;
import com.Esport.Service.interfaces.TournoiService;
import com.Esport.Util.LoggerUtil;

import java.util.List;
import java.util.Optional;

public class TournoiServiceImpl implements TournoiService {

    private TournoiRepository tournoiRepository;
    private EquipeRepository equipeRepository;
    public TournoiServiceImpl(TournoiRepository tournoiRepository, EquipeRepository equipeRepository) {
        this.tournoiRepository = tournoiRepository;
        this.equipeRepository = equipeRepository;
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
        try {
            if (tournoiId == null || equipeId == null) {
                throw new IllegalArgumentException("TournoiId and EquipeId cannot be null");
            }

            Optional<Tournoi> tournoiOpt = tournoiRepository.findById(tournoiId);
            if (!tournoiOpt.isPresent()) {
                LoggerUtil.error("Tournoi with ID " + tournoiId + " not found");
                return false;
            }

            Optional<Equipe> equipeOpt = equipeRepository.findById(equipeId);
            if (!equipeOpt.isPresent()) {
                LoggerUtil.error("Equipe with ID " + equipeId + " not found");
                return false;
            }

            equipeOpt.get().setTournoi(tournoiOpt.get());
            boolean result = equipeRepository.update(equipeOpt.get());
            if (result) {
                Optional<Tournoi> tournoi = tournoiRepository.findById(tournoiId);
                if (tournoi.isPresent()) {
                    tournoiRepository.update(tournoi.get());
                }
            }else{
                LoggerUtil.error("Error adding equipe to tournoi");
                return false;
            }
            return result;
        } catch (IllegalArgumentException e) {
            LoggerUtil.error("Invalid arguments: " + e.getMessage());
            return false;
        } catch (Exception e) {
            LoggerUtil.error("Error adding equipe to tournoi: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean removeEquipeFromTournoi(Long tournoiId, Long equipeId) {
        try {
            if (tournoiId == null || equipeId == null) {
                throw new IllegalArgumentException("TournoiId and EquipeId cannot be null");
            }

            Optional<Tournoi> tournoiOpt = tournoiRepository.findById(tournoiId);
            if (!tournoiOpt.isPresent()) {
                LoggerUtil.error("Tournoi with ID " + tournoiId + " not found");
                return false;
            }

            Optional<Equipe> equipeOpt = equipeRepository.findById(equipeId);
            if (!equipeOpt.isPresent()) {
                LoggerUtil.error("Equipe with ID " + equipeId + " not found");
                return false;
            }

            equipeOpt.get().setTournoi(null);
            boolean result = equipeRepository.update(equipeOpt.get());
            if (result) {
                Optional<Tournoi> tournoi = tournoiRepository.findById(tournoiId);
                if (tournoi.isPresent()) {
                    tournoiRepository.update(tournoi.get());
                }
            }
            return result;
        } catch (IllegalArgumentException e) {
            LoggerUtil.error("Invalid arguments: " + e.getMessage());
            return false;
        } catch (Exception e) {
            LoggerUtil.error("Error removing equipe from tournoi: " + e.getMessage());
            return false;
        }
    }

}
