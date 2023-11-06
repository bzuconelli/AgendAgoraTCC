package com.example.Agendagora.model.contratante;

import com.example.Agendagora.ConnectionSingleton;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ContratanteDAO {
    public ContratanteEntity addcontratante(ContratanteEntity entity) throws SQLException {
        final String sql ="insert into endereco (rua,cidade,bairo,numero,longitude,latitude)values( ?, ? , ?, ?, ?, ? )" ;
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
        }
        try (final PreparedStatement preparedStatement = ConnectionSingleton.getConnection().prepareStatement("insert into contratante (nome, sobrenome, cpf, telefone, endereco_idendereco) values ( ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.nome);
            preparedStatement.setString(2, entity.sobrenome);
            preparedStatement.setString(3, entity.cpf);
            preparedStatement.setString(4, entity.telefone);
            preparedStatement.setInt(5, entity.idendereco);
            preparedStatement.executeUpdate();

            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                rs.next();
                entity.id = rs.getInt(1);
            }
        }
        try (final PreparedStatement preparedStatement = ConnectionSingleton.getConnection().prepareStatement("insert into usuario (login, senha,contratante_idcontratante ) values ( ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.login);
            preparedStatement.setString(2, entity.senha);
            preparedStatement.setInt(3, entity.id);
            preparedStatement.executeUpdate();
            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                rs.next();
                entity.idlogin = rs.getInt(1);
            }
        }
        return entity;

    }

}
