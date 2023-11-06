package com.example.Agendagora.controller.contratante;



import com.example.Agendagora.model.contratante.ContratanteDAO;
import com.example.Agendagora.model.contratante.ContratanteEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@CrossOrigin("*")
@RequestMapping("/contratante/")

public class ContratanteController {
    @PostMapping()
    public ResponseEntity<ContratanteDTO> addcontratante(@RequestBody ContratanteDTO dto) throws SQLException {
        final ContratanteConverter converter = new ContratanteConverter();

        return ResponseEntity.ok().body(converter.toDTO(new ContratanteDAO().addcontratante(converter.toEntity(dto))));


    }
}





