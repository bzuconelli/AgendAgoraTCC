package com.example.Agendagora.controller.agenda;


import com.example.Agendagora.controller.contratante.ContratanteDTO;
import com.example.Agendagora.controller.ordendeservico.OrdendeservicoDTO;
import com.example.Agendagora.model.agenda.AgendaEntity;
import com.example.Agendagora.model.contratante.ContratanteEntity;
import com.example.Agendagora.model.endereco.EnderecoEntity;
import com.example.Agendagora.model.prestador.PrestadorEntity;
import com.example.Agendagora.model.usuario.UsuarioEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component

public class AgendaConverter {


    public AgendaEntity toEntity(OrdendeservicoDTO dto, PrestadorEntity prestadorEntity) {
        prestadorEntity.id= dto.idprerst;
        return new AgendaEntity(dto.idagenda, dto.data, prestadorEntity);
    }

}
