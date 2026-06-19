package com.example.pokemon.service;

import com.example.pokemon.dto.PokemonRequestDTO;
import com.example.pokemon.dto.PokemonResponseDTO;

public interface PokemonRegistroService {
    PokemonResponseDTO registrarPokemon(PokemonRequestDTO requestDTO);
}