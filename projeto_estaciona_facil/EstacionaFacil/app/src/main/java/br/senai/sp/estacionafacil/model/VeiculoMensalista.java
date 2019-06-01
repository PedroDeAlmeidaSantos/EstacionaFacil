package br.senai.sp.estacionafacil.model;

import java.io.Serializable;

public class VeiculoMensalista implements Serializable{

    private int id;
    private String placa;
    private String idMensalista;
    private String modelo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getIdMensalista() {
        return idMensalista;
    }

    public void setIdMensalista(String idMensalista) {
        this.idMensalista = idMensalista;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "Placa: " + placa + " | Modelo: " + modelo;
    }
}
