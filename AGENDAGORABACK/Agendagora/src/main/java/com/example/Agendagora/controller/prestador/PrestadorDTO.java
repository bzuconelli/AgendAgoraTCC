package com.example.Agendagora.controller.prestador;

import com.example.Agendagora.controller.enderco.EnderecoDTO;
import com.example.Agendagora.model.endereco.EnderecoEntity;

public class PrestadorDTO {
    public int id;
    public String nome;
    public String sobrenome;
    public String cpf;
    public String cnpj;
    public String telefone;
    public EnderecoDTO enderecoDTO;

    public PrestadorDTO(int id, String nome, String sobrenome, String cpf, String cnpj, String telefone, EnderecoDTO enderecoDTO) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.enderecoDTO = enderecoDTO;
    }


   

    public PrestadorDTO() {

    }

    public PrestadorDTO(int id, String nome, String sobrenome, String cpf, String cnpj, String telefone, int idendereco, String rua, String bairo, String cidade, int numero, String lat, String lng) {
    }
}
