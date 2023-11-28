package com.example.Agendagora.controller.ordendeservico;

import com.example.Agendagora.model.agenda.AgendaEntity;
import com.example.Agendagora.model.contratante.ContratanteEntity;

public class OrdendeservicoDTO {
    public int idos;
    public String status;
    public String descricao;
    public Double valor;
    public String observacao;
    public int nota;
    public int idtiposervico;
    public int idagenda;
    public int idprerst;
    public int idcontratante;
    public String nomeo;
    public String sobrenomeo;
    public String data;
    public String formapagamento;
    public String nomec;
    public String sobrenomec;

    public String rua;
    public String cidade;
    public String bairo;
    public int numero;
    public String telefone;


    public OrdendeservicoDTO() {
    }

    public OrdendeservicoDTO(int idos, int idtiposervico, String descricao, String formapagamento, String status, String observacao, int nota, AgendaEntity agenda, ContratanteEntity contratanteEntity,  Double valor) {
        this.idos = idos;
        this.status = status;
        this.descricao = descricao;
        this.valor = valor;
        this.observacao = observacao;
        this.nota = nota;
        this.idtiposervico = idtiposervico;
        this.idagenda = agenda.idagenda;
        this.idprerst = agenda.prestadorEntity.id;
        this.nomeo = agenda.prestadorEntity.nome;
        this.sobrenomeo = agenda.prestadorEntity.sobrenome;
        this.data = agenda.data;
        this.formapagamento = formapagamento;
    }
    public OrdendeservicoDTO(int idos,ContratanteEntity contratante,AgendaEntity agenda, String descricao){
        this.idos=idos;
        this.nomec= contratante.nome;
        this.sobrenomec= contratante.sobrenome;
        this.telefone=contratante.telefone;
        this.rua=contratante.enderecoEntity.rua;
        this.cidade=contratante.enderecoEntity.cidade;
        this.bairo=contratante.enderecoEntity.bairo;
        this.numero=contratante.enderecoEntity.numero;
        this.data=agenda.data;
        this.descricao=descricao;

    }


}
