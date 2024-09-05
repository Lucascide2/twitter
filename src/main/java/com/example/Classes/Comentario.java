package com.example.Classes;

public class Comentario {
    private int id;
    private int id_usuario;
    private String conteudo;

    public Comentario (String conteudo, int id_usuario) {
        this.id_usuario = id_usuario;
        this.conteudo = conteudo;
    }

    public Comentario (int id, String conteudo, int id_usuario) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.conteudo = conteudo;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Comentario [id = " + id + ", conteudo = "+ conteudo + ", id_usuario = " + id_usuario + "]";
    }

    
    
}
