package com.example.Agendagora.model.usuario;

import com.example.Agendagora.ConnectionSingleton;
import com.example.Agendagora.model.contratante.ContratanteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class UsuarioDAO {
    @Autowired
    public ConnectionSingleton connectionSingleton;

    public int pesquisar(String usuario, String senha) throws SQLException {
        final String sql = "select * from usuario where login = ? and senha = ? ";
        try (final PreparedStatement preparedStatement = connectionSingleton.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, senha);
            try (final ResultSet rs = preparedStatement.executeQuery()) {
                if (!rs.next()) {
                    return -1;
                } else {
                    return rs.getInt(1);
                }
            }
        }
    }

    public void adicionarnatabela(int id, String token) throws SQLException {
        final String sql = "INSERT INTO token (usuario_idusuario, token) values (?, ?)";
        try (final PreparedStatement preparedStatement = connectionSingleton.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, token);
            preparedStatement.execute();

        }
    }

    public int existetoken(String toke) throws SQLException {
        final String sql = "select usuario_idusuario from token where token = ? ";
        try (final PreparedStatement preparedStatement = connectionSingleton.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, toke);
            try (final ResultSet rs = preparedStatement.executeQuery()) {
                while (!rs.next()) {
                    return 0;
                }
                return rs.getInt(1);
            }
        }
    }

    public boolean eprestador(int id) throws SQLException {
        final String sql = "select * from usuario where idusuario = ? ";
        try (final PreparedStatement preparedStatement = connectionSingleton.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (final ResultSet rs = preparedStatement.executeQuery()) {
                rs.next();
                int tem = rs.getInt(5);
                if (tem == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    public void addlongincont(UsuarioEntity usuario) throws SQLException {
        try (final PreparedStatement preparedStatement = connectionSingleton.getConnection().prepareStatement("insert into usuario (login, senha,contratante_idcontratante ) values ( ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, usuario.login);
            preparedStatement.setString(2, usuario.senha);
            preparedStatement.setInt(3, usuario.contratante.id);
            preparedStatement.executeUpdate();
            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                rs.next();
                usuario.id = rs.getInt(1);
            }
        }
    }

    public void addlonginprest(UsuarioEntity usuario) throws SQLException {
        try (final PreparedStatement preparedStatement = connectionSingleton.getConnection().prepareStatement("insert into usuario (login, senha,prestador_idprestador ) values ( ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, usuario.login);
            preparedStatement.setString(2, usuario.senha);
            preparedStatement.setInt(3, usuario.prestador.id);
            preparedStatement.executeUpdate();
            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                rs.next();
                usuario.id = rs.getInt(1);
            }
        }
    }

    public boolean findbylogin(String usuario) throws SQLException {
        final String sql = "select * from usuario where login = ?  ";
        try (final PreparedStatement preparedStatement = connectionSingleton.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, usuario);
            try (final ResultSet rs = preparedStatement.executeQuery()) {
                return rs.next();
            }
        }
    }

    public UsuarioEntity findbyid(int id, boolean contratante) throws SQLException {

        final String sql = "select * from usuario where idusuario = ?  ";
        try (final PreparedStatement preparedStatement = connectionSingleton.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (final ResultSet rs = preparedStatement.executeQuery()) {
                rs.next();
                if (contratante) {
                    UsuarioEntity usuario = new UsuarioEntity();
                    usuario.id = rs.getInt(1);
                    usuario.login = rs.getString(2);
                    usuario.senha = rs.getString(3);
                    usuario.contratante = new ContratanteEntity();
                    usuario.contratante.id = rs.getInt(4);

                    return usuario;
                } else {
                    UsuarioEntity usuario = new UsuarioEntity();
                    usuario.id = rs.getInt(1);
                    usuario.login = rs.getString(2);
                    usuario.senha = rs.getString(3);
                    usuario.prestador.id = rs.getInt(5);
                    usuario.id = id;
                    return usuario;
                }

            }
        }
    }

    public UsuarioEntity updateusuario(UsuarioEntity entity, boolean contratante) throws SQLException {
        String sql = "update usuario set login= ?,senha=? where ";
        if (contratante) {
            sql += " contratante_idcontratante = ? ";
        } else {
            sql += "prestador_idprestador = ?";
        }
        if (contratante) {
            try (final PreparedStatement preparedStatement = connectionSingleton.getConnection().prepareStatement(sql)) {
                preparedStatement.setString(1, entity.login);
                preparedStatement.setString(2, entity.senha);
                preparedStatement.setInt(3, entity.contratante.id);
                int qtdlinhas = preparedStatement.executeUpdate();;
                if(qtdlinhas==0){
                    return null;
                }
                return entity;

            }
        }
        try (final PreparedStatement preparedStatement = connectionSingleton.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, entity.login);
            preparedStatement.setString(2, entity.senha);
            preparedStatement.setInt(3, entity.prestador.id);
            int qtdlinhas = preparedStatement.executeUpdate();;
            if(qtdlinhas==0){
                return null;
            }
            return entity;
        }
    }
}

















