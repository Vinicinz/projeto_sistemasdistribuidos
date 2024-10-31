package com.projeto.projeto_sistemasdistribuidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ProjetoSistemasdistribuidosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoSistemasdistribuidosApplication.class, args);
	}

}
