package com.example.Agendagora.model.prestador;

import com.example.Agendagora.model.endereco.EnderecoEntity;

public class PrestadorEntity {
    public int id;
    public String nome;
    public String sobrenome;
    public String cpf;
    public String cnpj;
    public String telefone;
    public EnderecoEntity enderecoEntity;
    public String recebepix;
    public String dinheiro;
    public String recebecartao;
    public  int idtiposervico;

    public PrestadorEntity() {
    }

    public PrestadorEntity(int id, String nome, String sobrenome, String telefone, EnderecoEntity enderecoEntity, String recebecartao, String recebepix, String dinheiro) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.enderecoEntity = enderecoEntity;
        this.recebecartao = recebecartao;
        this.recebepix = recebepix;
        this.dinheiro = dinheiro;

    }
}
