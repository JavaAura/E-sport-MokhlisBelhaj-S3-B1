package com.Esport.Service.Impl;

import java.util.List;
import java.util.Optional;

import com.Esport.Modele.Joueur;
import com.Esport.Repository.interfaces.JoueurRepository;
import com.Esport.Service.interfaces.JoueurService;

public class JoueurServiceImpl implements JoueurService {

    private JoueurRepository joueurRepository;

    public JoueurServiceImpl(JoueurRepository joueurRepository) {
        this.joueurRepository = joueurRepository;
    }

    @Override
    public List<Joueur> findAll() {
        return joueurRepository.findAll();
    }

    @Override
    public Optional<Joueur> findById(Long id) {
        return joueurRepository.findById(id);
    }

    @Override
    public boolean create(Joueur joueur) {
        return joueurRepository.create(joueur);
    }

    @Override
    public boolean update(Joueur joueur) {
        return joueurRepository.update(joueur);
    }

    @Override
    public boolean delete(Long id) {
        return joueurRepository.delete(id);
    }



}
