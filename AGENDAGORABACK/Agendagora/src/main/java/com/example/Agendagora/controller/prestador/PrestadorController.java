package com.example.Agendagora.controller.prestador;
import com.example.Agendagora.controller.enderco.EnderecoConverter;
import com.example.Agendagora.controller.usuario.UsuarioConverter;
import com.example.Agendagora.model.endereco.EnderecoEntity;
import com.example.Agendagora.model.endereco.EndrecoDAO;
import com.example.Agendagora.model.prestador.PrestadorDAO;
import com.example.Agendagora.model.prestador.PrestadorEntity;
import com.example.Agendagora.model.usuario.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

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
    public ResponseEntity<PrestadorDTO> addconprestador(@RequestBody PrestadorDTO dto) throws SQLException {
        PrestadorConverter converterp =prestadorConverter;
        EnderecoConverter convertere= enderecoConverter;
        UsuarioConverter converteru=usuarioConverter;

        if (!usuarioDAO.findbylogin(dto.login)) {
            EnderecoEntity enderecoEntity = endrecoDAO.addendereco(convertere.toEntityP(dto));
            PrestadorEntity prestadorEntity = prestadorDAO.addprestador(converterp.toEntity(dto, enderecoEntity));
            usuarioDAO.addlonginprest(converteru.toEntityP(dto, prestadorEntity));
            prestadorDAO.prestadorpresta(prestadorEntity.id, dto.idtiposervico);
            PrestadorDTO dtoResponse = new PrestadorDTO();
            dtoResponse.id= prestadorEntity.id;
            dtoResponse.nome=prestadorEntity.nome;
            dtoResponse.sobrenome=prestadorEntity.sobrenome;
            dtoResponse.telefone=prestadorEntity.telefone;
            dtoResponse.recebecartao=prestadorEntity.recebecartao;
            dtoResponse.recebepix=prestadorEntity.recebepix;
            dtoResponse.dinheiro=prestadorEntity.dinheiro;
            dtoResponse.idendereco= enderecoEntity.idendereco;
            return ResponseEntity.ok().body(dtoResponse);
        }
        return ResponseEntity.badRequest().build();
    }
   @GetMapping()
   public ResponseEntity<List<PrestadorDTO>>pesquisarprestadores(@RequestParam (value = "data",required = false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data, @RequestParam(value = "tipopag",required = false)String tipopag,
                                                                    @RequestParam (value = "tiposervico",required = false)int tiposervico, @RequestParam (value = "distancia",required = false) int distancia,
                                                                    @RequestParam (value = "lat",required = false)String lat, @RequestParam (value = "lng",required = false)String lng,@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) throws SQLException {

       PrestadorConverter converterp =prestadorConverter;
        int idusuario =usuarioDAO.existetoken(auth);
       if (idusuario==0) {
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
       }
        List<PrestadorEntity> prestadorEntityList= prestadorDAO.pesquisarprestadores(data,distancia,tiposervico,tipopag,lat,lng);
        if(prestadorEntityList.size()==0 ){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(converterp.toDTO(prestadorEntityList));


   }



}









