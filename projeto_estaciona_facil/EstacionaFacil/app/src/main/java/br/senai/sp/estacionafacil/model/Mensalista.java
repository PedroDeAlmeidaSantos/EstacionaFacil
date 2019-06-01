package br.senai.sp.estacionafacil.model;

import java.io.Serializable;

public class Mensalista implements Serializable{

    private int id;
    private String nome;
    private String cpf;
    private String celular;
    private int quantidadeVagas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public int getQuantidadeVagas() {
        return quantidadeVagas;
    }

    public void setQuantidadeVagas(int quantidadeVagas) {
        this.quantidadeVagas = quantidadeVagas;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " | "  + " Vagas: " + quantidadeVagas;
    }

}
