package com.example.jonnathanbruno.suapproject;


public class Aluno {
    private String matricula;
    private String foto;
    private String email;
    private String nome;


    //Constructor
    public Aluno(String matricula, String foto, String email, String nome) {
        this.matricula = matricula;
        this.foto = foto;
        this.email = email;
        this.nome = nome;
    }


    public String getMatricula() {
        return this.matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }


    public String getFoto() {
        return this.foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getNome () {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
