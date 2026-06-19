package com.example.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class PokemonRequestDTO {
    private String nombre;
    private String descripcion;

    @JsonProperty("tipo_pokemon_id")
    private Integer tipoPokemonId;

    @JsonProperty("fecha_descubrimiento")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaDescubrimiento;

    private Integer generacion;

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Integer getTipoPokemonId() { return tipoPokemonId; }
    public void setTipoPokemonId(Integer tipoPokemonId) { this.tipoPokemonId = tipoPokemonId; }

    public LocalDate getFechaDescubrimiento() { return fechaDescubrimiento; }
    public void setFechaDescubrimiento(LocalDate fechaDescubrimiento) { this.fechaDescubrimiento = fechaDescubrimiento; }

    public Integer getGeneracion() { return generacion; }
    public void setGeneracion(Integer generacion) { this.generacion = generacion; }
}