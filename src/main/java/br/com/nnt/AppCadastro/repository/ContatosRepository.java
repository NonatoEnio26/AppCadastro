package br.com.nnt.AppCadastro.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.nnt.AppCadastro.model.Contato;



@Repository
public interface ContatosRepository extends JpaRepository<Contato, Long> {
	List<Contato> findByPessoaId(Long pessoaId);
	
}

