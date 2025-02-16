package br.com.nnt.AppCadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.nnt.AppCadastro.model.Pessoa;


@Repository

public interface PessoasRepository extends JpaRepository<Pessoa, Long > {

		

}




