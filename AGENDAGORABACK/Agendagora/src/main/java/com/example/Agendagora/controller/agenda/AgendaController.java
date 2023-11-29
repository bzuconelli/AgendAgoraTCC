package com.example.Agendagora.controller.agenda;

import com.example.Agendagora.controller.ordendeservico.OrdendeservicoDTO;
import com.example.Agendagora.model.usuario.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/agenda/")
public class AgendaController {
    @Autowired
    public UsuarioDAO usuarioDAO;
    @Autowired
    public AgendaConverter agendaConverter;
    @PostMapping()
    public ResponseEntity<List<AgendaDTO>>adicionardiastrabalhados(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody List<AgendaDTO> dto) throws SQLException {
        int idusuario =usuarioDAO.existetoken(auth);
        if (idusuario==0) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


}
