package com.example.Agendagora.model.prestador;

import com.example.Agendagora.ConnectionSingleton;
import com.example.Agendagora.model.endereco.EnderecoEntity;
import com.example.Agendagora.model.usuario.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PrestadorDAO {
    @Autowired
    public ConnectionSingleton connectionSingleton;

    public PrestadorEntity addprestador(PrestadorEntity entity) throws SQLException {

            try (final PreparedStatement preparedStatement = connectionSingleton.getConnection().prepareStatement("insert into prestador (nomeprestador , sobrenomeprestador , telefone, pix, cartao, dinheiro, endereco_idendereco) values ( ?, ?, ?, ?, ?, ?,? )", Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, entity.nome);
                preparedStatement.setString(2, entity.sobrenome);
                preparedStatement.setString(3, entity.telefone);
                preparedStatement.setString(4, entity.recebepix);
                preparedStatement.setString(5, entity.recebecartao);
                preparedStatement.setString(6, entity.dinheiro);
                preparedStatement.setInt(7, entity.enderecoEntity.idendereco);
                preparedStatement.executeUpdate();

                try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                    rs.next();
                    entity.id = rs.getInt(1);
                }
            }
            return entity;
    }
    public void prestadorpresta(int id, int servico) throws SQLException {
        String sql= "insert into prestadorpresta ( prestador_idprestador, tiposervico_idtipodeservico) values ( ? , ? )";
        try (final PreparedStatement preparedStatement = connectionSingleton.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, servico);
            preparedStatement.execute();

        }
    }
    public List<PrestadorEntity> pesquisarprestadores(LocalDate date, int distancia, int tiposervico, String pagamento, String lat, String lng) throws SQLException {
        String sql= "{call BuscarPrestadores(?, ?, ?, ?, ?, ?)}";
        try (final PreparedStatement preparedStatement = connectionSingleton.getConnection().prepareStatement(sql)) {
            preparedStatement.setDouble(1, Double.parseDouble(lat));
            preparedStatement.setDouble(2, Double.parseDouble(lng));
            preparedStatement.setInt(3,distancia);
            preparedStatement.setInt(4,tiposervico);
            preparedStatement.setString(5,pagamento);
            preparedStatement.setDate(6, Date.valueOf(date));
            try (final ResultSet rs = preparedStatement.executeQuery()) {

                List<PrestadorEntity> resultado= new ArrayList<>();
                while (rs.next()){
                    PrestadorEntity prestador = new PrestadorEntity();
                    prestador.id=rs.getInt(1);
                    prestador.nome= rs.getString(2);
                    prestador.sobrenome= rs.getString(3);
                    prestador.enderecoEntity= new EnderecoEntity();
                    prestador.enderecoEntity.idendereco= rs.getInt(4);
                    prestador.enderecoEntity.lng= rs.getString(5);
                    prestador.enderecoEntity.lat= rs.getString(6);
                    prestador.idagenda= rs.getInt(7);
                    prestador.nota= rs.getInt(8);
                    resultado.add(prestador);
                }
                return resultado;
            }

        }
    }

}





