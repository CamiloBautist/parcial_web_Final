package com.example.pokemon.controller;

import com.example.pokemon.dto.ErrorResponse;
import com.example.pokemon.dto.PokemonResponseDTO;
import com.example.pokemon.service.PokemonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipo-pokemon")
@Tag(name = "Tipo Pokémon Controller", description = "Endpoints para la gestión y consultas por Tipo de Pokémon")
public class TipoPokemonController {

    @Autowired
    private PokemonService pokemonService;

    @Operation(summary = "Listar todos los Pokémon registrados que pertenecen a un tipo específico")
    @GetMapping("/{id}/pokemons")
    public ResponseEntity<?> obtenerPokemonsPorTipo(@PathVariable Integer id) {
        try {
            List<PokemonResponseDTO> pokemons = pokemonService.listarPokemonsPorTipo(id);
            return ResponseEntity.ok(pokemons);
        } catch (org.springframework.web.server.ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode())
                    .body(new ErrorResponse("true", ex.getReason()));
        }
    }
}