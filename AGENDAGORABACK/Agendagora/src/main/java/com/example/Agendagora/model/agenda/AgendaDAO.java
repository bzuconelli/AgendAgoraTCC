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

    public List<AgendaEntity> pesquisardiastrabalhados(int mes, int idprestador, int ano) throws SQLException {
        final String sql = "SELECT dataserv,  COUNT(prestador_idprestador) as quantidade " +
                "FROM agenda " +
                "WHERE MONTH( dataserv ) = ? " +
                "and prestador_idprestador = ? " +
                " and  year ( dataserv ) = ?  " +
                "GROUP BY dataserv, prestador_idprestador ";
        try (final PreparedStatement preparedStatement = connectionSingleton.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, mes);
            preparedStatement.setInt(2, idprestador);
            preparedStatement.setInt(3, ano);
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

    public List<AgendaEntity> editardiastrabalhados( List<AgendaEntity> agenda,int mes, int idprestador, int ano) throws SQLException {
        List<AgendaEntity> agendaatual = pesquisardiastrabalhados(mes, idprestador, ano);
        final String sqlinsert = " insert into agenda (dataserv,prestador_idprestador) values (? , ?) ";
        final String sqldelete = " DELETE FROM agenda " +
                "WHERE idagenda IN ( " +
                "    SELECT idagendaToDelete " +
                "    FROM ( " +
                "        SELECT a.idagenda AS idagendaToDelete " +
                "        FROM agenda a " +
                "        LEFT JOIN ordendeservico o ON a.idagenda = o.agenda_idagenda " +
                "        WHERE a.dataserv = ? " +
                "        AND a.prestador_idprestador = ? " +
                "        AND o.agenda_idagenda IS NULL " +
                "        LIMIT 1 " +
                "    ) AS subquery " +
                ") ";
        for (AgendaEntity agendadobanco : agendaatual) {
            for (AgendaEntity agendaeditada : agenda) {
                if (agendaeditada.data.equals(agendadobanco.data)) {
                    if (agendaeditada.qtdVagas > agendadobanco.qtdVagas) {
                        int vagas = agendaeditada.qtdVagas - agendadobanco.qtdVagas;
                        try (final PreparedStatement preparedStatement = connectionSingleton.getConnection().prepareStatement(sqlinsert)) {
                            for (int contador = 0; contador < vagas; contador++) {
                                preparedStatement.setDate(1, java.sql.Date.valueOf(agendaeditada.data));
                                preparedStatement.setInt(2, idprestador);
                                preparedStatement.executeUpdate();
                            }
                        }
                    } else if (agendaeditada.qtdVagas < agendadobanco.qtdVagas) {
                        int vagas = agendadobanco.qtdVagas-agendaeditada.qtdVagas  ;
                        try (final PreparedStatement preparedStatement = connectionSingleton.getConnection().prepareStatement(sqldelete)) {
                            for (int contador = 0; contador < vagas; contador++) {
                                preparedStatement.setDate(1, Date.valueOf(agendaeditada.data));
                                preparedStatement.setInt(2, idprestador);
                                preparedStatement.executeUpdate();
                            }
                        }
                    }
                }
            }
        }
        return agenda;
    }
}










