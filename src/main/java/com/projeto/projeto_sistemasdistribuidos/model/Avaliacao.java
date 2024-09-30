package com.projeto.projeto_sistemasdistribuidos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "avaliacao")
public class Avaliacao{

    @Id
    @GeneratedValue
    @Column (name = "AVL_INT_ID")
    private int id;
    
    public Avaliacao(int id) {
        
        this.id = id;
    }

    public Avaliacao(){}

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
}