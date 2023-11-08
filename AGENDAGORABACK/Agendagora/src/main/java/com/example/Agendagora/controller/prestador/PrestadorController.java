package com.example.Agendagora.controller.prestador;





import com.example.Agendagora.controller.contratante.ContratanteDTO;
import com.example.Agendagora.controller.enderco.EnderecoConverter;
import com.example.Agendagora.controller.usuario.UsuarioConverter;
import com.example.Agendagora.model.contratante.ContratanteDAO;
import com.example.Agendagora.model.contratante.ContratanteEntity;
import com.example.Agendagora.model.endereco.EnderecoEntity;
import com.example.Agendagora.model.endereco.EndrecoDAO;
import com.example.Agendagora.model.prestador.PrestadorDAO;
import com.example.Agendagora.model.prestador.PrestadorEntity;
import com.example.Agendagora.model.usuario.UsuarioDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@CrossOrigin("*")
@RequestMapping("/prestador/")
public class PrestadorController {
    @PostMapping()
    public ResponseEntity<HttpStatus> addconprestador(@RequestBody PrestadorDTO dto) throws SQLException {

        final EnderecoConverter converterE = new EnderecoConverter();
        final PrestadorConverter converterP = new PrestadorConverter();
        final UsuarioConverter converterU = new UsuarioConverter();
        if (!new UsuarioDAO().findbylogin(dto.login)) {
            EnderecoEntity enderecoEntity = new EndrecoDAO().addendereco(converterE.toEntityP(dto));
            PrestadorEntity prestadorEntity = new PrestadorDAO().addprestador(converterP.toEntity(dto, enderecoEntity));
            new UsuarioDAO().addlonginprest(converterU.toEntityP(dto, prestadorEntity));
            new PrestadorDAO().prestadorpresta(prestadorEntity.id, dto.idtiposervico);
            return ResponseEntity.ok().body(HttpStatus.CREATED);
        }
        return ResponseEntity.ok().body(HttpStatus.UNPROCESSABLE_ENTITY);
    }
}









