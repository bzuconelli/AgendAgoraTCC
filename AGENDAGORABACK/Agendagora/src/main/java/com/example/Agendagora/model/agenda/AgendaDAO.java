package com.example.Agendagora.model.agenda;

import com.example.Agendagora.ConnectionSingleton;
import com.example.Agendagora.model.prestador.PrestadorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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

    public List<AgendaEntity> pesquisardiastrabalhados(LocalDate mes, int idprestador) throws SQLException {
        final String sql = "SELECT dataserv,  COUNT(prestador_idprestador) as quantidade" +
                "FROM agenda " +
                "WHERE MONTH( dataserv ) = ? " +
                "and prestador_idprestador = ? " +
                "GROUP BY dataserv, prestador_idprestador ";
        try (final PreparedStatement preparedStatement = connectionSingleton.getConnection().prepareStatement(sql)) {
            preparedStatement.setDate(1, Date.valueOf(mes));
            preparedStatement.setInt(2, idprestador);
            try (final ResultSet rs = preparedStatement.executeQuery()) {
                List<AgendaEntity> resultado = new ArrayList<>();
                while (rs.next()) {
                    AgendaEntity agenda = new AgendaEntity();
                    agenda.data = rs.getString(1);
                    agenda.qtdVagas = rs.getInt(2);
                    resultado.add(agenda);
                }
                return resultado;
            }
        }
    }
}





