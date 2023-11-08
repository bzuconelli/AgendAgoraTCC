package com.example.Agendagora.controller.contratante;


import com.example.Agendagora.model.contratante.ContratanteEntity;
import com.example.Agendagora.model.endereco.EnderecoEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ContratanteConverter {

   // public List<ContratanteDTO> toDTO(List<ContratanteEntity> entities) {
        //        List<PessoaDTO> pessoasDto = new ArrayList<>();
        //        for (PessoaEntity entity : pessoasEntity) {
        //            PessoaDTO dto = new PessoaDTO();
        //            dto.id = entity.id;
        //            dto.nome = entity.nome;
        //            dto.idade = entity.idade;
        //            pessoasDto.add(dto);
        //        }

      //  return entities //
            //    .stream() //
              //  .map(entity -> new ContratanteDTO(entity.id, entity.nome, entity.sobrenome,entity.telefone, entity.cpf,entity.enderecoEntity)) //
             //   .collect(Collectors.toList());
   // }

  //  public ContratanteDTO toDTO(ContratanteEntity entity) {
     //   return new ContratanteDTO(entity.id, entity.nome, entity.sobrenome,entity.cpf, entity.telefone,entity.enderecoEntity);
   // }

    public ContratanteEntity toEntity(ContratanteDTO dto, EnderecoEntity enderecoEntity) {
        return new ContratanteEntity(dto.id, dto.nome, dto.sobrenome,dto.cpf, dto.telefone, enderecoEntity);
    }

}
