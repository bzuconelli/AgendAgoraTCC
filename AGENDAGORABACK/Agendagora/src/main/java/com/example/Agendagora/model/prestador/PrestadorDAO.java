package com.example.Agendagora.model.prestador;

import com.example.Agendagora.ConnectionSingleton;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PrestadorDAO {

    public PrestadorEntity addprestador(PrestadorEntity entity) throws SQLException {

            try (final PreparedStatement preparedStatement = ConnectionSingleton.getConnection().prepareStatement("insert into prestador (nomeprestador , sobrenomeprestador , telefone, pix, cartao, dinheiro, endereco_idendereco) values ( ?, ?, ?, ?, ?, ?,? )", Statement.RETURN_GENERATED_KEYS)) {
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
        try (final PreparedStatement preparedStatement = ConnectionSingleton.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, servico);
            preparedStatement.execute();

        }
    }

}





