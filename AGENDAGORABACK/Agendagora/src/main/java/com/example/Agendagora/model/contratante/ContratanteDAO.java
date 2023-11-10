package com.example.Agendagora.model.contratante;

import com.example.Agendagora.ConnectionSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
@Component

public class ContratanteDAO {
    @Autowired
    public ConnectionSingleton connectionSingleton;

    public ContratanteEntity pesquisarporid(int id) throws SQLException{
        String sql =" SELECT * FROM contratante where idcontratante = ? ";
        try (final PreparedStatement preparedStatement = connectionSingleton.getConnection().prepareStatement(sql)){
            preparedStatement.setInt(1, id);
            try (final ResultSet rs = preparedStatement.executeQuery()) {
                ContratanteEntity contratante= new ContratanteEntity();
                contratante.id=rs.getInt(1);
                contratante.nome=rs.getString(2);
                contratante.sobrenome=rs.getString(3);
                contratante.enderecoEntity.idendereco=rs.getInt(4);
                return contratante;
            }
        }
    }



    public ContratanteEntity addcontratante(ContratanteEntity entity) throws SQLException {
        String sql ="insert into contratante (nome, sobrenome, telefone, endereco_idendereco) values ( ?, ?, ?, ? )";
        try (final PreparedStatement preparedStatement = connectionSingleton.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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
