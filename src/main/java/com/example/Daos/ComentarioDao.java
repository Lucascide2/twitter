package com.example.Daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.Classes.Comentario;

public class ComentarioDao {
    private Connection conn;

    public ComentarioDao(Connection conn) {
        this.conn = conn;
    }

    public void create(ArrayList<Comentario> comentarios) {
        try {
            PreparedStatement stmt = conn
                    .prepareStatement("INSERT INTO Comentario(conteudo, id_usuario) values (?, ?)");
            comentarios.forEach((comentario) -> {
                try {
                    stmt.setString(1, comentario.getConteudo());
                    stmt.setInt(2, comentario.getId_usuario());
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            });
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ArrayList<Comentario> read() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * FROM Comentario");

            ArrayList<Comentario> comentarios = new ArrayList<Comentario>();

            while (rs.next()) {
                comentarios.add(new Comentario(rs.getInt("id"), rs.getString("conteudo"), rs.getInt("id_usuario")));
            }

            return comentarios;
        } catch (SQLException e) {
            System.out.println("erro");
            return null;
        }
    }

    public void update(Comentario comentario, String conteudo) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE Comentario SET conteudo = ? WHERE id = ?");
            stmt.setString(1, comentario.getConteudo());
            stmt.setInt(2, comentario.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void delete(Comentario comentario) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Comentario WHERE id = ?");
            stmt.setInt(1, comentario.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
