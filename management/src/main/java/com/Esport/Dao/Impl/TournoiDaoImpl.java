package com.Esport.Dao.Impl;

import com.Esport.Dao.interfaces.TournoiDao;
import com.Esport.Modele.Equipe;
import com.Esport.Modele.Tournoi;
import com.Esport.Util.LoggerUtil;
import com.Esport.Util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TournoiDaoImpl implements TournoiDao {

    public TournoiDaoImpl() {
    }

    @Override
    public List<Tournoi> findAll() {
        EntityManager entityManager = JpaUtil.getEntityManager();
        try {
            return entityManager.createQuery("SELECT t FROM Tournoi t", Tournoi.class).getResultList();
        } catch (Exception e) {
            LoggerUtil.error("Error finding all tournois: " + e.getMessage());
            return new ArrayList<>();
        } finally {
            JpaUtil.closeEntityManager(entityManager);
        }
    }

    @Override
    public Optional<Tournoi> findById(Long id) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        try {
            return Optional.ofNullable(entityManager.find(Tournoi.class, id));
        } catch (Exception e) {
            LoggerUtil.error("Error finding tournoi by id: " + e.getMessage());
            return Optional.empty();
        } finally {
            JpaUtil.closeEntityManager(entityManager);
        }
    }

    @Override
    public boolean create(Tournoi tournoi) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(tournoi);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            LoggerUtil.error("Error creating tournoi: " + e.getMessage());
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return false;
        } finally {
            JpaUtil.closeEntityManager(entityManager);
        }
    }

    @Override
    public boolean update(Tournoi tournoi) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(tournoi);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            LoggerUtil.error("Error updating tournoi: " + e.getMessage());
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return false;
        } finally {
            JpaUtil.closeEntityManager(entityManager);
        }
    }

   

    @Override
    public boolean addEquipe(Long idTournoi, Long idEquipe) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        try {
            Tournoi tournoi = entityManager.find(Tournoi.class, idTournoi);
            Equipe equipe = entityManager.find(Equipe.class, idEquipe);
            tournoi.getEquipes().add(equipe);
            return true;
        } catch (Exception e) {
            LoggerUtil.error("Error adding equipe to tournoi: " + e.getMessage());
            return false;
        } finally {
            JpaUtil.closeEntityManager(entityManager);
        }
    }

    @Override
    public boolean removeEquipe(Long idTournoi, Long idEquipe) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        try {
            Tournoi tournoi = entityManager.find(Tournoi.class, idTournoi);
            Equipe equipe = entityManager.find(Equipe.class, idEquipe);
            tournoi.getEquipes().remove(equipe);
            return true;
        } catch (Exception e) {
            LoggerUtil.error("Error removing equipe from tournoi: " + e.getMessage());
            return false;
        } finally {
            JpaUtil.closeEntityManager(entityManager);
        }
    }
}
