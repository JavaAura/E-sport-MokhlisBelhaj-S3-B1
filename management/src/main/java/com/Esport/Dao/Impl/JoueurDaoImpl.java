package com.Esport.Dao.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.Esport.Dao.interfaces.JoueurDao;
import com.Esport.Modele.Joueur;
import com.Esport.Util.LoggerUtil;
import com.Esport.Util.JpaUtil;

public class JoueurDaoImpl implements JoueurDao {

    public JoueurDaoImpl() {
    }

    @Override
    public List<Joueur> findAll() {
        EntityManager entityManager = JpaUtil.getEntityManager();
        try {
            List<Joueur> result = entityManager.createQuery("SELECT j FROM Joueur j", Joueur.class).getResultList();
            return result;
        } catch (Exception e) {
            LoggerUtil.error("Error in findAll: " + e.getMessage());
            return new ArrayList<>();
        } finally {
            JpaUtil.closeEntityManager(entityManager);
        }
    }

    @Override
    public Optional<Joueur> findById(Long id) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        try {
            Joueur joueur = entityManager.find(Joueur.class, id);
            return Optional.ofNullable(joueur);
        } catch (Exception e) {
            LoggerUtil.error("Error in findById: " + e.getMessage());
            return Optional.empty();
        } finally {
            JpaUtil.closeEntityManager(entityManager);
        }
    }

    @Override
    public boolean create(Joueur joueur) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(joueur);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            LoggerUtil.error("Error in create: " + e.getMessage());
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return false;
        } finally {
            JpaUtil.closeEntityManager(entityManager);
        }
    }

    @Override
    public boolean update(Joueur joueur) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(joueur);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            LoggerUtil.error("Error in update: " + e.getMessage());
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return false;
        } finally {
            JpaUtil.closeEntityManager(entityManager);
        }
    }

    @Override
    public boolean delete(Long id) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Joueur joueur = entityManager.find(Joueur.class, id);
            if (joueur != null) {
                entityManager.remove(joueur);
            }
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            LoggerUtil.error("Error in delete: " + e.getMessage());
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return false;
        } finally {
            JpaUtil.closeEntityManager(entityManager);
        }
    }
}
