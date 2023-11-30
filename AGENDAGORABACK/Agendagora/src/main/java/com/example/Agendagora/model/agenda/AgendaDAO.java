package com.example.Agendagora.model.agenda;

import com.example.Agendagora.ConnectionSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class AgendaDAO {
    @Autowired
    public ConnectionSingleton connectionSingleton;

    public List<AgendaEntity> adicionardiastrabalhados(List<AgendaEntity> agenda, int idprestador) throws SQLException {
        final String sql = " insert into agenda (dataserv,prestador_idprestador) values (? , ?) ";
        try (final PreparedStatement preparedStatement = connectionSingleton.getConnection().prepareStatement(sql)) {
            for (AgendaEntity agendaEntity : agenda) {
                int qtdVagas = agendaEntity.qtdVagas;
                for (int contador = 0; contador < qtdVagas; contador++) {
                    preparedStatement.setDate(1, java.sql.Date.valueOf(agendaEntity.data));
                    preparedStatement.setInt(2, idprestador);
                    preparedStatement.executeUpdate();
                }
            }
            return agenda;
        }
    }
}



