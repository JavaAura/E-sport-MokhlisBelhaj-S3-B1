package com.Esport.Dao.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

import com.Esport.Dao.interfaces.TournoiDao;
import com.Esport.Modele.Equipe;
import com.Esport.Modele.Tournoi;
import com.Esport.Util.LoggerUtil;

public class TournoiDaoImpl implements TournoiDao {

    private EntityManager entityManager;

    public TournoiDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Tournoi> findAll() {
        try {
            return entityManager.createQuery("SELECT t FROM Tournoi t", Tournoi.class).getResultList();
        } catch (Exception e) {
            LoggerUtil.error("Error finding all tournois: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Tournoi> findById(Long id) {
           try {
            return Optional.ofNullable(entityManager.find(Tournoi.class, id));
        } catch (Exception e) {
            LoggerUtil.error("Error finding tournoi by id: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public boolean create(Tournoi tournoi) {
        try {
            entityManager.persist(tournoi);
            return true;
        } catch (Exception e) {
            LoggerUtil.error("Error creating tournoi: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Tournoi tournoi) {
        try {
            entityManager.merge(tournoi);
            return true;
        } catch (Exception e) {
            LoggerUtil.error("Error updating tournoi: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        try {   
            Tournoi tournoi = entityManager.find(Tournoi.class, id);
            if (tournoi != null) {
                entityManager.remove(tournoi);
                return true;
            }
            return false;
        } catch (Exception e) {
            LoggerUtil.error("Error deleting tournoi: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean addEquipe(Long idTournoi, Long idEquipe) {
        try {
            Tournoi tournoi = entityManager.find(Tournoi.class, idTournoi);
            Equipe equipe = entityManager.find(Equipe.class, idEquipe);
            tournoi.getEquipes().add(equipe);
            return true;
        } catch (Exception e) {
            LoggerUtil.error("Error adding equipe to tournoi: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean removeEquipe(Long idTournoi, Long idEquipe) {
        try {
            Tournoi tournoi = entityManager.find(Tournoi.class, idTournoi);
            Equipe equipe = entityManager.find(Equipe.class, idEquipe);
            tournoi.getEquipes().remove(equipe);
            return true;
        } catch (Exception e) {
            LoggerUtil.error("Error removing equipe from tournoi: " + e.getMessage());
            return false;
        }
    }
}   
