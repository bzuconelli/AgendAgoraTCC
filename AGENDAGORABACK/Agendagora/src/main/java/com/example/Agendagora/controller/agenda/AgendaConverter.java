package com.example.Agendagora.controller.agenda;
import com.example.Agendagora.controller.ordendeservico.OrdendeservicoDTO;
import com.example.Agendagora.controller.prestador.PrestadorDTO;
import com.example.Agendagora.model.agenda.AgendaEntity;
import com.example.Agendagora.model.prestador.PrestadorEntity;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;


@Component

public class AgendaConverter {
    public List<AgendaDTO> toDTO(List<AgendaEntity> entities) {
        List<AgendaDTO> agendaDTOS = new ArrayList<>();
        for (AgendaEntity entity : entities) {
            AgendaDTO dto = new AgendaDTO();
            dto.data=entity.data;
            dto.quantidade=entity.qtdVagas;
            agendaDTOS.add(dto);
        }
        return agendaDTOS;
    }
    public List<AgendaEntity> toEntity2(List<AgendaDTO> dtos) {
        List<AgendaEntity> agendaEntity = new ArrayList<>();
        for (AgendaDTO dto : dtos) {
            AgendaEntity agenda = new AgendaEntity();
            agenda.data= dto.data;
            agenda.qtdVagas=dto.quantidade;
            agendaEntity.add(agenda);
        }
        return agendaEntity;
    }



    public AgendaEntity toEntity(OrdendeservicoDTO dto, PrestadorEntity prestadorEntity) {
        prestadorEntity.id= dto.idprerst;
        return new AgendaEntity(dto.idagenda, dto.data, prestadorEntity);
    }

}
