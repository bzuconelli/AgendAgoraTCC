package com.example.Agendagora.model.usuario;


import com.example.Agendagora.model.contratante.ContratanteEntity;
import com.example.Agendagora.model.prestador.PrestadorEntity;

public class UsuarioEntity {

    public int id;
    public String login;
    public String senha;
    public ContratanteEntity contratante;
    public PrestadorEntity prestador;

    public UsuarioEntity(String usuario, String senha, ContratanteEntity contratante) {
        this.login = usuario;
        this.senha = senha;
        this.contratante = contratante;
    }
    public UsuarioEntity(String usuario, String senha, PrestadorEntity prestador) {
        this.login = usuario;
        this.senha = senha;
        this.prestador = prestador;
    }

    public UsuarioEntity(String usuario, String senha) {
        this.login = usuario;
        this.senha = senha;
    }

    public UsuarioEntity(String login, String senha,int id) {
        this.login = login;
        this.senha = senha;
        this.id = id;
    }

    public UsuarioEntity() {
    }


}
