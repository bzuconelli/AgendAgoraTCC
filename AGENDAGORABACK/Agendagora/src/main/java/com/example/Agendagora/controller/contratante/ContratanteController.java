package com.example.Agendagora.controller.contratante;



import com.example.Agendagora.controller.enderco.EnderecoConverter;
import com.example.Agendagora.controller.usuario.UsuarioConverter;
import com.example.Agendagora.model.contratante.ContratanteDAO;
import com.example.Agendagora.model.contratante.ContratanteEntity;
import com.example.Agendagora.model.endereco.EnderecoEntity;
import com.example.Agendagora.model.endereco.EndrecoDAO;
import com.example.Agendagora.model.usuario.UsuarioDAO;
import com.example.Agendagora.model.usuario.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@CrossOrigin("*")
@RequestMapping("/contratante/")

public class ContratanteController {
    @Autowired
    public ContratanteConverter contratanteConverter;
    @Autowired
    public UsuarioConverter usuarioConverter;
    @Autowired
    public EnderecoConverter enderecoConverter;
    @Autowired
    public EndrecoDAO endrecoDAO;
    @Autowired
    public ContratanteDAO contratanteDAO;
    @Autowired
    public UsuarioDAO usuarioDAO;

    @PostMapping()
    public ResponseEntity<ContratanteDTO> addcontratante(@RequestBody ContratanteDTO dto) throws SQLException {

        final EnderecoConverter converterE = enderecoConverter;
        final ContratanteConverter converterC = contratanteConverter;
        final UsuarioConverter converterU = usuarioConverter;
        if(!usuarioDAO.findbylogin(dto.login)){
            EnderecoEntity enderecoEntity =endrecoDAO.addendereco(converterE.toEntity(dto));
            ContratanteEntity contratanteEntity= contratanteDAO.addcontratante(converterC.toEntity(dto,enderecoEntity));
            usuarioDAO.addlongincont(converterU.toEntity(dto,contratanteEntity));
            ContratanteDTO dtoResponse = new ContratanteDTO();
            dtoResponse.id= contratanteEntity.id;
            dtoResponse.nome=contratanteEntity.nome;
            dtoResponse.sobrenome=contratanteEntity.sobrenome;
            dtoResponse.telefone=contratanteEntity.telefone;
            dtoResponse.idendereco= enderecoEntity.idendereco;
            return ResponseEntity.ok().body(dtoResponse);
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping()
    public ResponseEntity<ContratanteDTO>pesquisacontratante( @RequestHeader(HttpHeaders.AUTHORIZATION) String auth) throws SQLException {
        final ContratanteConverter converterC = contratanteConverter;
        int idusuario =usuarioDAO.existetoken(auth);
        boolean tipousuario= true;
        if (idusuario==0) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UsuarioEntity usuarioEntity= usuarioDAO.findbyid(idusuario,tipousuario);
        ContratanteEntity contratanteEntity= contratanteDAO.pesquisarporid(usuarioEntity);
        EnderecoEntity enderecoEntity= endrecoDAO.pesquisar(contratanteEntity.enderecoEntity.idendereco);


        ContratanteDTO dtoResponse = new ContratanteDTO();

        dtoResponse.login= usuarioEntity.login;
        dtoResponse.senha= usuarioEntity.senha;
        dtoResponse.id= contratanteEntity.id;
        dtoResponse.nome=contratanteEntity.nome;
        dtoResponse.sobrenome=contratanteEntity.sobrenome;
        dtoResponse.telefone=contratanteEntity.telefone;
        dtoResponse.idendereco= enderecoEntity.idendereco;
        dtoResponse.rua= enderecoEntity.rua;
        dtoResponse.cidade= enderecoEntity.cidade;
        dtoResponse.bairo= enderecoEntity.bairo;
        dtoResponse.numero= enderecoEntity.numero;
        dtoResponse.lat= enderecoEntity.lat;
        dtoResponse.lng= enderecoEntity.lng;
        return ResponseEntity.ok().body(dtoResponse);
    }
    @PutMapping("{id}")
    public ResponseEntity<ContratanteDTO>putcontratante(@RequestBody ContratanteDTO dto, @PathVariable int id,  @RequestHeader(HttpHeaders.AUTHORIZATION) String auth) throws SQLException {
        int idusuario =usuarioDAO.existetoken(auth);
        boolean tipousuario= true;
        if (idusuario==0) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        final EnderecoConverter converterE = enderecoConverter;
        final ContratanteConverter converterC = contratanteConverter;
        final UsuarioConverter converterU = usuarioConverter;
        EnderecoEntity enderecoEntity =endrecoDAO.updateendereco(converterE.toEntity(dto));
        if(enderecoEntity ==null){
            return ResponseEntity.notFound().build();
        }
        ContratanteEntity contratanteEntity=contratanteDAO.updatecontratante(id,(converterC.toEntity(dto,enderecoEntity)));
        if(contratanteEntity ==null){
            return ResponseEntity.notFound().build();
        }
        UsuarioEntity usuarioEntity = usuarioDAO.updateusuario(converterU.toEntity(dto,contratanteEntity),tipousuario);
        if(usuarioEntity ==null){
            return ResponseEntity.notFound().build();
        }
        ContratanteDTO dtoResponse = new ContratanteDTO();
        dtoResponse.nome =contratanteEntity.nome;
        dtoResponse.sobrenome =contratanteEntity.sobrenome;
        dtoResponse.telefone =contratanteEntity.telefone;
        dtoResponse.idendereco =enderecoEntity.idendereco;
        dtoResponse.rua =enderecoEntity.rua;
        dtoResponse.cidade =enderecoEntity.cidade;
        dtoResponse.bairo =enderecoEntity.bairo;
        dtoResponse.numero =enderecoEntity.numero;
        dtoResponse.lat =enderecoEntity.lat;
        dtoResponse.lng =enderecoEntity.lng;
        dtoResponse.login=usuarioEntity.login;
        dtoResponse.senha=usuarioEntity.senha;
        dtoResponse.id= contratanteEntity.id;
        return ResponseEntity.ok().body(dtoResponse);

    }
}





