package br.com.nnt.AppCadastro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "br.com.nnt.AppCadastro.model")
@EnableJpaRepositories(basePackages = "br.com.nnt.AppCadastro.repository")
public class CadastroApplication {
    public static void main(String[] args) {
        SpringApplication.run(CadastroApplication.class, args);
    }
}
