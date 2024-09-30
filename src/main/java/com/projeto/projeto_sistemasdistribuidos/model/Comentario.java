package com.projeto.projeto_sistemasdistribuidos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "comentario")
public class Comentario {

    @Id
    @GeneratedValue
    @Column (name = "COM_INT_ID")
    private int id;

    @Column (name = "COM_STR_DESC")
    private String desc;

    @ManyToOne
    @JoinColumn (referencedColumnName = "USUARIO_USU_INT_ID")
    private Usuario usuario;

    public Comentario(int id, String desc){
        
        this.id = id;
        this.desc = desc;
    }

    public Comentario(){
    
    }

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

    
    
}
