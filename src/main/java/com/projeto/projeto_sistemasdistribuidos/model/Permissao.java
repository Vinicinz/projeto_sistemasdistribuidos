package com.projeto.projeto_sistemasdistribuidos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "permissoes")
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PER_INT_ID")
    private int id;

    @Column(name = "PER_INT_NVLACESSO")
    private int nivelAcesso;

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public int getNivelAcesso() {return nivelAcesso;}

    public void setNivelAcesso(int nivelAcesso) {this.nivelAcesso = nivelAcesso;}

}
