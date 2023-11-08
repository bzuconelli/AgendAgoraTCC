package com.example.Agendagora.model.endereco;

import com.example.Agendagora.ConnectionSingleton;
import com.example.Agendagora.model.contratante.ContratanteEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EndrecoDAO {
    public EnderecoEntity addendereco(EnderecoEntity entity) throws SQLException {
        final String sql = "insert into endereco (rua,cidade,bairo,numero,longitude,latitude)values( ?, ? , ?, ?, ?, ? )";
        try (final PreparedStatement preparedStatement = ConnectionSingleton.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.rua);
            preparedStatement.setString(2, entity.cidade);
            preparedStatement.setString(3, entity.bairo);
            preparedStatement.setInt(4, entity.numero);
            preparedStatement.setString(5, entity.lng);
            preparedStatement.setString(6, entity.lat);
            preparedStatement.executeUpdate();
            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                rs.next();
                entity.idendereco = rs.getInt(1);

            }
            return entity;
        }
    }
}
