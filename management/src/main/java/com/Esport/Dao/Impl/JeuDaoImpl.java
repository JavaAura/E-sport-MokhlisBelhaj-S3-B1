package com.Esport.Dao.Impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import com.Esport.Dao.interfaces.JeuDao;
import com.Esport.Modele.Jeu;
import com.Esport.Util.JpaUtil;
import com.Esport.Util.LoggerUtil;


public class JeuDaoImpl implements JeuDao {

    public JeuDaoImpl() {
    }

    @Override
    public List<Jeu> findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("SELECT j FROM Jeu j", Jeu.class).getResultList();
        } catch (Exception e) {
            LoggerUtil.error("Error in findAll: " + e.getMessage());
            return null;
        } finally {
            JpaUtil.closeEntityManager(em);
        }
    }

    @Override
    public Optional<Jeu> findById(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return Optional.ofNullable(em.find(Jeu.class, id));
        } catch (Exception e) {
            LoggerUtil.error("Error in findById: " + e.getMessage());
            return Optional.empty();
        } finally {
            JpaUtil.closeEntityManager(em);
        }
    }

    @Override
    public boolean create(Jeu jeu) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(jeu);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            LoggerUtil.error("Error creating Jeu: " + e.getMessage());
            return false;
        } finally {
            JpaUtil.closeEntityManager(em);
        }
    }

    @Override
    public boolean update(Jeu jeu) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(jeu);
            tx.commit();
            return true;
        } catch (Exception e) {
            LoggerUtil.error("Error in update: " + e.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            JpaUtil.closeEntityManager(em);
        }
    }

  

    

}
