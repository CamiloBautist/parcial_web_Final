package com.example.pokemon.service;

import com.example.pokemon.dto.PokemonResponseDTO;
import java.util.List;

public interface PokemonService {
    List<PokemonResponseDTO> listarPokemonsPorEntrenador(String entrenadorUuid);

    List<PokemonResponseDTO> listarPokemonsPorTipo(Integer tipoId);
}