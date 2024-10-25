package com.Esport.Repository.Impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.Esport.Dao.Impl.EquipeDaoImpl;
import com.Esport.Dao.interfaces.EquipeDao;
import com.Esport.Modele.Equipe;
import com.Esport.Modele.Joueur;
import com.Esport.Repository.interfaces.EquipeRepository;
import com.Esport.Util.LoggerUtil;

public class EquipeRepositoryImpl implements EquipeRepository {

    private EntityManager entityManager;
    private EquipeDao equipeDao;

    public EquipeRepositoryImpl(EntityManager entityManager, EquipeDaoImpl equipeDao) {
        this.entityManager = entityManager;
        this.equipeDao = equipeDao;
    }

    @Override
    public List<Equipe> findAll() {
        return equipeDao.findAll();
    }

    @Override
    public Optional<Equipe> findById(Long id) {
        return equipeDao.findById(id);
    }

    @Override
    public boolean create(Equipe equipe) {
        return equipeDao.create(equipe);
    }

    @Override
    public boolean update(Equipe equipe) {
        return equipeDao.update(equipe);
    }

    @Override
    public boolean delete(Long id) {
        return equipeDao.delete(id);
    }

    @Override
    public boolean addJoueurToEquipe(Long equipeId, Long joueurId) {
        try {
            entityManager.getTransaction().begin();
            Equipe equipe = entityManager.find(Equipe.class, equipeId);
            Joueur joueur = entityManager.find(Joueur.class, joueurId);
            equipe.getJoueurs().add(joueur);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            LoggerUtil.error("Error adding joueur to equipe: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean removeJoueurFromEquipe(Long equipeId, Long joueurId) {
        try {
            entityManager.getTransaction().begin();
            Equipe equipe = entityManager.find(Equipe.class, equipeId);
            Joueur joueur = entityManager.find(Joueur.class, joueurId);
            equipe.getJoueurs().remove(joueur);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            LoggerUtil.error("Error removing joueur from equipe: " + e.getMessage());
            return false;
        }
    }
    


}
