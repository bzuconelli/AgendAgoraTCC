package com.example.Agendagora.controller.ordendeservico;

import com.example.Agendagora.controller.agenda.AgendaConverter;
import com.example.Agendagora.model.agenda.AgendaEntity;
import com.example.Agendagora.model.contratante.ContratanteEntity;
import com.example.Agendagora.model.ordendeservico.OrdendeservicoDAO;
import com.example.Agendagora.model.ordendeservico.OrdendeservicoEntity;
import com.example.Agendagora.model.prestador.PrestadorEntity;
import com.example.Agendagora.model.usuario.UsuarioDAO;
import com.example.Agendagora.model.usuario.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/ordendeservico/")
public class OrdendeservicoController {
    @Autowired
    public UsuarioDAO usuarioDAO;
    @Autowired
    public OrdendeservicoDAO ordendeservicoDAO;
    @Autowired
    public AgendaConverter agendaConverter;
    @Autowired
    public OrdendeservicoConverter ordendeservicoConverter;

    @PostMapping()
    public ResponseEntity<OrdendeservicoDTO> addos(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody OrdendeservicoDTO dto) throws SQLException {
        int idusuario =usuarioDAO.existetoken(auth);
        if (idusuario==0) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        final AgendaConverter convertera =agendaConverter;
        final OrdendeservicoConverter converteros =ordendeservicoConverter;

        PrestadorEntity prestadorEntity=new PrestadorEntity();
        prestadorEntity.id=dto.idprerst;
        ContratanteEntity contratanteEntity=new ContratanteEntity();
        contratanteEntity.id=dto.idcontratante;
        AgendaEntity agendaEntity = convertera.toEntity(dto,prestadorEntity);
        OrdendeservicoEntity ordendeservicoEntity =ordendeservicoDAO.addos(converteros.toEntity(dto,contratanteEntity,agendaEntity));
        if (ordendeservicoEntity==null){
            return ResponseEntity.badRequest().build();
        }
        OrdendeservicoDTO dtoResponse = new OrdendeservicoDTO();
        dtoResponse.idos=ordendeservicoEntity.idos;
        dtoResponse.idtiposervico= ordendeservicoEntity.idtiposervico;
        dtoResponse.formapagamento= ordendeservicoEntity.formapagamento;
        dtoResponse.idcontratante= ordendeservicoEntity.contratanteEntity.id;
        dtoResponse.idagenda=ordendeservicoEntity.agenda.idagenda;
        dtoResponse.descricao=ordendeservicoEntity.descricao;
        return ResponseEntity.ok().body(dtoResponse);
    }
    @GetMapping
    public ResponseEntity<List<OrdendeservicoDTO>> pesquisaroscontratante(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) throws SQLException {
        boolean tipousuario= true;
        final OrdendeservicoConverter converteros =ordendeservicoConverter;
        int idusuario =usuarioDAO.existetoken(auth);
        if (idusuario==0) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UsuarioEntity usuarioEntity= usuarioDAO.findbyid(idusuario,tipousuario);
        List<OrdendeservicoEntity> ordendeservicoEntityList= ordendeservicoDAO.findbyidcotratante(usuarioEntity.contratante.id);
        return ResponseEntity.ok(converteros.toDTO(ordendeservicoEntityList));

    }
    @DeleteMapping("{id}")
    public ResponseEntity<OrdendeservicoDTO> cancelarc(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth,@PathVariable int id) throws SQLException {

        int idusuario =usuarioDAO.existetoken(auth);
        if (idusuario==0) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        OrdendeservicoEntity entity=ordendeservicoDAO.delete(id);
        if(entity ==null){
            return ResponseEntity.notFound().build();
        }
        OrdendeservicoDTO responseDTO =new OrdendeservicoDTO();
        responseDTO.idos=entity.idos;
        responseDTO.status= entity.status;
        responseDTO.descricao=entity.descricao;
        responseDTO.formapagamento= entity.formapagamento;
        return ResponseEntity.ok(responseDTO);
    }



}