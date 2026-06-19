package com.example.pokemon.controller;

import com.example.pokemon.dto.ErrorResponse;
import com.example.pokemon.dto.PokemonRequestDTO;
import com.example.pokemon.dto.PokemonResponseDTO;
import com.example.pokemon.service.PokemonRegistroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pokemons")
@Tag(name = "Pokémon Controller", description = "Endpoints para el registro y gestión global de Pokémon")
public class PokemonController {

    @Autowired
    private PokemonRegistroService pokemonRegistroService;

    @Operation(summary = "Registrar un nuevo Pokémon en el sistema")
    @PostMapping
    public ResponseEntity<?> registrarPokemon(@RequestBody PokemonRequestDTO requestDTO) {
        try {
            PokemonResponseDTO nuevoPokemon = pokemonRegistroService.registrarPokemon(requestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPokemon);
        } catch (org.springframework.web.server.ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode())
                    .body(new ErrorResponse("true", ex.getReason()));
        }
    }
}