package com.projeto.projeto_sistemasdistribuidos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "avaliacao")
public class Avaliacao{

    @Id
    @GeneratedValue
    @Column (name = "AVL_INT_ID")
    private int id;
    
    @Column (name = "AVL_INT_REACT")
    private int react;

    @ManyToOne
    @JoinColumn (name = "USU_INT_ID", referencedColumnName = "USU_INT_ID")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn (name = "COM_INT_ID", referencedColumnName = "COM_INT_ID")
    private Comentario comentario;

    public Avaliacao(int id, int react) {
        
        this.id = id;
        this.react = react;
    }

    public Avaliacao(){}

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

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }
}