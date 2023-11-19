package com.example.Agendagora.model.ordendeservico;

import com.example.Agendagora.model.agenda.AgendaEntity;
import com.example.Agendagora.model.contratante.ContratanteEntity;


public class OrdendeservicoEntity {
    public int idos;
    public String status;
    public String descricao;
    public Double valor;
    public String observacao;
    public int idtiposervico;
    public AgendaEntity agenda;
    public ContratanteEntity contratanteEntity;
    public int nota;
    public String formapagamento;

    public OrdendeservicoEntity() {
    }

    public OrdendeservicoEntity(int idos, String descricao, String formapagamento, int idtiposervico, Double valor, String status, int nota, String observacao, ContratanteEntity contratanteEntity, AgendaEntity agendaEntity) {
        this.idos=idos;
        this.descricao=descricao;
        this.formapagamento=formapagamento;
        this.idtiposervico=idtiposervico;
        this.valor=valor;
        this.status=status;
        this.nota=nota;
        this.observacao= observacao;
        this.contratanteEntity=contratanteEntity;
        this.agenda=agendaEntity;

    }
}
