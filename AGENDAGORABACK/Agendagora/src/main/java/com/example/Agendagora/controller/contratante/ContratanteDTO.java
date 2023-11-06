package com.example.Agendagora.controller.contratante;


public class ContratanteDTO {

    public int id;
    public String nome;
    public String sobrenome;
    public String cpf;
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
    public int idlogin;



    public ContratanteDTO(int id, String nome, String sobrenome, String cpf, String telefone, int idendereco, String rua, String cidade, String bairo, int numero, String lat, String lng) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.idendereco = idendereco;
        this.rua = rua;
        this.cidade = cidade;
        this.bairo = bairo;
        this.numero = numero;
        this.lat = lat;
        this.lng = lng;
    }

    public ContratanteDTO() {
    }


    public ContratanteDTO(int id, String nome, String sobrenome, String telefone, String cpf, int idendereco, String rua, String bairo, String cidade, String lat, String lng, String login, String senha, int idlogin,int numero) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
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
        this.idlogin = idlogin;
    }
}



