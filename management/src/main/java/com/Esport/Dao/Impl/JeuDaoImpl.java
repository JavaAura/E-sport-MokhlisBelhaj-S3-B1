package com.Esport.Dao.Impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import com.Esport.Dao.interfaces.JeuDao;
import com.Esport.Modele.Jeu;
import com.Esport.Util.LoggerUtil;


public class JeuDaoImpl implements JeuDao {

    private EntityManager entityManager;
    public JeuDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Jeu> findAll() {
        try {
            return entityManager.createQuery("SELECT j FROM Jeu j", Jeu.class).getResultList();
        } catch (Exception e) {
            LoggerUtil.error("Error in findAll: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Optional<Jeu> findById(Long id) {
        try {
            return Optional.ofNullable(entityManager.find(Jeu.class, id));
        } catch (Exception e) {
            LoggerUtil.error("Error in findById: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public boolean create(Jeu jeu) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(jeu);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            LoggerUtil.error("Error in create: " + e.getMessage());
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return false;
        }
    }

    @Override
    public boolean update(Jeu jeu) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(jeu);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            LoggerUtil.error("Error in update: " + e.getMessage());
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            entityManager.getTransaction().begin();
            Jeu jeu = entityManager.find(Jeu.class, id);
            if (jeu != null) {
                entityManager.remove(jeu);
                entityManager.getTransaction().commit();
                return true;
            } else {
                entityManager.getTransaction().rollback();
                return false;
            }
        } catch (Exception e) {
            LoggerUtil.error("Error in delete: " + e.getMessage());
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return false;
        }
    }

    

}
