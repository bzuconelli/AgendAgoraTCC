package com.example.Agendagora.controller.usuario;

import com.example.Agendagora.model.usuario.UsuarioDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.UUID;
@RestController
@CrossOrigin("*")
@RequestMapping("/login/")
public class UsuarioController {
    @PostMapping()
    public ResponseEntity<String> usuarioexiste(@RequestBody UsuarioDTO dto) throws SQLException {
        String tipousuario;

        final int usuarioexiste = new UsuarioDAO().pesquisar(dto.login, dto.senha);
        if (usuarioexiste == -1) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else {
            String token = UUID.randomUUID().toString();
            new UsuarioDAO().adicionarnatabela(usuarioexiste, token);
            boolean prestador = new UsuarioDAO().eprestador(usuarioexiste);
            if (prestador) {
                tipousuario = "prestador";

            } else {
                tipousuario = "contratante";
            }
            return ResponseEntity.ok().body(token + tipousuario);

        }
    }

    @PostMapping("addcontratante")
    public ResponseEntity<String> addcontratante(@RequestBody UsuarioDTO dto) throws SQLException {


        return ResponseEntity.ok().body("deu boa");
    }
}
