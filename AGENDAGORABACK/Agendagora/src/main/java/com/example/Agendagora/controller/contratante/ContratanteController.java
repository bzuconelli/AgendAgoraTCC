package com.example.Agendagora.controller.contratante;



import com.example.Agendagora.controller.enderco.EnderecoConverter;
import com.example.Agendagora.controller.usuario.UsuarioConverter;
import com.example.Agendagora.model.contratante.ContratanteDAO;
import com.example.Agendagora.model.contratante.ContratanteEntity;
import com.example.Agendagora.model.endereco.EnderecoEntity;
import com.example.Agendagora.model.endereco.EndrecoDAO;
import com.example.Agendagora.model.usuario.UsuarioDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@CrossOrigin("*")
@RequestMapping("/contratante/")

public class ContratanteController {
    @PostMapping()
    public ResponseEntity<HttpStatus> addcontratante(@RequestBody ContratanteDTO dto) throws SQLException {

        final EnderecoConverter converterE = new EnderecoConverter();
        final ContratanteConverter converterC = new ContratanteConverter();
        final UsuarioConverter converterU = new UsuarioConverter();
        if(!new  UsuarioDAO().findbylogin(dto.login)){
        EnderecoEntity enderecoEntity =new EndrecoDAO().addendereco(converterE.toEntity(dto));
        ContratanteEntity contratanteEntity= new ContratanteDAO().addcontratante(converterC.toEntity(dto,enderecoEntity));
        new UsuarioDAO().addlongincont(converterU.toEntity(dto,contratanteEntity));
        return ResponseEntity.ok().body(HttpStatus.CREATED);
        }
        return ResponseEntity.ok().body(HttpStatus.UNPROCESSABLE_ENTITY);
    }
}





