package com.example.Agendagora.controller.usuario;

import com.example.Agendagora.controller.login.LoginDTO;
import com.example.Agendagora.model.usuario.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
    public ResponseEntity<LoginDTO> usuarioexiste(@RequestBody UsuarioDTO dto) throws SQLException {

        final int usuarioexiste = usuarioDAO.pesquisar(dto.login, dto.senha);
        if (usuarioexiste == -1) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else {
            String token = UUID.randomUUID().toString();
            usuarioDAO.adicionarnatabela(usuarioexiste, token);
            boolean prestador = usuarioDAO.eprestador(usuarioexiste);
            LoginDTO loginDTO=new LoginDTO();
            loginDTO.token=token;
            if (prestador) {

                loginDTO.niveldeacesso = "prestador";

            } else {
                loginDTO.niveldeacesso = "contratante";
            }
            return ResponseEntity.ok().body(loginDTO);

        }
    }
    @DeleteMapping()
    public ResponseEntity<LoginDTO> excluirtoken(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) throws SQLException {
        int idusuario =usuarioDAO.existetoken(auth);
        if (idusuario==0) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Integer tokenfoiexcluido = usuarioDAO.deletetoken(auth);
        if (tokenfoiexcluido==0){
            return ResponseEntity.notFound().build();
        }
        LoginDTO responseDTO =new LoginDTO();
        responseDTO.token=auth;
        return ResponseEntity.ok().body(responseDTO);
    }

}
