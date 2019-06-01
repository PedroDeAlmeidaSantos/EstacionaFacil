package br.senai.sp.estacionafacil.model;

import android.content.Intent;

import java.io.Serializable;

public class Preco implements Serializable {

    private int id;
    private Double valorPrimeiraHora;
    private Double valorDemaisHoras;
    private String dataFim;
    private int tolerancia;
    private String flag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getValorPrimeiraHora() {
        return valorPrimeiraHora;
    }

    public void setValorPrimeiraHora(Double valorPrimeiraHora) {
        this.valorPrimeiraHora = valorPrimeiraHora;
    }

    public Double getValorDemaisHoras() {
        return valorDemaisHoras;
    }

    public void setValorDemaisHoras(Double valorDemaisHoras) {
        this.valorDemaisHoras = valorDemaisHoras;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public int getTolerancia() {
        return tolerancia;
    }

    public void setTolerancia(int tolerancia) {
        this.tolerancia = tolerancia;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return
                "Primeira Hora: " + valorPrimeiraHora + " | " +
                "Demais Horas: " + valorDemaisHoras + " | " +
                flag;
    }
}
