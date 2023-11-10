package com.example.Agendagora.controller.usuario;

import com.example.Agendagora.model.usuario.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.UUID;
@RestController
@CrossOrigin("*")
@RequestMapping("/login/")
public class UsuarioController {
    @Autowired
    public UsuarioDAO usuarioDAO;
    @PostMapping()
    public ResponseEntity<String> usuarioexiste(@RequestBody UsuarioDTO dto) throws SQLException {
        String tipousuario;

        final int usuarioexiste = usuarioDAO.pesquisar(dto.login, dto.senha);
        if (usuarioexiste == -1) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else {
            String token = UUID.randomUUID().toString();
            usuarioDAO.adicionarnatabela(usuarioexiste, token);
            boolean prestador = usuarioDAO.eprestador(usuarioexiste);
            if (prestador) {
                tipousuario = "prestador";

            } else {
                tipousuario = "contratante";
            }
            return ResponseEntity.ok().body(token+"|"+ tipousuario);

        }
    }


}
