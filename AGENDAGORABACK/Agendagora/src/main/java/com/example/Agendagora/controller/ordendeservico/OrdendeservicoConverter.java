package com.example.Agendagora.controller.ordendeservico;

import com.example.Agendagora.model.agenda.AgendaEntity;
import com.example.Agendagora.model.contratante.ContratanteEntity;
import com.example.Agendagora.model.ordendeservico.OrdendeservicoEntity;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrdendeservicoConverter {
    public List<OrdendeservicoDTO> toDTO(List<OrdendeservicoEntity> entities) {


        return entities //
                .stream() //
                .map(entity -> new OrdendeservicoDTO(entity.idos, entity.idtiposervico, entity.descricao,entity.formapagamento,entity.status,entity.observacao,entity.nota,entity.agenda,entity.contratanteEntity
                , entity.valor)) //
                .collect(Collectors.toList());
    }
    public List<OrdendeservicoDTO> toDTO1(List<OrdendeservicoEntity> entities) {
        return entities //
                .stream() //
                .map(entity -> new OrdendeservicoDTO(entity.idos,entity.contratanteEntity,entity.agenda, entity.descricao)) //
                .collect(Collectors.toList());
    }


    public OrdendeservicoDTO toDTO(OrdendeservicoEntity entity) {
        return new OrdendeservicoDTO(entity.idos, entity.idtiposervico, entity.descricao,entity.formapagamento,entity.status,entity.observacao,entity.nota,entity.agenda,entity.contratanteEntity
                , entity.valor);
    }


    public OrdendeservicoEntity toEntity(OrdendeservicoDTO dto, ContratanteEntity contratanteEntity,AgendaEntity agendaEntity) {
        return new OrdendeservicoEntity(dto.idos,dto.descricao,dto.formapagamento,dto.idtiposervico,dto.valor,dto.status,dto.nota,dto.observacao, contratanteEntity,agendaEntity);
    }
    public OrdendeservicoEntity toEntitya(OrdendeservicoDTO dto) {
        return new OrdendeservicoEntity(dto.nota,dto.observacao);
    }
}
