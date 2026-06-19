package com.example.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class PokemonResponseDTO {
    private String id;
    private String nombre;
    private String descripcion;

    @JsonProperty("fecha_descubrimiento")
    @JsonFormat(pattern = "yyyy/MM/dd") // Transforma la fecha al formato con diagonales
    private LocalDate fechaDescubrimiento;

    private String generacion;
    private String uuid;

    @JsonProperty("tipo_pokemon")
    private TipoPokemonResponseDTO tipoPokemon;

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDate getFechaDescubrimiento() { return fechaDescubrimiento; }
    public void setFechaDescubrimiento(LocalDate fechaDescubrimiento) { this.fechaDescubrimiento = fechaDescubrimiento; }

    public String getGeneracion() { return generacion; }
    public void setGeneracion(String generacion) { this.generacion = generacion; }

    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }

    public TipoPokemonResponseDTO getTipoPokemon() { return tipoPokemon; }
    public void setTipoPokemon(TipoPokemonResponseDTO tipoPokemon) { this.tipoPokemon = tipoPokemon; }
    
}