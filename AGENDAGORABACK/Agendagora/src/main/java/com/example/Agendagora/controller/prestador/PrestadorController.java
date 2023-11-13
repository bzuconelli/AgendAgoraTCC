package com.example.Agendagora.controller.prestador;





import com.example.Agendagora.controller.contratante.ContratanteConverter;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@CrossOrigin("*")
@RequestMapping("/prestador/")
public class PrestadorController {
    @Autowired
    public PrestadorConverter prestadorConverter;
    @Autowired
    public UsuarioConverter usuarioConverter;
    @Autowired
    public EnderecoConverter enderecoConverter;
    @Autowired
    public EndrecoDAO endrecoDAO;
    @Autowired
    public PrestadorDAO prestadorDAO;
    @Autowired
    public UsuarioDAO usuarioDAO;

    @PostMapping()
    public ResponseEntity<HttpStatus> addconprestador(@RequestBody PrestadorDTO dto) throws SQLException {

        final EnderecoConverter converterE = enderecoConverter;
        final PrestadorConverter converterP = prestadorConverter;
        final UsuarioConverter converterU = usuarioConverter;
        if (!usuarioDAO.findbylogin(dto.login)) {
            EnderecoEntity enderecoEntity = endrecoDAO.addendereco(converterE.toEntityP(dto));
            PrestadorEntity prestadorEntity = prestadorDAO.addprestador(converterP.toEntity(dto, enderecoEntity));
            usuarioDAO.addlonginprest(converterU.toEntityP(dto, prestadorEntity));
            prestadorDAO.prestadorpresta(prestadorEntity.id, dto.idtiposervico);
            return ResponseEntity.ok().body(HttpStatus.CREATED);
        }
        return ResponseEntity.ok().body(HttpStatus.UNPROCESSABLE_ENTITY);
    }
}









