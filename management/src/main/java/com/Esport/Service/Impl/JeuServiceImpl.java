package com.Esport.Service.Impl;

import java.util.List;
import java.util.Optional;

import com.Esport.Repository.interfaces.JeuRepository;
import com.Esport.Modele.Jeu;
import com.Esport.Service.interfaces.JeuService;

public class JeuServiceImpl implements JeuService{

    private final JeuRepository jeuRepository;

    public JeuServiceImpl(JeuRepository jeuRepository) {
        this.jeuRepository = jeuRepository;
    }

    @Override
    public List<Jeu> findAll() {
        return jeuRepository.findAll();
    }

    @Override
    public Optional<Jeu> findById(Long id) {
        return jeuRepository.findById(id);
    }

    @Override
    public boolean create(Jeu jeu) {
        return jeuRepository.create(jeu);
    }

    @Override
    public boolean update(Jeu jeu) {
        return jeuRepository.update(jeu);
    }

    @Override
    public boolean delete(Long id) {
           return jeuRepository.delete(id);
    }

}

