package br.edu.escola.jogos.aluno;

import jakarta.persistence.*;


@Entity
@Table(name = "alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String matricula;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String turma;

    @Column(name = "faixa_etaria", nullable = false)
    private String faixaEtaria;

    @Column(name = "senha_hash", nullable = false)
    private String senhaHash;

    protected Aluno() {}

    public Aluno(String matricula, String nome, String turma, String faixaEtaria, String senhaHash) {
        this.matricula = matricula;
        this.nome = nome;
        this.turma = turma;
        this.faixaEtaria = faixaEtaria;
        this.senhaHash = senhaHash;
    }

    public Long getId() { return id; }
    public String getMatricula() { return matricula; }
    public String getNome() { return nome; }
    public String getTurma() { return turma; }
    public String getFaixaEtaria() { return faixaEtaria; }
    public String getSenhaHash() { return senhaHash; }
}
