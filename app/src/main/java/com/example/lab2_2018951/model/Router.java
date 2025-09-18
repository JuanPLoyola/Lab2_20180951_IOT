package com.example.lab2_2018951.model;

import java.io.Serializable;

public class Router implements Serializable {
    private String marca;
    private String modelo;
    private String velocidad;
    private String estado;

    public Router(String marca, String modelo, String velocidad, String estado) {
        this.marca = marca;
        this.modelo = modelo;
        this.velocidad = velocidad;
        this.estado = estado;
    }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getVelocidad() { return velocidad; }
    public void setVelocidad(String velocidad) { this.velocidad = velocidad; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }


}
