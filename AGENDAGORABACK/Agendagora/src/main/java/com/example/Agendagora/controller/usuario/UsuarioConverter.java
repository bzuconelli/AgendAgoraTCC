package com.example.Agendagora.controller.usuario;


import com.example.Agendagora.controller.contratante.ContratanteDTO;
import com.example.Agendagora.model.usuario.UsuarioEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioConverter {

    public List<UsuarioDTO> toDTO(List<UsuarioEntity> entities) {
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
                .map(entity -> new UsuarioDTO(entity.login, entity.senha, entity.id)) //
                .collect(Collectors.toList());
    }

    public UsuarioDTO toDTO(UsuarioEntity entity) {
        return new UsuarioDTO(entity.login, entity.senha, entity.id);
    }
    public UsuarioEntity toEntity(com.example.Agendagora.controller.usuario.UsuarioDTO dto) {
        return new UsuarioEntity(dto.login, dto.senha,dto.id);

    }



}
