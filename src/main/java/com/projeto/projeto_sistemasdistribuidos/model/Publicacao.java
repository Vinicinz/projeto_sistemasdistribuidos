package com.projeto.projeto_sistemasdistribuidos.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "publicacao")
public class Publicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PUB_INT_ID")
    private Integer id;

    @Column(name = "PUB_STR_TXT")
    private String texto;

    @Column(name = "PUB_DAT_DATAPUBLI")
    private Date dataPublicacao;

    @Column(name = "PUB_BOO_VERIF")
    private boolean verificacao;

    @Column(name = "PUB_BOO_TITULO")
    private boolean titulo;

    @ManyToOne
    @JoinColumn(name = "USU_INT_ID", referencedColumnName = "USU_INT_ID")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "CAT_INT_ID", referencedColumnName = "CAT_INT_ID")
    private Categoria categoria;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public boolean isVerificacao() {
        return verificacao;
    }

    public void setVerificacao(boolean verificacao) {
        this.verificacao = verificacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public boolean isTitulo() {return titulo;}

    public void setTitulo(boolean titulo) {this.titulo = titulo;    }
}
