package com.example.lab2_2018951.model;

import java.io.Serializable;

public class SwitchDevice implements Serializable {
    private String marca;
    private String modelo;
    private int puertos;          // cantidad de puertos
    private String tipo;          // Administrable / No Administrable
    private String estado;        // Operativo / En reparación / Dado de baja

    public SwitchDevice(String marca, String modelo, int puertos, String tipo, String estado) {
        this.marca = marca;
        this.modelo = modelo;
        this.puertos = puertos;
        this.tipo = tipo;
        this.estado = estado;
    }

    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public int getPuertos() { return puertos; }
    public String getTipo() { return tipo; }
    public String getEstado() { return estado; }

    public void setMarca(String marca) { this.marca = marca; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setPuertos(int puertos) { this.puertos = puertos; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SwitchDevice)) return false;
        SwitchDevice other = (SwitchDevice) o;
        // criterio simple para identificarlo en la lista
        return this.marca.equals(other.marca) && this.modelo.equals(other.modelo);
    }
}
