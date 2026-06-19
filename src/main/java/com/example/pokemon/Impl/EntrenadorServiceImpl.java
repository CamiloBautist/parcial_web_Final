package com.example.pokemon.service.impl;

import com.example.pokemon.model.Entrenador;
import com.example.pokemon.repository.EntrenadorRepository;
import com.example.pokemon.service.EntrenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EntrenadorServiceImpl implements EntrenadorService {

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    @Override
    public String obtenerUuidPorId(Integer id) {
        // Buscamos al entrenador por su ID numérico; si no existe, lanzamos un 404
        Entrenador entrenador = entrenadorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuario/Entrenador con ID " + id + " no encontrado"));
        
        return entrenador.getUuid();
    }
}