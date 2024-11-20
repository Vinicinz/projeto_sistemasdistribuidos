package com.projeto.projeto_sistemasdistribuidos.model;

import jakarta.persistence.*;

@Entity
@Table(
        name = "avaliacao",
        uniqueConstraints = @UniqueConstraint(columnNames = {"USU_INT_ID", "PUB_INT_ID"})
)

public class Avaliacao{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "AVL_INT_ID")
    private int id;
    
    @Column (name = "AVL_INT_REACT")
    private int react;

    @ManyToOne
    @JoinColumn (name = "USU_INT_ID", referencedColumnName = "USU_INT_ID")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn (name = "PUB_INT_ID", referencedColumnName = "PUB_INT_ID")
    private Publicacao publicacao;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getReact() {
        return react;
    }

    public void setReact(int react) {
        this.react = react;
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