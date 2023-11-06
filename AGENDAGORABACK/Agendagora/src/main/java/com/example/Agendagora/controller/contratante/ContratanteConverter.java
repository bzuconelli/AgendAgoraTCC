package com.example.Agendagora.controller.contratante;


import com.example.Agendagora.model.contratante.ContratanteEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ContratanteConverter {

    public List<ContratanteDTO> toDTO(List<ContratanteEntity> entities) {
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
                .map(entity -> new ContratanteDTO(entity.id, entity.nome, entity.sobrenome,entity.telefone, entity.cpf,entity.idendereco, entity.rua, entity.bairo, entity.cidade, entity.lat, entity.lng, entity.login, entity.senha, entity.idlogin,entity.numero)) //
                .collect(Collectors.toList());
    }

    public ContratanteDTO toDTO(ContratanteEntity entity) {
        return new ContratanteDTO(entity.id, entity.nome, entity.sobrenome,entity.cpf, entity.telefone,entity.idendereco, entity.rua, entity.bairo, entity.cidade, entity.lat, entity.lng,entity.login, entity.senha, entity.idlogin,entity.numero);
    }

    public ContratanteEntity toEntity(ContratanteDTO dto) {
        return new ContratanteEntity(dto.id, dto.nome, dto.sobrenome,dto.cpf, dto.telefone, dto.idendereco, dto.rua, dto.bairo, dto.cidade,dto.numero,dto.lat, dto.lng,dto.login,dto.senha,dto.idlogin);
    }

}
