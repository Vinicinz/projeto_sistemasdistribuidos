package com.projeto.projeto_sistemasdistribuidos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "COM_INT_ID")
    private int id;

    @Column (name = "COM_STR_DESC")
    private String desc;

    @ManyToOne
    @JoinColumn (name = "USU_INT_ID", referencedColumnName = "USU_INT_ID")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn (name = "PUB_INT_ID", referencedColumnName = "PUB_INT_ID")
    private Publicacao publicacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }
}
