package com.Esport.Dao.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.Esport.Dao.interfaces.JoueurDao;
import com.Esport.Modele.Joueur;
import com.Esport.Util.LoggerUtil;

public class JoueurDaoImpl implements JoueurDao {

    private EntityManager entityManager;        
    public JoueurDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Joueur> findAll() {
        try {
            return entityManager.createQuery("SELECT j FROM Joueur j", Joueur.class).getResultList();
        } catch (Exception e) {
            LoggerUtil.error("Error in findAll: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Joueur> findById(Long id) {
        try {
            return Optional.ofNullable(entityManager.find(Joueur.class, id));
        } catch (Exception e) {
            LoggerUtil.error("Error in findById: " + e.getMessage());
            return Optional.empty();
        }
    }
    @Override
    public boolean create(Joueur joueur) {
        try {
            entityManager.persist(joueur);
            return true;
        } catch (Exception e) {
            LoggerUtil.error("Error in create: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Joueur joueur) {
        try {
            entityManager.merge(joueur);
            return true;
        } catch (Exception e) {
            LoggerUtil.error("Error in update: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            entityManager.remove(entityManager.find(Joueur.class, id));
            return true;
        } catch (Exception e) {
            LoggerUtil.error("Error in delete: " + e.getMessage());
            return false;
        }
    }
}
