package com.projeto.projeto_sistemasdistribuidos.model;

public enum Role {

    ADMIN ("admin"),
    USER ("user");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
