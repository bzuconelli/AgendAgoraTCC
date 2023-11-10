package com.example.Agendagora.controller.prestador;


import com.example.Agendagora.controller.enderco.EnderecoDTO;
import com.example.Agendagora.model.endereco.EnderecoEntity;
import com.example.Agendagora.model.prestador.PrestadorEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component

public class PrestadorConverter {

   // public List<PrestadorDTO> toDTO(List<PrestadorEntity> entities) {
        //        List<PessoaDTO> pessoasDto = new ArrayList<>();
        //        for (PessoaEntity entity : pessoasEntity) {
        //            PessoaDTO dto = new PessoaDTO();
        //            dto.id = entity.id;
        //            dto.nome = entity.nome;
        //            dto.idade = entity.idade;
        //            pessoasDto.add(dto);
        //        }

     //   return entities //
          //      .stream() //
           //     .map(entity -> new  PrestadorDTO  (entity.id, entity.nome, entity.sobrenome,entity.cpf, entity.cnpj, entity.telefone,entity.enderecoEntity.idendereco,
                    //    entity.enderecoEntity.rua,entity.enderecoEntity.bairo,entity.enderecoEntity.cidade,entity.enderecoEntity.numero,entity.enderecoEntity.lat,entity.enderecoEntity.lng)) //
             //   .collect(Collectors.toList());
   // }

   // public PrestadorDTO toDTO(PrestadorEntity entity) {
      //  return new PrestadorDTO(entity.id, entity.nome, entity.sobrenome,entity.cpf, entity.cnpj, entity.telefone,entity.enderecoEntity.idendereco,
     //           entity.enderecoEntity.rua,entity.enderecoEntity.bairo,entity.enderecoEntity.cidade,entity.enderecoEntity.numero,entity.enderecoEntity.lat,entity.enderecoEntity.lng);
   // }

    public PrestadorEntity toEntity(PrestadorDTO dto, EnderecoEntity enderecoEntity) {
        return new PrestadorEntity(dto.id,dto.nome, dto.sobrenome, dto.telefone, enderecoEntity, dto.recebecartao, dto.recebepix,dto.dinheiro);
    }

}
