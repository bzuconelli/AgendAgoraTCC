package com.example.Agendagora.controller.agenda;

public class AgendaDTO {
    String data;
    int quantidade;
    int mes;
    int ano;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public int getAno() {
        return Integer.parseInt(data.split("-")[0]);
    }

    public int getMes() {
        return Integer.parseInt(data.split("-")[1]);
    }

    public AgendaDTO() {

    }

    public AgendaDTO(int mes, int ano) {
        this.mes = mes;
        this.ano = ano;
    }
}
