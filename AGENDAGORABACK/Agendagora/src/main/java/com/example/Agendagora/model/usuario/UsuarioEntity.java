package com.example.Agendagora.model.usuario;


public class UsuarioEntity {

    public int id;
    public String login;
    public String senha;



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
