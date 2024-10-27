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
            return entityManager.createQuery("SELECT e FROM Equipe e", Equipe.class).getResultList();
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
            return Optional.ofNullable(entityManager.find(Equipe.class, id));
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

    @Override
    public boolean delete(Long id) {
        EntityManager entityManager = null;
        try {
            entityManager = JpaUtil.getEntityManager();
            entityManager.getTransaction().begin();
            Equipe equipe = entityManager.find(Equipe.class, id);
            if (equipe != null) {
                entityManager.remove(equipe);
            }
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            LoggerUtil.error("Error in delete: " + e.getMessage());
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return false;
        } finally {
            JpaUtil.closeEntityManager(entityManager);
        }
    }
}
