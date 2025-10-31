package com.example.demo.model.repository.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "aluno")
public class AlunoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "turma_id") 
    private TurmaEntity turma;

    
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public TurmaEntity getTurma() { return turma; }
    public void setTurma(TurmaEntity turma) { this.turma = turma; }
}
