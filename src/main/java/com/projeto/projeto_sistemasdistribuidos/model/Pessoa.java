package com.projeto.projeto_sistemasdistribuidos.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PES_INT_ID")
    private int id;

    @Column(name = "PES_STR_NOME")
    private String nome;

    @Column(name = "PES_STR_CPF")
    private String cpf;

    @Column(name = "PES_DAT_NASC")
    private Date dataNascimento;

    @ManyToOne
    @JoinColumn(name = "USU_INT_ID", referencedColumnName = "USU_INT_ID")
    private Usuario usuario;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
