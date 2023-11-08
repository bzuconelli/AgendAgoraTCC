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
    public int idlogin;
    public  int idtiposervico;

    public PrestadorDTO() {
    }

    public PrestadorDTO(int idendereco, String cidade, String rua, String bairo, int numero, String lat, String lng) {
    }
}
