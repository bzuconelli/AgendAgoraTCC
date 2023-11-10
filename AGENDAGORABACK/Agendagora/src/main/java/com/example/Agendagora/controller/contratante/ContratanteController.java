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
            int idusuario =usuarioDAO.existetoken(auth);
        if (idusuario==0) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        boolean contrantante =true;
        ContratanteEntity contratanteEntity = contratanteDAO.pesquisarporid(idusuario);
        EnderecoEntity enderecoEntity= endrecoDAO.pesquisar(contratanteEntity.id);
        UsuarioEntity usuarioEntity= usuarioDAO.findbyid(contratanteEntity.id,contrantante);


    }
}





