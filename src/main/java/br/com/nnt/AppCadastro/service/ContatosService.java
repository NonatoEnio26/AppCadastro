package br.com.nnt.AppCadastro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import br.com.nnt.AppCadastro.model.Contato;
import br.com.nnt.AppCadastro.model.Pessoa;
import br.com.nnt.AppCadastro.repository.ContatosRepository;
import br.com.nnt.AppCadastro.repository.PessoasRepository;

@Service
public class ContatosService {

    @Autowired
    private ContatosRepository contatoRepository;

    @Autowired
    private PessoasRepository pessoaRepository;

    // CRUD - Adicionar um novo contato a uma pessoa
    public Contato save(Contato contato) {
        if (contato.getPessoa() == null || contato.getPessoa().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pessoa não informada");
        }

        // Buscar a pessoa no banco de dados
        Pessoa pessoa = pessoaRepository.findById(contato.getPessoa().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));

        // Associar a pessoa ao contato e salvar
        contato.setPessoa(pessoa);
        return contatoRepository.save(contato);
    }

    // CRUD - Obter contato por ID
    public Contato findById(Long id) {
        return contatoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado"));
    }

    // CRUD - Listar todos os contatos de uma pessoa
    public List<Contato> findAllByPessoaId(Long pessoaId) {
        return contatoRepository.findByPessoaId(pessoaId);
    }

    // CRUD - Atualizar contato por ID
    public Contato update(Long id, Contato contatoAtualizado) {
        Contato contatoExistente = contatoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado"));

        // Atualizar apenas os campos permitidos
        contatoExistente.setTipoContato(contatoAtualizado.getTipoContato());
        contatoExistente.setContato(contatoAtualizado.getContato());

        return contatoRepository.save(contatoExistente);
    }

    // CRUD - Deletar contato por ID
    public void delete(Long id) {
        if (!contatoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado");
        }
        contatoRepository.deleteById(id);
    }

	public List<Contato> findAll() {
		// TODO Auto-generated method stub
		return contatoRepository.findAll();
	}
}
