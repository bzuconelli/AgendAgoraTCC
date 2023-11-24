package com.example.Agendagora;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Component
@Scope("singleton")
public class ConnectionSingleton {

    private static Connection connection;

    private ConnectionSingleton() {
        // Singleton class
    }

    /**
     * Obtém a conexão ativa com o banco.
     * Caso não exista nenhuma conexão ativa ainda, cria uma nova.
     */
    public  Connection getConnection() throws SQLException {

        if (connection == null) {
            connection = DriverManager.getConnection( //
                    "jdbc:mysql://localhost:3306/agendagora", //
                    "root", //
                    "");
        }

        return connection;
    }

}
