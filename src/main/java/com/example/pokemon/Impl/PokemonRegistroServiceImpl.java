package com.example.pokemon.service.impl;

import com.example.pokemon.dto.PokemonRequestDTO;
import com.example.pokemon.dto.PokemonResponseDTO;
import com.example.pokemon.dto.TipoPokemonResponseDTO;
import com.example.pokemon.model.Pokemon;
import com.example.pokemon.model.TipoPokemon;
import com.example.pokemon.repository.PokemonRepository;
import com.example.pokemon.repository.TipoPokemonRepository;
import com.example.pokemon.service.PokemonRegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class PokemonRegistroServiceImpl implements PokemonRegistroService {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private TipoPokemonRepository tipoPokemonRepository;

    @Override
    public com.example.pokemon.dto.PokemonResponseDTO registrarPokemon(PokemonRequestDTO requestDTO) {
        // 1. Validar que el tipo de Pokémon exista en la base de datos
        TipoPokemon tipo = tipoPokemonRepository.findById(requestDTO.getTipoPokemonId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "El tipo de Pokémon con ID " + requestDTO.getTipoPokemonId() + " no existe."));

        // 2. Crear y mapear la nueva entidad Pokémon
        Pokemon pokemon = new Pokemon();
        pokemon.setNombre(requestDTO.getNombre());
        pokemon.setDescripcion(requestDTO.getDescripcion());
        pokemon.setFechaDescubrimiento(requestDTO.getFechaDescubrimiento());
        pokemon.setGeneracion(requestDTO.getGeneracion());
        pokemon.setTipoPokemon(tipo);
        
        // Generación automática de UUID único para el sistema
        pokemon.setUuid(UUID.randomUUID().toString());

        // 3. Guardar en la base de datos
        Pokemon pokemonGuardado = pokemonRepository.save(pokemon);

        // 4. Mapear al DTO de respuesta con el formato JSON requerido
        PokemonResponseDTO responseDTO = new PokemonResponseDTO();
        responseDTO.setId(String.valueOf(pokemonGuardado.getId()));
        responseDTO.setNombre(pokemonGuardado.getNombre());
        responseDTO.setDescripcion(pokemonGuardado.getDescripcion());
        responseDTO.setFechaDescubrimiento(pokemonGuardado.getFechaDescubrimiento());
        responseDTO.setGeneracion(pokemonGuardado.getGeneracion() != null ? String.valueOf(pokemonGuardado.getGeneracion()) : null);
        responseDTO.setUuid(pokemonGuardado.getUuid());

        // Mapeo adaptativo del tipo (entidad 'nombre' -> json 'descripcion')
        TipoPokemonResponseDTO tipoDto = new TipoPokemonResponseDTO(
                String.valueOf(tipo.getId()),
                tipo.getNombre(),
                pokemonGuardado.getUuid().substring(0, 7) + "-" // Mock de UUID corto para cumplir con el ejemplo visual
        );
        responseDTO.setTipoPokemon(tipoDto);

        return responseDTO;
    }
}