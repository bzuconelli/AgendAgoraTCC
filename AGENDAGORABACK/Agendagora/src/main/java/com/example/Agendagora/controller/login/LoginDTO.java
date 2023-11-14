package com.example.Agendagora.controller.login;

public class LoginDTO {

    public String token;

    public String niveldeacesso;

    public LoginDTO(String token, String niveldeacesso) {
        this.token = token;
        this.niveldeacesso = niveldeacesso;
    }

    public LoginDTO() {
    }
}
