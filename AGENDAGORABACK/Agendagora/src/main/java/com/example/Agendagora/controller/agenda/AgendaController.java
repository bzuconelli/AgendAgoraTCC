package com.example.Agendagora.controller.agenda;


import com.example.Agendagora.model.agenda.AgendaDAO;
import com.example.Agendagora.model.agenda.AgendaEntity;
import com.example.Agendagora.model.usuario.UsuarioDAO;
import com.example.Agendagora.model.usuario.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/agenda/")
public class AgendaController {

    @Autowired
    public UsuarioDAO usuarioDAO;
    @Autowired
    public AgendaDAO agendaDAO;
    @Autowired
    public AgendaConverter agendaConverter;
    @PostMapping()
    public ResponseEntity<List<AgendaDTO>>adicionardiastrabalhados(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody List<AgendaDTO> dto) throws SQLException {
        int idusuario =usuarioDAO.existetoken(auth);
        boolean tipousuario = false;
         if (idusuario==0) {
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
         }
         UsuarioEntity usuarioEntity= usuarioDAO.findbyid(idusuario,tipousuario);
         List<AgendaEntity> agendaEntityList= agendaDAO.adicionardiastrabalhados(agendaConverter.toEntity2(dto),usuarioEntity.prestador.id);
         return ResponseEntity.ok(agendaConverter.toDTO(agendaEntityList));
    }
    @GetMapping()
    public ResponseEntity<List<AgendaDTO>>pesquisar(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth,@RequestParam (value = "mes",required = false)  int mes,@RequestParam (value = "ano",required = false)  int ano) throws SQLException {
        int idusuario =usuarioDAO.existetoken(auth);
        boolean tipousuario = false;
        if (idusuario==0) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UsuarioEntity usuarioEntity= usuarioDAO.findbyid(idusuario,tipousuario);
        List<AgendaEntity> agendaEntityList= agendaDAO.pesquisardiastrabalhados( mes,usuarioEntity.prestador.id,ano);
        return ResponseEntity.ok(agendaConverter.toDTO(agendaEntityList));
    }
    @PutMapping()
    public ResponseEntity<List<AgendaDTO>>editar(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody List<AgendaDTO>dtos, @RequestParam (value = "mes",required = false)  int mes,@RequestParam (value = "ano",required = false)  int ano) throws SQLException {
        int idusuario =usuarioDAO.existetoken(auth);
        boolean tipousuario = false;
        if (idusuario==0) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UsuarioEntity usuarioEntity= usuarioDAO.findbyid(idusuario,tipousuario);
        List<AgendaEntity> agendaEntityList= agendaDAO.editardiastrabalhados( agendaConverter.toEntity2(dtos),mes,usuarioEntity.prestador.id,ano);
        return ResponseEntity.ok(agendaConverter.toDTO(agendaEntityList));

    }

}
