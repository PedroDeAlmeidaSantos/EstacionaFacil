package br.senai.sp.estacionafacil.model;

import java.io.Serializable;

public class Veiculo implements Serializable {

    private int id;
    private String placa;
    private String modelo;
    private String horarioEntrada;
    private String horarioSaida;
    private Integer tempo;
    private Double valorPago;

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

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getHorarioEntrada() {
        return horarioEntrada;
    }

    public void setHorarioEntrada(String horarioEntrada) {
        this.horarioEntrada = horarioEntrada;
    }

    public String getHorarioSaida() {
        return horarioSaida;
    }

    public void setHorarioSaida(String horarioSaida) {
        this.horarioSaida = horarioSaida;
    }

    public Integer getTempo() {
        return tempo;
    }

    public void setTempo(Integer tempo) {
        this.tempo = tempo;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    @Override
    public String toString() {
        return id + " PLACA: " + placa + " - MODELO: " + modelo;
    }
}
