package com.example.Daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.Classes.Usuario;
import com.example.Classes.UsuarioCred;

public class UsuarioDao {
    private Connection conn;

    public UsuarioDao(Connection conn) {
        this.conn = conn;
    }

    public void create(UsuarioCred usuario) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Usuario(nome, email, idade, login, senha) values (?, ?, ?, ?, ?)");
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setInt(3, usuario.getIdade());
            stmt.setString(4, usuario.getLogin());
            stmt.setString(5, usuario.getSenha());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public ArrayList<Usuario> read(String filter) {
        try {
            PreparedStatement stmt = conn.prepareStatement("Select * FROM Usuario WHERE nome LIKE ?");
            stmt.setString(1, "%" + filter + "%");
            ResultSet rs = stmt.executeQuery();

            ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

            while (rs.next()) {
                usuarios.add(new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getInt("idade")));
            }

            return usuarios;
        } catch (SQLException e) {
            System.out.println("erro");
            return null;
        }
    }

    public UsuarioCred read(String login, String senha) {
        try {
            PreparedStatement stmt = conn.prepareStatement("Select * FROM Usuario WHERE login = ? AND senha = ?");
            stmt.setString(1, login);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();
            UsuarioCred usuario = null;

            while(rs.next()) {
                usuario = new UsuarioCred(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getInt("idade"),
                    rs.getString("login"),
                    ""
                );
            }

            return usuario;
        } catch (SQLException e2) {
            System.out.println(e2);
            return null;
        }
    }

    public void update(UsuarioCred usuario, String nova_senha) {
        try {
            if (usuario.getSenha() == "") {
                PreparedStatement stmt = conn.prepareStatement("UPDATE Usuario SET nome = ?, email = ?, idade = ?, login = ? WHERE id = ?");
                stmt.setString(1, usuario.getNome());
                stmt.setString(2, usuario.getEmail());
                stmt.setInt(3, usuario.getId());
                stmt.setString(4, usuario.getLogin());
                stmt.executeUpdate();
            } else {
                if ((nova_senha != null) && (nova_senha != "")) {
                    Usuario usuario_temp = read(usuario.getLogin(), usuario.getSenha());
                    if (usuario_temp == null) {
                        System.out.println("Senha incorreta");
                        return;
                    }

                    PreparedStatement stmt = conn.prepareStatement("UPDATE Usuario SET nome = ?, email = ?, idade = ?, login = ?, senha = ? WHERE id = ?");
                    stmt.setString(1, usuario.getNome());
                    stmt.setString(2, usuario.getEmail());
                    stmt.setInt(3, usuario.getIdade());
                    stmt.setString(4, usuario.getLogin());         
                    stmt.setString(5, nova_senha);
                    stmt.setInt(6, usuario.getId());
                    stmt.executeUpdate();                
                }
            }


        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void delete(UsuarioCred usuario) {
        try {
            UsuarioCred usuario_temp = read(usuario.getLogin(), usuario.getSenha());

            if (usuario_temp == null) {
                System.out.println("Senha ou login incorretos");
                return;
            }

            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Usuario WHERE id = ?");
            stmt.setInt(1, usuario_temp.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
