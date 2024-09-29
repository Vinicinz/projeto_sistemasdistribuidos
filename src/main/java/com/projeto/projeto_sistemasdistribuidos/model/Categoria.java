package com.projeto.projeto_sistemasdistribuidos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue
    @Column(name = "CAT_INT_ID")
    private int id;
    @Column(name = "CAT_STR_TIPO")
    private String tipo;

    public Categoria(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public Categoria() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
