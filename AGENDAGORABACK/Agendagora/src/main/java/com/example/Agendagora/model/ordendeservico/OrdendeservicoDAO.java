package com.example.Agendagora.model.ordendeservico;

import com.example.Agendagora.ConnectionSingleton;
import com.example.Agendagora.model.agenda.AgendaEntity;
import com.example.Agendagora.model.contratante.ContratanteEntity;
import com.example.Agendagora.model.endereco.EnderecoEntity;
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
    public List<OrdendeservicoEntity> findbyidcotratante(int id, boolean apenasconcluido,boolean tipousuario) throws SQLException {
        String sql = "select os.idordendeservico, p.nomeprestador,p.sobrenomeprestador, p.telefone,  " +
                " os.descservico,os.statusordem,a.dataserv,os.formapagamento,os.avaliacao " +
                " from ordendeservico os " +
                " inner join agenda a on os.agenda_idagenda= a.idagenda " +
                " inner join prestador p on a.prestador_idprestador = p.idprestador " ;
        if(tipousuario){
            sql+=" where contratante_idcontratante = ? ";
        }else {
            sql+=" where os.prestador_idprestador = ? ";
        }
        if (!apenasconcluido) {
            sql += " and os.statusordem ='aberto'  ";
        }
        sql += "ORDER BY a.dataserv";
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
                    entity.agenda.prestadorEntity.telefone = rs.getString(4);
                    entity.descricao = rs.getString(5);
                    entity.status = rs.getString(6);
                    entity.agenda.data = rs.getString(7);
                    entity.formapagamento = rs.getString(8);
                    entity.nota = rs.getInt(9);
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
    public OrdendeservicoEntity addosp(OrdendeservicoEntity entity) throws SQLException {
        final String sql = "INSERT INTO ordendeservico (statusordem, descservico,formapagamento, tiposervico_idtipodeservico, agenda_idagenda, prestador_idprestador)" +
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
    public List<OrdendeservicoEntity> consultarPorIdPrestador(int id, boolean apenasdodia) throws SQLException {
        String sql ="SELECT  ordendeservico.idordendeservico," +
                "    COALESCE(contratante.nome, prestador.nomeprestador) AS nome, " +
                "    COALESCE(contratante.sobrenome, prestador.sobrenomeprestador) AS sobrenome, " +
                "    COALESCE(contratante.telefone, prestador.telefone) AS telefone, "+
                "    endereco.Rua, " +
                "    endereco.cidade, " +
                "    endereco.bairo, " +
                "    endereco.numero,  " +
                "    agenda.dataserv, +agenda.idagenda, " +
                "    ordendeservico.descservico " +
                "FROM " +
                "    agenda " +
                "JOIN  " +
                " ordendeservico ON agenda.idagenda = ordendeservico.agenda_idagenda " +
                "LEFT JOIN  " +
                " prestador ON ordendeservico.prestador_idprestador = prestador.idprestador " +
                "LEFT JOIN " +
                " contratante ON ordendeservico.contratante_idcontratante = contratante.idcontratante " +
                "LEFT JOIN " +
                " endereco ON COALESCE(contratante.endereco_idendereco, prestador.endereco_idendereco) = endereco.idendereco " +
                "WHERE " +
                " agenda.prestador_idprestador = ? " +
                " AND ordendeservico.statusordem = 'aberto ' ";
                if (!apenasdodia){
                    sql+=" AND agenda.dataserv = CURRENT_DATE()";
                }
                sql += "ORDER BY agenda.dataserv ";
        try (final PreparedStatement preparedStatement = connectionSingleton.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1,id);
            try (final ResultSet rs = preparedStatement.executeQuery()) {
                List<OrdendeservicoEntity> resultado = new ArrayList<>();
                while (rs.next()) {
                    OrdendeservicoEntity entity = new OrdendeservicoEntity();
                    entity.idos = rs.getInt(1);
                    entity.contratanteEntity = new ContratanteEntity();
                    entity.contratanteEntity.nome = rs.getString(2);
                    entity.contratanteEntity.sobrenome = rs.getString(3);
                    entity.contratanteEntity.enderecoEntity = new EnderecoEntity();
                    entity.contratanteEntity.telefone= rs.getString(4);
                    entity.contratanteEntity.enderecoEntity.rua = rs.getString(5);
                    entity.contratanteEntity.enderecoEntity.cidade = rs.getString(6);
                    entity.contratanteEntity.enderecoEntity.bairo = rs.getString(7);
                    entity.contratanteEntity.enderecoEntity.numero = rs.getInt(8);
                    entity.agenda = new AgendaEntity();
                    entity.agenda.data = rs.getString(9);
                    entity.agenda.idagenda = rs.getInt(10);
                    entity.descricao = rs.getString(11);
                    resultado.add(entity);
                }
                return resultado;
            }
        }
    }
    public OrdendeservicoEntity finalizar(OrdendeservicoEntity entity, int id) throws SQLException {
        final String sql = "update ordendeservico set valorfinal = ?, statusordem = 'concluido'  where idordendeservico = ? ";
        try (final PreparedStatement preparedStatement = connectionSingleton.getConnection().prepareStatement(sql)) {
            preparedStatement.setDouble(1, entity.valor);
            preparedStatement.setInt(2, id);
            int qtdlinhas = preparedStatement.executeUpdate();
            if (qtdlinhas == 0) {
                return null;
            }
            entity.idos = id;
            return entity;
        }
    }
}






