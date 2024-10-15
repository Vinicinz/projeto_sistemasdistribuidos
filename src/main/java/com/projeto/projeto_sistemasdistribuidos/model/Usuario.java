package com.projeto.projeto_sistemasdistribuidos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USU_INT_ID")
    private Integer id;

    @Column (name ="USU_STR_EMAIL")
    private String email;

    @Column (name = "USU_STR_SENHA")
    private String senha;

    @Column (name = "USU_STR_NICK")
    private String nick;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public String getNick() {return nick;}

    public String setNick(String nick) {this.nick = nick; return nick; }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
