package com.example.Agendagora.controller.ordendeservico;

import com.example.Agendagora.model.agenda.AgendaEntity;
import com.example.Agendagora.model.contratante.ContratanteEntity;
import com.example.Agendagora.model.ordendeservico.OrdendeservicoEntity;

import org.springframework.stereotype.Component;

@Component
public class OrdendeservicoConverter {
    public OrdendeservicoEntity toEntity(OrdendeservicoDTO dto, ContratanteEntity contratanteEntity,AgendaEntity agendaEntity) {
        return new OrdendeservicoEntity(dto.idos,dto.descricao,dto.formapagamento,dto.idtiposervico,dto.valor,dto.status,dto.nota,dto.observacao, contratanteEntity,agendaEntity);
    }
}
