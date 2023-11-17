package com.example.Agendagora.controller.prestador;


import com.example.Agendagora.model.endereco.EnderecoEntity;
import com.example.Agendagora.model.prestador.PrestadorEntity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Component

public class PrestadorConverter {

    public List<PrestadorDTO> toDTO(List<PrestadorEntity> entities) {
               List<PrestadorDTO> prestadorDTOS = new ArrayList<>();
               for (PrestadorEntity entity : entities) {
                   PrestadorDTO dto = new PrestadorDTO();
                   dto.id = entity.id;
                   dto.nome = entity.nome;
                   dto.sobrenome = entity.sobrenome;
                   dto.idendereco=entity.enderecoEntity.idendereco;
                   dto.lat=entity.enderecoEntity.lat;
                   dto.lng=entity.enderecoEntity.lng;
                   dto.idagenda=entity.idagenda;
                   dto.nota=entity.nota;
                   prestadorDTOS.add(dto);
               }
               return prestadorDTOS;
    }





    public PrestadorEntity toEntity(PrestadorDTO dto, EnderecoEntity enderecoEntity) {
        return new PrestadorEntity(dto.id,dto.nome, dto.sobrenome, dto.telefone, enderecoEntity, dto.recebecartao, dto.recebepix,dto.dinheiro );
    }

}
