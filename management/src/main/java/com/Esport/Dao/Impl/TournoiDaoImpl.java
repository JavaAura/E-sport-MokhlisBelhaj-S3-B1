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
            List<Tournoi> tournois = entityManager.createQuery(
                "SELECT DISTINCT t FROM Tournoi t LEFT JOIN FETCH t.equipes", 
                Tournoi.class)
                .getResultList();

            if (!tournois.isEmpty()) {
                List<Equipe> allEquipes = new ArrayList<>();
                for (Tournoi t : tournois) {
                    allEquipes.addAll(t.getEquipes());
                }
                if (!allEquipes.isEmpty()) {
                    entityManager.createQuery(
                        "SELECT e FROM Equipe e LEFT JOIN FETCH e.joueurs WHERE e IN :equipes",
                        Equipe.class)
                        .setParameter("equipes", allEquipes)
                        .getResultList();
                }
            }
            
            return tournois;
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
            Tournoi tournoi = entityManager.createQuery(
                "SELECT t FROM Tournoi t LEFT JOIN FETCH t.equipes WHERE t.id = :id", 
                Tournoi.class)
                .setParameter("id", id)
                .getSingleResult();

            if (tournoi != null) {
                entityManager.createQuery(
                    "SELECT e FROM Equipe e LEFT JOIN FETCH e.joueurs WHERE e IN :equipes",
                    Equipe.class)
                    .setParameter("equipes", tournoi.getEquipes())
                    .getResultList();
            }
            
            return Optional.ofNullable(tournoi);
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
}
