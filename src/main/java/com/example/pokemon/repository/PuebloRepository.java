package com.example.pokemon.repository;

import com.example.pokemon.model.Pueblo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuebloRepository extends JpaRepository<Pueblo, Integer> {
}
