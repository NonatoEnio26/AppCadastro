package br.com.nnt.AppCadastro.resource;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import br.com.nnt.AppCadastro.dto.PessoaMalaDiretaDto;
import br.com.nnt.AppCadastro.model.Pessoa;
import br.com.nnt.AppCadastro.service.PessoasService;
import org.springframework.web.server.ResponseStatusException;

// API RESTful - retorna JSON
@RestController
@RequestMapping("/api/pessoas") // http://localhost:8080/api/pessoas
public class PessoasResource {

    @Autowired
    private PessoasService pessoaService;

    // ENDPOINT - Criar uma nova pessoa
    @PostMapping
    public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa) {
        if (pessoa.getId() != null) {
            return ResponseEntity.badRequest().body(null); // Retorna 400 Bad Request se ID for enviado
        }
        Pessoa newPessoa = pessoaService.save(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPessoa); // Retorna 201 Created
    }

    // ENDPOINT - Retorna os dados de uma pessoa por ID

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
        try {
            Pessoa pessoa = pessoaService.findById(id);
            return ResponseEntity.ok(pessoa); // Retorna 200 OK com os dados da pessoa
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(null); // Retorna 404 se não encontrar
        }
    }


    // ENDPOINT - Retorna os dados de uma pessoa para mala direta
    @GetMapping("/maladireta/{id}")
    public ResponseEntity<PessoaMalaDiretaDto> getMalaDiretaById(@PathVariable Long id) {
        PessoaMalaDiretaDto malaDireta = pessoaService.getMalaDiretaById(id);
        if (malaDireta == null) {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found se não encontrar
        }
        return ResponseEntity.ok(malaDireta);
    }

    // ENDPOINT - Lista todas as pessoas
    @GetMapping
    public ResponseEntity<List<Pessoa>> findAll() {
        List<Pessoa> pessoas = pessoaService.findAll();
        if (pessoas.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        }
        return ResponseEntity.ok(pessoas);
    }

    // ENDPOINT - Atualiza uma pessoa existente
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        if (!pessoaService.existsById(id)) { // Corrigido para verificar se NÃO existe
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found se a pessoa não existir
        }
        Pessoa updatedPessoa = pessoaService.update(id, pessoa);
        return ResponseEntity.ok(updatedPessoa);
    }

    // ENDPOINT - Remove uma pessoa por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!pessoaService.existsById(id)) { // Corrigido para verificar se NÃO existe
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found se a pessoa não existir
        }
        pessoaService.delete(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }
}
  