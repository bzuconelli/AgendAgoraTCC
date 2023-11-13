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
    public ResponseEntity<HttpStatus> addcontratante(@RequestBody ContratanteDTO dto) throws SQLException {

        final EnderecoConverter converterE = enderecoConverter;
        final ContratanteConverter converterC = contratanteConverter;
        final UsuarioConverter converterU = usuarioConverter;
        if(!usuarioDAO.findbylogin(dto.login)){
        EnderecoEntity enderecoEntity =endrecoDAO.addendereco(converterE.toEntity(dto));
        ContratanteEntity contratanteEntity= contratanteDAO.addcontratante(converterC.toEntity(dto,enderecoEntity));
        usuarioDAO.addlongincont(converterU.toEntity(dto,contratanteEntity));
        return ResponseEntity.ok().body(HttpStatus.CREATED);
        }
        return ResponseEntity.ok().body(HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @GetMapping("pequisar")
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
        UsuarioEntity usuario =new UsuarioEntity();
        usuario.id= usuarioEntity.id;
        usuario.login= usuarioEntity.login;
        usuario.senha= usuarioEntity.senha;
        usuario.contratante= new ContratanteEntity();
        usuario.contratante.id= contratanteEntity.id;
        usuario.contratante.nome=contratanteEntity.nome;
        usuario.contratante.sobrenome=contratanteEntity.sobrenome;
        usuario.contratante.telefone=contratanteEntity.telefone;
        usuario.contratante.enderecoEntity= new EnderecoEntity();
        usuario.contratante.enderecoEntity.idendereco= enderecoEntity.idendereco;
        usuario.contratante.enderecoEntity.rua= enderecoEntity.rua;
        usuario.contratante.enderecoEntity.cidade= enderecoEntity.cidade;
        usuario.contratante.enderecoEntity.bairo= enderecoEntity.bairo;
        usuario.contratante.enderecoEntity.numero= enderecoEntity.numero;
        usuario.contratante.enderecoEntity.lat= enderecoEntity.lat;
        usuario.contratante.enderecoEntity.lng= enderecoEntity.lng;
        return ResponseEntity.ok().body(converterC.toDTO(usuario));
    }
    @PutMapping("put/{id}")
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
        usuarioEntity.contratante.nome =contratanteEntity.nome;
        usuarioEntity.contratante.sobrenome =contratanteEntity.sobrenome;
        usuarioEntity.contratante.telefone =contratanteEntity.telefone;
        usuarioEntity.contratante.enderecoEntity.rua =enderecoEntity.rua;
        usuarioEntity.contratante.enderecoEntity.cidade =enderecoEntity.cidade;
        usuarioEntity.contratante.enderecoEntity.bairo =enderecoEntity.bairo;
        usuarioEntity.contratante.enderecoEntity.numero =enderecoEntity.numero;
        usuarioEntity.contratante.enderecoEntity.lat =enderecoEntity.lat;
        usuarioEntity.contratante.enderecoEntity.lng =enderecoEntity.lng;
        return ResponseEntity.ok().body(converterC.toDTO(usuarioEntity));

    }
}





