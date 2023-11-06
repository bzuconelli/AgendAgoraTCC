package com.example.Agendagora.model.prestador;

import com.example.Agendagora.ConnectionSingleton;
import com.example.Agendagora.controller.prestador.CadprestadorDTO;
import com.example.Agendagora.model.endereco.EnderecoEntity;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PrestadorDAO {

    public PrestadorEntity addprestador(CadprestadorDTO dto,PrestadorEntity entity) throws SQLException {
            final String sql ="insert into endereco (rua,cidade,bairo,numero,longitude,latitude)values( ?, ? , ?, ?, ?, ? )" ;
            try (final PreparedStatement preparedStatement = ConnectionSingleton.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                entity.enderecoEntity= new EnderecoEntity();
                preparedStatement.setString(1, entity.enderecoEntity.rua);
                preparedStatement.setString(2, entity.enderecoEntity.cidade);
                preparedStatement.setString(3, entity.enderecoEntity.bairo);
                preparedStatement.setInt(4, entity.enderecoEntity.numero);
                preparedStatement.setString(5, entity.enderecoEntity.lng);
                preparedStatement.setString(6, entity.enderecoEntity.lat);
                preparedStatement.executeUpdate();
                try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                    rs.next();
                    entity.enderecoEntity.idendereco = rs.getInt(1);

                }
            }
            try (final PreparedStatement preparedStatement = ConnectionSingleton.getConnection().prepareStatement("insert into prestador (nomeprestador , sobrenomeprestador ,  cpf, cnpj,  telefone, pix, cartao,dinheiro, endereco_idendereco) values ( ?, ?, ?, ?, ?, ?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, entity.nome);
                preparedStatement.setString(2, entity.sobrenome);
                preparedStatement.setString(3, entity.cpf);
                preparedStatement.setString(4, entity.cnpj);
                preparedStatement.setString(5, entity.telefone);
                preparedStatement.setString(6, dto.recebepix);
                preparedStatement.setString(7, dto.recebecartao);
                preparedStatement.setString(8, dto.dinheiro);
                preparedStatement.setInt(9, entity.enderecoEntity.idendereco);
                preparedStatement.executeUpdate();

                try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                    rs.next();
                    entity.id = rs.getInt(1);
                }
            }
            try (final PreparedStatement preparedStatement = ConnectionSingleton.getConnection().prepareStatement("insert into prestadorpresta ( prestador_idprestador, tiposervico_idtipodeservico) values ( ? ,?)")) {
                 preparedStatement.setInt(1, entity.id);
                 preparedStatement.setInt(1, dto.idtiposervico);
            }
            return entity;

    }
}





