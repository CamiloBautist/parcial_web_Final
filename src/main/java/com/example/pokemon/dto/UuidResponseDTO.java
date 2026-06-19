package com.example.pokemon.dto;

public class UuidResponseDTO {
    private String uuid;

    public UuidResponseDTO(String uuid) {
        this.uuid = uuid;
    }

    // Getter y Setter
    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }
}