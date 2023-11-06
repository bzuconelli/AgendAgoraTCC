package com.example.Agendagora.controller.prestador;




import com.example.Agendagora.controller.enderco.EnderecoDTO;
import com.example.Agendagora.model.prestador.PrestadorDAO;
import com.example.Agendagora.model.prestador.PrestadorEntity;
import com.example.Agendagora.model.usuario.UsuarioDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@CrossOrigin("*")
@RequestMapping("/prestador/")

public class PrestadorController {
    @PostMapping()
    public ResponseEntity<String> addprestador(@RequestBody CadprestadorDTO dto) throws SQLException {
         PrestadorDTO prestadorDTO = new PrestadorDTO();
         prestadorDTO.id= dto.id;
         prestadorDTO.cpf= dto.cpf;
         prestadorDTO.nome= dto.nome;
         prestadorDTO.sobrenome= dto.sobrenome;
         prestadorDTO.cnpj= dto.cnpj;
         prestadorDTO.telefone= dto.telefone;
         prestadorDTO.enderecoDTO= new EnderecoDTO();
         prestadorDTO.enderecoDTO.cidade= dto.cidade;
         prestadorDTO.enderecoDTO.bairo= dto.bairo;
         prestadorDTO.enderecoDTO.rua= dto.rua;
         prestadorDTO.enderecoDTO.numero= dto.numero;
         prestadorDTO.enderecoDTO.lat= dto.lat;
         prestadorDTO.enderecoDTO.lng= dto.lng;
         final PrestadorConverter converter = new PrestadorConverter();
         PrestadorEntity prestadorEntity= new PrestadorDAO().addprestador(dto,converter.toEntity( prestadorDTO));
         int funciona = new UsuarioDAO().addlogin(dto.login, dto.senha, prestadorEntity.id);
         if(funciona !=0){
              return ResponseEntity.ok().body("funfa");
         } else {
              return ResponseEntity.badRequest().body("não funcionaou ");
         }


    }
}





