package com.Esport.Dao.interfaces;

import java.util.List;
import java.util.Optional;

import com.Esport.Modele.Equipe;

public interface EquipeDao {

    public List<Equipe> findAll();
    public Optional<Equipe> findById(Long id);
    public boolean create(Equipe equipe);
    public boolean update(Equipe equipe);


}
