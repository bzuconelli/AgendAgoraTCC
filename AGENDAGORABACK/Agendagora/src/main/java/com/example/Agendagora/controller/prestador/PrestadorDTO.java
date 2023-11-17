package com.example.Agendagora.controller.prestador;

import com.example.Agendagora.controller.enderco.EnderecoDTO;
import com.example.Agendagora.model.endereco.EnderecoEntity;

public class PrestadorDTO {
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
    public String recebepix;
    public String dinheiro;
    public String recebecartao;
    public  int idtiposervico;
    public int nota;
    public int idagenda;
    public PrestadorDTO(int id, String nome, String sobrenome, String telefone, int idendereco, String rua, String cidade, String bairo, int numero, String lat, String lng, String login, String senha, String recebepix, String dinheiro, String recebecartao, int idtiposervico, int nota, int idagenda) {
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
        this.recebepix = recebepix;
        this.dinheiro = dinheiro;
        this.recebecartao = recebecartao;
        this.idtiposervico = idtiposervico;
        this.nota = nota;
        this.idagenda = idagenda;
    }

    public PrestadorDTO() {
    }

    public PrestadorDTO(int idendereco, String cidade, String rua, String bairo, int numero, String lat, String lng) {
    }



    public PrestadorDTO(int id, String nome, String sobrenome, String telefone, int idendereco, String rua, String cidade, String bairo, int numero, String lat, String lng, String login, String senha, String dinheiro, String recebepix, String recebecartao, int nota, int idagenda) {
    }




    public PrestadorDTO(int id, String nome, String sobrenome, String telefone, int idendereco, String rua, String cidade, String bairo, int numero, String lat, String lng, String dinheiro, String recebepix, String recebecartao, int nota, int idagenda) {
    }


}
