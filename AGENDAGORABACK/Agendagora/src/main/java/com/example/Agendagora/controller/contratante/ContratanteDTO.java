package com.example.Agendagora.controller.contratante;


public class ContratanteDTO {

    public int id;
    public String nome;
    public String sobrenome;

    public String telefone;
    public int idendereco;
    public String rua;
    public String cidade;
    public String bairo;
    public int numero;
    public String lat;
    public String lng;
    public String login;
    public String senha;





    public ContratanteDTO(int idendereco, String cidade, String rua, String bairo, int numero, String lat, String lng) {
    }

    public ContratanteDTO(int id, String nome, String sobrenome, String telefone, int idendereco, String rua, String cidade, String bairo, int numero, String lat, String lng, String login, String senha) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.idendereco = idendereco;
        this.rua = rua;
        this.cidade = cidade;
        this.bairo = bairo;
        this.numero = numero;
        this.lat = lat;
        this.lng = lng;
        this.login = login;
        this.senha = senha;
    }

    public ContratanteDTO() {
    }
}



