package com.example.Agendagora.model.contratante;

import com.example.Agendagora.ConnectionSingleton;
import com.example.Agendagora.model.endereco.EnderecoEntity;
import com.example.Agendagora.model.usuario.UsuarioEntity;
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

    public ContratanteEntity pesquisarporid(UsuarioEntity usuarioEntity) throws SQLException{
        String sql =" SELECT * FROM contratante where idcontratante = ? ";
        try (final PreparedStatement preparedStatement = connectionSingleton.getConnection().prepareStatement(sql)){
            preparedStatement.setInt(1, usuarioEntity.prestador.id);
            try (final ResultSet rs = preparedStatement.executeQuery()) {
                rs.next();
                ContratanteEntity contratante= new ContratanteEntity();
                contratante.id=rs.getInt(1);
                contratante.nome=rs.getString(2);
                contratante.sobrenome=rs.getString(3);
                contratante.telefone=rs.getString(4);
                contratante.enderecoEntity= new EnderecoEntity();
                contratante.enderecoEntity.idendereco=rs.getInt(5);
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
    public ContratanteEntity updatecontratante(int id,ContratanteEntity entity) throws SQLException {
        String sql ="update contratante set nome=?, sobrenome=?, telefone=? where idcontratante=?";
        try (final PreparedStatement preparedStatement = connectionSingleton.getConnection().prepareStatement(sql)){
            preparedStatement.setString(1, entity.nome);
            preparedStatement.setString(2, entity.sobrenome);
            preparedStatement.setString(3, entity.telefone);
            preparedStatement.setInt(4, id);
            int qtdlinhas = preparedStatement.executeUpdate();;
            if(qtdlinhas==0){
                return null;
            }
            entity.id=id;
            return entity;

        }

    }

}
