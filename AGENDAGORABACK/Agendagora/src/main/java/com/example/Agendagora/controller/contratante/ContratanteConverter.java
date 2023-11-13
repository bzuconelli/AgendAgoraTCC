package com.example.Agendagora.controller.contratante;


import com.example.Agendagora.model.contratante.ContratanteEntity;
import com.example.Agendagora.model.endereco.EnderecoEntity;
import com.example.Agendagora.model.usuario.UsuarioEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component

public class ContratanteConverter {

    public List<ContratanteDTO> toDTO(List<UsuarioEntity> entities) {

       return entities //
             .stream() //
               .map(entity -> new ContratanteDTO(entity.contratante.id,entity.contratante.nome,entity.contratante.sobrenome,entity.contratante.telefone,entity.contratante.enderecoEntity.idendereco,
                       entity.contratante.enderecoEntity.rua, entity.contratante.enderecoEntity.cidade, entity.contratante.enderecoEntity.bairo, entity.contratante.enderecoEntity.numero,
                       entity.contratante.enderecoEntity.lat,  entity.contratante.enderecoEntity.lng,entity.login, entity.senha)) //
               .collect(Collectors.toList());
    }

   public ContratanteDTO toDTO(UsuarioEntity entity) {
       return new ContratanteDTO(entity.contratante.id,entity.contratante.nome,entity.contratante.sobrenome,entity.contratante.telefone,entity.contratante.enderecoEntity.idendereco,
               entity.contratante.enderecoEntity.rua, entity.contratante.enderecoEntity.cidade, entity.contratante.enderecoEntity.bairo, entity.contratante.enderecoEntity.numero,
               entity.contratante.enderecoEntity.lat,  entity.contratante.enderecoEntity.lng,entity.login, entity.senha);
   }

    public ContratanteEntity toEntity(ContratanteDTO dto, EnderecoEntity enderecoEntity) {
        return new ContratanteEntity(dto.id, dto.nome, dto.sobrenome, dto.telefone, enderecoEntity);
    }

}
