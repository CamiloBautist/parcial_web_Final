package com.example.pokemon.service.impl;

import com.example.pokemon.dto.PokemonResponseDTO;
import com.example.pokemon.dto.TipoPokemonResponseDTO;
import com.example.pokemon.model.Pokemon;
import com.example.pokemon.repository.PokemonRepository;
import com.example.pokemon.repository.TipoPokemonRepository;
import com.example.pokemon.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private TipoPokemonRepository tipoPokemonRepository;

    @Override
    public List<PokemonResponseDTO> listarPokemonsPorEntrenador(String entrenadorUuid) {
        // ... tu código existente para listar por entrenador
        return null; 
    }

    @Override
    public List<PokemonResponseDTO> listarPokemonsPorTipo(Integer tipoId) {
        // 1. Validar que el tipo de Pokémon exista en la base de datos
        tipoPokemonRepository.findById(tipoId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Tipo de Pokémon con ID " + tipoId + " no encontrado"));

        // 2. Recuperar todos los Pokémon vinculados a ese tipo
        List<Pokemon> pokemons = pokemonRepository.findByTipoPokemonId(tipoId);

        // 3. Transformar la lista de entidades al formato JSON DTO requerido
        return pokemons.stream().map(pokemon -> {
            PokemonResponseDTO dto = new PokemonResponseDTO();
            dto.setId(String.valueOf(pokemon.getId()));
            dto.setNombre(pokemon.getNombre());
            dto.setDescripcion(pokemon.getDescripcion());
            dto.setFechaDescubrimiento(pokemon.getFechaDescubrimiento());
            dto.setGeneracion(pokemon.getGeneracion() != null ? String.valueOf(pokemon.getGeneracion()) : null);
            dto.setUuid(pokemon.getUuid());

            if (pokemon.getTipoPokemon() != null) {
                dto.setTipoPokemon(new TipoPokemonResponseDTO(
                        String.valueOf(pokemon.getTipoPokemon().getId()),
                        pokemon.getTipoPokemon().getNombre(), // Mapea entidad 'nombre' -> JSON 'descripcion'
                        pokemon.getUuid().substring(0, Math.min(pokemon.getUuid().length(), 7)) + "-" // Mock UUID corto
                ));
            }

            return dto;
        }).collect(Collectors.toList());
    }
}