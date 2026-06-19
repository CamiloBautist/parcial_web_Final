package com.example.pokemon.dto;

public class TipoPokemonResponseDTO {
    private String id;
    private String descripcion;
    private String uuid;

    public TipoPokemonResponseDTO(String id, String descripcion, String uuid) {
        this.id = id;
        this.descripcion = descripcion;
        this.uuid = uuid;
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }

    
}