package com.example.Agendagora.controller.usuario;

import com.example.Agendagora.controller.prestador.PrestadorDTO;

public class UsuarioDTO {

    public String login;
    public String senha;
    public int id;


    public UsuarioDTO(String usuario, String senha) {
        this.login = usuario;
        this.senha = senha;
    }

    public UsuarioDTO(String login, String senha,int id) {
        this.login = login;
        this.senha = senha;
        this.id= id;
    }



    public UsuarioDTO() {
    }



}
