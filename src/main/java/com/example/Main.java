package com.example;

import java.sql.Connection;
import java.util.ArrayList;

import com.example.Classes.Comentario;
import com.example.Classes.Usuario;
import com.example.Classes.UsuarioCred;
import com.example.Daos.ComentarioDao;
import com.example.Daos.UsuarioDao;
import com.example.Database.ConnectionSql;

public class Main {
    public static void main(String[] args) {
        ConnectionSql db = new ConnectionSql();
        Connection conn = db.connect();

        if (conn != null) {
            UsuarioDao crudUsuario = new UsuarioDao(conn);
            ComentarioDao crudComentario = new ComentarioDao(conn);

            // Criar Usuarios
            System.out.println("Criando usuarios...");
            crudUsuario.create(new UsuarioCred("Lucas", "Lucas@email.com", 0, "Lucas_lopes", "Lucas123"));
            crudUsuario.create(new UsuarioCred("Pedro", "Pedro@email.com", 0, "Pedro_lopes", "Pedro123"));
            crudUsuario.create(new UsuarioCred("Caio", "Caio@email.com", 0, "Caio_lopes", "Caio123"));
            
            // // Checar Usuario específico
            System.out.println("\nChecando via Login...");
            UsuarioCred usuario_pedro = crudUsuario.read("Pedro_lopes", "Pedro123");
            if (usuario_pedro != null)
                System.out.println(usuario_pedro.toString());

            // // Deletar usuário
            System.out.println("\nDeletando Lucas...");
            crudUsuario.delete(new UsuarioCred("Lucas", "Lucas@email.com", 0, "Lucas_lopes", "Lucas123"));
            

            // // Mostrar todos os usuários
            System.out.println("\nMostrando todos os usuarios...");
            ArrayList<Usuario> usuarios = crudUsuario.read("");
            if (usuarios != null) {
                usuarios.forEach((usuario) -> System.out.println(usuario));
            }

            // //Update incorreto
            System.out.println("\nUpdate incorreto...");
            usuario_pedro.setSenha("senha");
            crudUsuario.update(usuario_pedro, "nova_senha");
            
            // // Update sem senha
            System.out.println("\nUpdate sem senha...");
            usuario_pedro.setSenha("");
            crudUsuario.update(usuario_pedro, "nova_senha");
            
            System.out.println("\nMostrando todos os usuarios...");
            usuarios = crudUsuario.read("");
            if (usuarios != null) {
                usuarios.forEach((usuario) -> System.out.println(usuario));
            }

            // Update com senha
            System.out.println("\nUpdate com senha...");
            usuario_pedro.setSenha("Pedro123");
            crudUsuario.update(usuario_pedro, "nova_senha");

            System.out.println("\nMostrando todos os usuarios...");
            usuarios = crudUsuario.read("");
            if (usuarios != null) {
                usuarios.forEach((usuario) -> System.out.println(usuario));
            }

            // Read com filtro
            System.out.println("\nMostrando usuarios com Caio no nome...");
            usuarios = crudUsuario.read("Caio");
            if (usuarios != null) {
                usuarios.forEach((usuario) -> System.out.println(usuario));
            }

            // Inserção de varios comentarios
            System.out.println("\nInserindo varios comentarios");
            ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
            comentarios.add(new Comentario("Salve familia", usuario_pedro.getId()));
            comentarios.add(new Comentario("Tudo bom com vocês???", usuario_pedro.getId()));
            crudComentario.create(comentarios);

            // Mostrando todos os comentarios
            System.out.println("\nMostrando os comentarios");
            comentarios = crudComentario.read();
            comentarios.forEach((comentario) -> {
                System.out.println(comentario.toString());
            });
        }
    }
}