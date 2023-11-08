package com.example.Agendagora.model.contratante;

import com.example.Agendagora.ConnectionSingleton;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ContratanteDAO {
    public ContratanteEntity addcontratante(ContratanteEntity entity) throws SQLException {
        String sql ="insert into contratante (nome, sobrenome, telefone, endereco_idendereco) values ( ?, ?, ?, ? )";
        try (final PreparedStatement preparedStatement = ConnectionSingleton.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.nome);
            preparedStatement.setString(2, entity.sobrenome);
            preparedStatement.setString(3, entity.telefone);
            preparedStatement.setInt(4, entity.enderecoEntity.idendereco);
            preparedStatement.executeUpdate();
            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                rs.next();
                entity.id = rs.getInt(1);
            }
        }
        return entity;

    }

}
