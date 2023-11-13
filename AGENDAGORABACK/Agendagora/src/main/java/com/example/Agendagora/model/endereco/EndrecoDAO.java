package com.example.Agendagora.model.endereco;

import com.example.Agendagora.ConnectionSingleton;
import com.example.Agendagora.model.contratante.ContratanteEntity;
import com.example.Agendagora.model.usuario.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
@Component
public class EndrecoDAO {
    @Autowired
    public ConnectionSingleton connectionSingleton;
    public EnderecoEntity addendereco(EnderecoEntity entity) throws SQLException {
        final String sql = "insert into endereco (rua,cidade,bairo,numero,longitude,latitude)values( ?, ? , ?, ?, ?, ? )";
        try (final PreparedStatement preparedStatement = connectionSingleton.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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
    public EnderecoEntity pesquisar(int id) throws SQLException {
        String sql =" SELECT * FROM endereco where idendereco = ? ";
        try (final PreparedStatement preparedStatement = connectionSingleton.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (final ResultSet rs = preparedStatement.executeQuery()) {
                rs.next();
                EnderecoEntity endereco = new EnderecoEntity();
                endereco.idendereco = rs.getInt(1);
                endereco.rua = rs.getString(2);
                endereco.cidade = rs.getString(3);
                endereco.bairo = rs.getString(4);
                endereco.numero = rs.getInt(5);
                endereco.lng = rs.getString(6);
                endereco.lat = rs.getString(7);
                return endereco;
            }
        }
    }
    public EnderecoEntity updateendereco(EnderecoEntity entity) throws SQLException {
        final String sql = "update endereco set rua = ?,cidade = ?, bairo = ?,numero = ?, longitude = ?, latitude = ? where idendereco = ? ";
        try (final PreparedStatement preparedStatement = connectionSingleton.getConnection().prepareStatement(sql)){
            preparedStatement.setString(1, entity.rua);
            preparedStatement.setString(2, entity.cidade);
            preparedStatement.setString(3, entity.bairo);
            preparedStatement.setInt(4, entity.numero);
            preparedStatement.setString(5, entity.lng);
            preparedStatement.setString(6, entity.lat);
            preparedStatement.setInt(7, entity.idendereco);
            int qtdlinhas = preparedStatement.executeUpdate();;
            if(qtdlinhas==0){
                return null;
            }
            return  entity;

        }

    }
}
