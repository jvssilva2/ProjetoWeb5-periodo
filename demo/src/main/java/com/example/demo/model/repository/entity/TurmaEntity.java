package com.example.demo.model.repository.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "turma")
public class TurmaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_turma", nullable = false, unique = true)
    private String nomeTurma;

    private String curso;
    private String periodo;

    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AlunoEntity> alunos = new ArrayList<>();

    

   
    public void addAluno(AlunoEntity aluno) {
        this.alunos.add(aluno);
        aluno.setTurma(this);
    }
    
   
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNomeTurma() { return nomeTurma; }
    public void setNomeTurma(String nomeTurma) { this.nomeTurma = nomeTurma; }
    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }
    public String getPeriodo() { return periodo; }
    public void setPeriodo(String periodo) { this.periodo = periodo; }
    public List<AlunoEntity> getAlunos() { return alunos; }
    public void setAlunos(List<AlunoEntity> alunos) { this.alunos = alunos; }
}