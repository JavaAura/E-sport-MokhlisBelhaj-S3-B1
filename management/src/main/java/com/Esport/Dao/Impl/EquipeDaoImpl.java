package com.Esport.Dao.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.Esport.Dao.interfaces.EquipeDao;
import com.Esport.Modele.Equipe;
import com.Esport.Util.JpaUtil;

public class EquipeDaoImpl implements EquipeDao {

    private EntityManager entityManager;

    public EquipeDaoImpl() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    @Override
    public List<Equipe> findAll() {
        try {
            return entityManager.createQuery("SELECT e FROM Equipe e", Equipe.class).getResultList();
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            // Return an empty list or throw a custom exception
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Equipe> findById(Long id) {
        try {
            return Optional.ofNullable(entityManager.find(Equipe.class, id));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public boolean create(Equipe equipe) {
        try {
            entityManager.persist(equipe);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Equipe equipe) {
        try {
            entityManager.merge(equipe);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            entityManager.remove(entityManager.find(Equipe.class, id));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    

}
