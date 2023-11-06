package com.example.Agendagora.controller.enderco;


import com.example.Agendagora.controller.contratante.ContratanteDTO;
import com.example.Agendagora.model.contratante.ContratanteEntity;
import com.example.Agendagora.model.endereco.EnderecoEntity;

import java.util.List;
import java.util.stream.Collectors;

public class EnderecoConverter {

    public List<EnderecoDTO> toDTO(List<EnderecoEntity> entities) {
        //        List<PessoaDTO> pessoasDto = new ArrayList<>();
        //        for (PessoaEntity entity : pessoasEntity) {
        //            PessoaDTO dto = new PessoaDTO();
        //            dto.id = entity.id;
        //            dto.nome = entity.nome;
        //            dto.idade = entity.idade;
        //            pessoasDto.add(dto);
        //        }

        return entities //
                .stream() //
                .map(entity -> new EnderecoDTO(entity.idendereco, entity.cidade, entity.rua,entity.bairo, entity.numero,entity.lat, entity.lng)) //
                .collect(Collectors.toList());
    }

    public EnderecoDTO toDTO(EnderecoEntity entity) {
        return new EnderecoDTO(entity.idendereco, entity.rua, entity.cidade,entity.bairo, entity.numero, entity.lat, entity.lng);
    }

    public EnderecoEntity toEntity(EnderecoDTO dto) {
        return new EnderecoEntity(dto.idendereco, dto.rua, dto.bairo, dto.cidade,dto.numero,dto.lat, dto.lng);
    }

}
