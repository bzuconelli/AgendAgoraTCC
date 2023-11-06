package com.example.Agendagora.controller.prestador;


public class CadprestadorDTO {

    public int id;
    public String nome;
    public String sobrenome;
    public String cpf;
    public String cnpj;
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
    public String recebepix;
    public String dinheiro;
    public String recebecartao;
    public int idlogin;
    public  int idtiposervico;

    public CadprestadorDTO(int id, String nome, String sobrenome, String cpf, String cnpj, String telefone, int idendereco, String rua,
                           String cidade, String bairo,int numero, String lat, String lng, String login, String senha, String recebepix,
                           String dinheiro, String recebecartao, int idlogin,int idtiposervico) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.cnpj = cnpj;
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
        this.recebepix = recebepix;
        this.dinheiro = dinheiro;
        this.recebecartao = recebecartao;
        this.idlogin = idlogin;
        this.idtiposervico = idtiposervico;
    }

    public CadprestadorDTO() {
    }



}



