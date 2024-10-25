package com.Esport.Repository.Impl;

import com.Esport.Dao.Impl.JoueurDaoImpl;
import com.Esport.Dao.interfaces.JoueurDao;
import com.Esport.Modele.Joueur;
import com.Esport.Repository.interfaces.JoueurRepository;

import java.util.List;
import java.util.Optional;

public class JoueurRepositoryImpl implements JoueurRepository {

    private JoueurDao joueurDao;

    public JoueurRepositoryImpl(JoueurDaoImpl joueurDao) {
        this.joueurDao = joueurDao;
    }

    @Override
    public List<Joueur> findAll() {
        return joueurDao.findAll();
    }

    @Override
    public Optional<Joueur> findById(Long id) {
        return joueurDao.findById(id);
    }

    @Override
    public boolean create(Joueur joueur) {
        return joueurDao.create(joueur);
    }

    @Override
    public boolean update(Joueur joueur) {
        return joueurDao.update(joueur);
    }

    @Override
    public boolean delete(Long id) {
        return joueurDao.delete(id);
    }
}
