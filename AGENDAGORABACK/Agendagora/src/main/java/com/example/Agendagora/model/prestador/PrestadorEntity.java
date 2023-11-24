package com.example.Agendagora.model.prestador;

import com.example.Agendagora.model.endereco.EnderecoEntity;

public class PrestadorEntity {
    public int id;
    public String nome;
    public String sobrenome;

    public String telefone;
    public EnderecoEntity enderecoEntity;
    public String recebepix;
    public String dinheiro;
    public String recebecartao;
    public int nota;
    public int idagenda;
    public  int idservico;



    public PrestadorEntity() {
    }

    public PrestadorEntity(int id, String nome, String sobrenome, String telefone, EnderecoEntity enderecoEntity, String recebecartao, String recebepix, String dinheiro, int idservico) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.recebecartao = recebecartao;
        this.recebepix = recebepix;
        this.dinheiro = dinheiro;
        this.enderecoEntity = enderecoEntity;
        this.idservico= idservico;
    }


}
