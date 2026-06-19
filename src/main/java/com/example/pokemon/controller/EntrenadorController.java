package com.example.pokemon.controller;

import com.example.pokemon.dto.ErrorResponse;
import com.example.pokemon.model.Entrenador;
import com.example.pokemon.model.Pokemon;
import com.example.pokemon.repository.EntrenadorRepository;
import com.example.pokemon.repository.PokemonRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Entrenador Controller", description = "Endpoints para la gestión de entrenadores y sus Pokémon")
public class EntrenadorController {

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    @Autowired
    private PokemonRepository pokemonRepository;

    @Operation(summary = "Asociar un Pokémon a un entrenador", 
               description = "Agrega un Pokémon a la lista de Pokémon de un entrenador. Si ya está registrado, retorna un error.")
    @RequestMapping(
        value = "/entrenador/{entrenadorUuid}/pokemons/{pokemonUuid}",
        method = {RequestMethod.POST, RequestMethod.PUT}
    )
    public ResponseEntity<?> asociarPokemon(
            @PathVariable String entrenadorUuid,
            @PathVariable String pokemonUuid) {

        Optional<Entrenador> entrenadorOpt = entrenadorRepository.findByUuid(entrenadorUuid);
        if (entrenadorOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("true", "Entrenador con UUID " + entrenadorUuid + " no encontrado"));
        }

        Optional<Pokemon> pokemonOpt = pokemonRepository.findByUuid(pokemonUuid);
        if (pokemonOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("true", "Pokemon con UUID " + pokemonUuid + " no encontrado"));
        }

        Entrenador entrenador = entrenadorOpt.get();
        Pokemon pokemon = pokemonOpt.get();

        // Verificar si el pokemon ya está asociado
        boolean yaAsociado = entrenador.getPokemons().stream()
                .anyMatch(p -> p.getUuid().equals(pokemonUuid));

        if (yaAsociado) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("true", "pokemon ya esta registrado al entrenador"));
        }

        // Agregar el pokemon y guardar
        entrenador.getPokemons().add(pokemon);
        entrenadorRepository.save(entrenador);

        return ResponseEntity.ok(entrenador.getPokemons());
    }

    @Operation(summary = "Obtener todos los entrenadores")
    @GetMapping("/entrenadores")
    public List<Entrenador> obtenerEntrenadores() {
        return entrenadorRepository.findAll();
    }

    @Operation(summary = "Obtener todos los Pokémon")
    @GetMapping("/pokemons")
    public List<Pokemon> obtenerPokemons() {
        return pokemonRepository.findAll();
    }
    // Agrega esta inyección en los atributos de tu EntrenadorController
    @Autowired
    private com.example.pokemon.service.PokemonService pokemonService;

    // Agrega este nuevo método mapeado al endpoint solicitado
    @Operation(summary = "Listar los Pokémon de un entrenador por su UUID")
    @GetMapping("/entrenador/{uuid}/pokemons")
    public ResponseEntity<?> listarPokemonsDeEntrenador(@PathVariable String uuid) {
        try {
            List<PokemonResponseDTO> pokemons = pokemonService.listarPokemonsPorEntrenador(uuid);
            return ResponseEntity.ok(pokemons);
        } catch (org.springframework.web.server.ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode())
                    .body(new ErrorResponse("true", ex.getReason()));
        }
    }

    @Autowired
    private com.example.pokemon.service.EntrenadorService entrenadorService;

    @Operation(summary = "Obtener el UUID de un usuario/entrenador mediante su ID interno")
    @GetMapping("/entrenador/{id}/uuid")
    public ResponseEntity<?> obtenerUuidPorId(@PathVariable Integer id) {
        try {
            String uuid = entrenadorService.obtenerUuidPorId(id);
            return ResponseEntity.ok(new com.example.pokemon.dto.UuidResponseDTO(uuid));
        } catch (org.springframework.web.server.ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode())
                    .body(new ErrorResponse("true", ex.getReason()));
        }
    }
}
