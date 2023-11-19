package com.example.Agendagora.model.ordendeservico;

import com.example.Agendagora.ConnectionSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class OrdendeservicoDAO {
    @Autowired
    public ConnectionSingleton connectionSingleton;

    public OrdendeservicoEntity addos (OrdendeservicoEntity entity) throws SQLException {
        final String sql= "INSERT INTO ordendeservico (statusordem, descservico,formapagamento, tiposervico_idtipodeservico, agenda_idagenda, contratante_idcontratante)" +
                "SELECT 'aberto' , ? , ? , ? , a.idagenda, ? " +
                "FROM agenda a " +
                "WHERE a.dataserv = ? " +
                "AND a.prestador_idprestador = ? " +
                "AND NOT EXISTS ( " +
                "    SELECT 1 FROM ordendeservico o " +
                "    WHERE o.agenda_idagenda = a.idagenda" +
                ")" +
                "LIMIT 1 ";
        try (final PreparedStatement preparedStatement = connectionSingleton.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.descricao);
            preparedStatement.setString(2, entity.formapagamento);
            preparedStatement.setInt(3, entity.idtiposervico);
            preparedStatement.setInt(4,entity.contratanteEntity.id);
            preparedStatement.setDate(5, Date.valueOf(entity.agenda.data));
            preparedStatement.setInt(6, entity.agenda.prestadorEntity.id);
            int qtdlinhas= preparedStatement.executeUpdate();
            if (qtdlinhas==0){
                return null;
            }
            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                rs.next();
                entity.idos = rs.getInt(1);
                return entity;
            }
        }

    }

}
