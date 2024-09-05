package com.example.Classes;

public class UsuarioCred extends Usuario {
    private String login;
    private String senha;

    public UsuarioCred(String nome, String email, int idade, String login, String senha) {
        super(nome, email, idade);
        this.login = login;
        this.senha = senha;
    }

    public UsuarioCred(int id, String nome, String email, int idade, String login, String senha) {
        super(id, nome, email, idade);
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "UsuarioCred [Id = " + getId() + ", Nome=" + getNome() + ", Email=" + getEmail()
                + ", Idade=" + getIdade() + ", login= " + login + "]";
    }

}
