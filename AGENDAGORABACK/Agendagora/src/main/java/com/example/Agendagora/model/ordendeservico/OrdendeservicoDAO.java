package com.example.Agendagora.model.ordendeservico;

import com.example.Agendagora.ConnectionSingleton;
import com.example.Agendagora.model.agenda.AgendaEntity;
import com.example.Agendagora.model.prestador.PrestadorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrdendeservicoDAO {
    @Autowired
    public ConnectionSingleton connectionSingleton;

    public OrdendeservicoEntity addos(OrdendeservicoEntity entity) throws SQLException {
        final String sql = "INSERT INTO ordendeservico (statusordem, descservico,formapagamento, tiposervico_idtipodeservico, agenda_idagenda, contratante_idcontratante)" +
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
            preparedStatement.setInt(4, entity.contratanteEntity.id);
            preparedStatement.setDate(5, Date.valueOf(entity.agenda.data));
            preparedStatement.setInt(6, entity.agenda.prestadorEntity.id);
            int qtdlinhas = preparedStatement.executeUpdate();
            if (qtdlinhas == 0) {
                return null;
            }
            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                rs.next();
                entity.idos = rs.getInt(1);
                return entity;
            }
        }
    }

    public List<OrdendeservicoEntity> findbyidcotratante(int id, boolean apenasemaberto) throws SQLException {
        String sql = "select os.idordendeservico, p.nomeprestador,p.sobrenomeprestador," +
                " os.descservico,os.statusordem,a.dataserv,os.formapagamento,os.avaliacao " +
                " from ordendeservico os " +
                " inner join agenda a on os.agenda_idagenda= a.idagenda " +
                " inner join prestador p on a.prestador_idprestador = p.idprestador " +
                " where contratante_idcontratante = ? ";
        if (apenasemaberto) {
            sql += "and os.statusordem ='aberto' ";
        }
        try (final PreparedStatement preparedStatement = connectionSingleton.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (final ResultSet rs = preparedStatement.executeQuery()) {
                List<OrdendeservicoEntity> resultado = new ArrayList<>();
                while (rs.next()) {
                    OrdendeservicoEntity entity = new OrdendeservicoEntity();
                    entity.idos = rs.getInt(1);
                    entity.agenda = new AgendaEntity();
                    entity.agenda.prestadorEntity = new PrestadorEntity();
                    entity.agenda.prestadorEntity.nome = rs.getString(2);
                    entity.agenda.prestadorEntity.sobrenome = rs.getString(3);
                    entity.descricao = rs.getString(4);
                    entity.status = rs.getString(5);
                    entity.agenda.data = rs.getString(6);
                    entity.formapagamento = rs.getString(7);
                    entity.nota = rs.getInt(8);
                    resultado.add(entity);
                }
                return resultado;
            }
        }
    }

    public OrdendeservicoEntity findbyidordendesrvico(int id) throws SQLException {
        final String sql = "select idordendeservico, statusordem, descservico, formapagamento from ordendeservico where idordendeservico= ? ";
        try (final PreparedStatement preparedStatement = connectionSingleton.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (final ResultSet rs = preparedStatement.executeQuery()) {
                rs.next();
                OrdendeservicoEntity entity = new OrdendeservicoEntity();
                entity.idos = rs.getInt(1);
                entity.status = rs.getString(2);
                entity.descricao = rs.getString(3);
                entity.formapagamento = rs.getString(4);
                return entity;
            }
        }
    }

    public OrdendeservicoEntity delete(int id) throws SQLException {
        final OrdendeservicoEntity osaserapagada = findbyidordendesrvico(id);
        final String sql = "delete from ordendeservico where idordendeservico = ? ";
        try (final PreparedStatement preparedStatement = connectionSingleton.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int qtdlinhas = preparedStatement.executeUpdate();
            if (qtdlinhas == 0) {
                return null;
            }
            return osaserapagada;
        }
    }

    public OrdendeservicoEntity avaliacao(OrdendeservicoEntity entity, int id) throws SQLException {
        final String sql = "update ordendeservico set avaliacao = ? , obervacao = ? where idordendeservico = ? ";
        try (final PreparedStatement preparedStatement = connectionSingleton.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, entity.nota);
            preparedStatement.setString(2, entity.observacao);
            preparedStatement.setInt(3, id);
            int qtdlinhas = preparedStatement.executeUpdate();
            if (qtdlinhas == 0) {
                return null;
            }
            entity.idos = id;
            return entity;
        }
    }
}






