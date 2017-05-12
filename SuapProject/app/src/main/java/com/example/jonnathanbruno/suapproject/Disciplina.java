package com.example.jonnathanbruno.suapproject;


public class Disciplina {
    private int codigo_diario;
    private String disciplina;
    private int nota1;
    private int nota2;
    private int faltas;
    private int perFaltas;
    private String situacao;

    //Constructor
    public Disciplina(int codigo_diario, String disciplina, int nota1, int nota2, int faltas, String situacao) {
        this.codigo_diario = codigo_diario;
        this.disciplina = disciplina;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.faltas = faltas;
        this.situacao = situacao;
    }

    public Disciplina(int codigo_diario, String disciplina, int faltas, int perFaltas) {
        this.codigo_diario = codigo_diario;
        this.disciplina = disciplina;
        this.perFaltas = perFaltas;
        this.faltas = faltas;
    }

    //Setter, getter

    public int getCodigo_diario() {
        return codigo_diario;
    }

    public void setCodigo_diario(int id) {
        this.codigo_diario = codigo_diario;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }


    public int getNota1() {
        return nota1;
    }

    public void setNota1(int nota1) {
        this.nota1 = nota1;
    }


    public int getNota2() {
        return nota2;
    }

    public void setNota2(int nota2) {
        this.nota2 = nota2;
    }


    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }


    public int getPerFaltas() {
        return perFaltas;
    }

    public void setPerFaltas(int perFaltas) {
        this.perFaltas = perFaltas;
    }


    public String getSituacao() {
        return situacao;
    }

    public void setSituacaoituacao(String situacao) {
        this.situacao = situacao;
    }

}
