package com.example.pokemon.service.impl;

import com.example.pokemon.dto.PokemonResponseDTO;
import com.example.pokemon.dto.TipoPokemonResponseDTO;
import com.example.pokemon.model.Entrenador;
import com.example.pokemon.repository.EntrenadorRepository;
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
    private EntrenadorRepository entrenadorRepository;

    @Override
    public List<PokemonResponseDTO> listarPokemonsPorEntrenador(String entrenadorUuid) {
        // 1. Validar la existencia del entrenador por su UUID
        Entrenador entrenador = entrenadorRepository.findByUuid(entrenadorUuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Entrenador con UUID " + entrenadorUuid + " no encontrado"));

        // 2. Mapear la lista de entidades al DTO personalizado de salida
        return entrenador.getPokemons().stream().map(pokemon -> {
            PokemonResponseDTO dto = new PokemonResponseDTO();
            dto.setId(String.valueOf(pokemon.getId()));
            dto.setNombre(pokemon.getNombre());
            dto.setDescripcion(pokemon.getDescripcion());
            dto.setFechaDescubrimiento(pokemon.getFechaDescubrimiento());
            dto.setGeneracion(pokemon.getGeneracion() != null ? String.valueOf(pokemon.getGeneracion()) : null);
            dto.setUuid(pokemon.getUuid());

            // Mapeo adaptativo para TipoPokemon
            if (pokemon.getTipoPokemon() != null) {
                String tipoId = String.valueOf(pokemon.getTipoPokemon().getId());
                String tipoDescripcion = pokemon.getTipoPokemon().getNombre(); // Mapea 'nombre' a 'descripcion'
                String tipoUuidMock = "123456-"; // Hardcodeado temporalmente dado que TipoPokemon no tiene UUID en tu entidad

                dto.setTipoPokemon(new TipoPokemonResponseDTO(tipoId, tipoDescripcion, tipoUuidMock));
            }

            return dto;
        }).collect(Collectors.toList());
    }
}