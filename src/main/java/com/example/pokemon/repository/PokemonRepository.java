package com.example.pokemon.repository;

import com.example.pokemon.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {
    Optional<Pokemon> findByUuid(String uuid);
    
    List<Pokemon> findByTipoPokemonId(Integer tipoPokemonId);
}