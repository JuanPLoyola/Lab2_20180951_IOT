package com.example.lab2_2018951.model;

import java.io.Serializable;

public class AccessPoint implements Serializable {
    private String marca;
    private String frecuencia; // "2.4 GHz", "5 GHz", "Dual Band"
    private int alcance;       // en metros
    private String estado;     // "Operativo", "En reparación", "Dado de baja"

    public AccessPoint(String marca, String frecuencia, int alcance, String estado) {
        this.marca = marca;
        this.frecuencia = frecuencia;
        this.alcance = alcance;
        this.estado = estado;
    }

    public String getMarca() { return marca; }
    public String getFrecuencia() { return frecuencia; }
    public int getAlcance() { return alcance; }
    public String getEstado() { return estado; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setFrecuencia(String frecuencia) { this.frecuencia = frecuencia; }
    public void setAlcance(int alcance) { this.alcance = alcance; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AccessPoint)) return false;
        AccessPoint other = (AccessPoint) o;
        // criterio simple de identificación
        return this.marca.equals(other.marca) && this.frecuencia.equals(other.frecuencia);
    }
}
