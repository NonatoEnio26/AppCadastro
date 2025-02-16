package br.com.nnt.AppCadastro.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.nnt.AppCadastro.dto.PessoaMalaDiretaDto;
import br.com.nnt.AppCadastro.model.Pessoa;
import br.com.nnt.AppCadastro.repository.PessoasRepository;

@Service
public class PessoasService {
    
    @Autowired
    private PessoasRepository pessoaRepository; // Agora corretamente injetado antes do uso

    // DTO para Mala Direta
    public PessoaMalaDiretaDto getMalaDiretaById(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));
        return PessoaMalaDiretaDto.fromEntity(pessoa);
    }

    // CRUD - Criar Pessoa
    public Pessoa save(Pessoa pessoa) {
        if (pessoa.getNome() == null || pessoa.getNome().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome da pessoa não pode ser vazio.");
        }
        return pessoaRepository.save(pessoa);
    }

    // CRUD - Obter Pessoa por ID
    public Pessoa findById(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));
    }

    // CRUD - Listar todas as Pessoas
    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    // CRUD - Atualizar Pessoa por ID
    public Pessoa update(Long id, Pessoa pessoa) {
        Pessoa pessoaExistente = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));

        if (pessoa.getNome() != null) pessoaExistente.setNome(pessoa.getNome());
        if (pessoa.getEndereco() != null) pessoaExistente.setEndereco(pessoa.getEndereco());
        if (pessoa.getCep() != null) pessoaExistente.setCep(pessoa.getCep());
        if (pessoa.getCidade() != null) pessoaExistente.setCidade(pessoa.getCidade());
        if (pessoa.getUf() != null) pessoaExistente.setUf(pessoa.getUf());
        return pessoaRepository.save(pessoaExistente);
    }

    // CRUD - Deletar Pessoa por ID
    public void delete(Long id) {
        if (!pessoaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada");
        }
        pessoaRepository.deleteById(id);
    }

	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return pessoaRepository.existsById(id);
	}
}	