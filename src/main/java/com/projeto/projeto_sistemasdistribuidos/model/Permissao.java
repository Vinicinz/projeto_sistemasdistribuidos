package com.projeto.projeto_sistemasdistribuidos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "permissoes")
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PER_INT_ID")
    private int id;

    @Column(name = "PER_INT_NVACESSO")
    private int nivelAcesso;

    @ManyToOne
    @JoinColumn(name = "USU_INT_ID", referencedColumnName = "USU_INT_ID")
    private Usuario usuario;

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public int getNivelAcesso() {return nivelAcesso;}

    public void setNivelAcesso(int nivelAcesso) {this.nivelAcesso = nivelAcesso;}

    public Usuario getUsuario() {return usuario;}

    public void setUsuario(Usuario usuario) {this.usuario = usuario;}
}
