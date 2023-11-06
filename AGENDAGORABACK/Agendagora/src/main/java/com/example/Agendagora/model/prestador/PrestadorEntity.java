package com.example.Agendagora.model.prestador;

import com.example.Agendagora.controller.enderco.EnderecoDTO;
import com.example.Agendagora.model.endereco.EnderecoEntity;

public class PrestadorEntity {
    public int id;
    public String nome;
    public String sobrenome;
    public String cpf;
    public String cnpj;
    public String telefone;
    public EnderecoEntity enderecoEntity;

    public PrestadorEntity(int id, String nome, String sobrenome, String cpf, String cnpj, String telefone, EnderecoEntity enderecoEntity) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.enderecoEntity = enderecoEntity;
    }

    public PrestadorEntity() {
    }

    public PrestadorEntity(int id, String nome, String nome1, String sobrenome, String cpf, String cnpj, String telefone, int idendereco, String rua, String bairo, String cidade, int numero, String lat, String lng) {
    }
}
