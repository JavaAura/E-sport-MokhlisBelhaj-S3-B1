package com.Esport.Repository.Impl;

import com.Esport.Dao.interfaces.JeuDao;
import com.Esport.Repository.interfaces.JeuRepository;
import com.Esport.Modele.Jeu;

import java.util.List;
import java.util.Optional;

public class JeuRepositoryImpl implements JeuRepository {

    private final JeuDao jeuDao;

    public JeuRepositoryImpl(JeuDao jeuDao) {
        this.jeuDao = jeuDao;
    }

    @Override
    public List<Jeu> findAll() {
        return jeuDao.findAll();
    }

    @Override
    public Optional<Jeu> findById(Long id) {
        return jeuDao.findById(id);
    }

    @Override
    public boolean create(Jeu jeu) {
        return jeuDao.create(jeu);
    }

    @Override
    public boolean update(Jeu jeu) {
        return jeuDao.update(jeu);
    }

    



}
