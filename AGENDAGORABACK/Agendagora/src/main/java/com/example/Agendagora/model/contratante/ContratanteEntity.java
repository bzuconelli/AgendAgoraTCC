package com.example.Agendagora.model.contratante;


import com.example.Agendagora.model.endereco.EnderecoEntity;

public class ContratanteEntity {

    public int id;
    public String nome;
    public String sobrenome;
    public String cpf;
    public String telefone;
    public EnderecoEntity enderecoEntity;






    public ContratanteEntity(int id, String nome, String sobrenome, String cpf, String telefone,EnderecoEntity entity) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.enderecoEntity= entity;

    }




    public ContratanteEntity() {
    }


}
