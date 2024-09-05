package com.example.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSql {
    private String url;

    public ConnectionSql(){
        this.url = "jdbc:sqlite:src\\main\\java\\com\\example\\Database\\BancoNovo.db"; // mudar
    }

    public Connection connect() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e);
        }

        return conn;
    }
}
