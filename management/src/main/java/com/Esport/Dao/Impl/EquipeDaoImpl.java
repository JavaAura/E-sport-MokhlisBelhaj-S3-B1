package com.Esport.Dao.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.Esport.Dao.interfaces.EquipeDao;
import com.Esport.Modele.Equipe;
import com.Esport.Util.LoggerUtil;
import com.Esport.Util.JpaUtil;

public class EquipeDaoImpl implements EquipeDao {

    public EquipeDaoImpl() {
    }

    @Override
    public List<Equipe> findAll() {
        EntityManager entityManager = null;
        try {
            entityManager = JpaUtil.getEntityManager();
            return entityManager.createQuery(
                "SELECT DISTINCT e FROM Equipe e LEFT JOIN FETCH e.joueurs", 
                Equipe.class
            ).getResultList();
        } catch (Exception e) {
            LoggerUtil.error("Error in findAll: " + e.getMessage());
            return new ArrayList<>();
        } finally {
            JpaUtil.closeEntityManager(entityManager);
        }
    }

    @Override
    public Optional<Equipe> findById(Long id) {
        EntityManager entityManager = null;
        try {
            entityManager = JpaUtil.getEntityManager();
            // Utilisation d'une requête JPQL avec JOIN FETCH pour charger les joueurs
            String jpql = "SELECT e FROM Equipe e LEFT JOIN FETCH e.joueurs WHERE e.id = :id";
            Equipe equipe = entityManager.createQuery(jpql, Equipe.class)
                    .setParameter("id", id)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);
            return Optional.ofNullable(equipe);
        } catch (Exception e) {
            LoggerUtil.error("Error in findById: " + e.getMessage());
            return Optional.empty();
        } finally {
            JpaUtil.closeEntityManager(entityManager);
        }
    }

    @Override
    public boolean create(Equipe equipe) {
        EntityManager entityManager = null;
        try {
            entityManager = JpaUtil.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(equipe);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            LoggerUtil.error("Error in create: " + e.getMessage());
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return false;
        } finally {
            JpaUtil.closeEntityManager(entityManager);
        }
    }

    @Override
    public boolean update(Equipe equipe) {
        EntityManager entityManager = null;
        try {
            entityManager = JpaUtil.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(equipe);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            LoggerUtil.error("Error in update: " + e.getMessage());
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return false;
        } finally {
            JpaUtil.closeEntityManager(entityManager);
        }
    }

}
