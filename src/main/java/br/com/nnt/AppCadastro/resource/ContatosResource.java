package br.com.nnt.AppCadastro.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import br.com.nnt.AppCadastro.model.Contato;
import br.com.nnt.AppCadastro.service.ContatosService;
import io.swagger.v3.oas.annotations.Operation;



@RestController
@RequestMapping("/api/contatos")
public class ContatosResource {

    @Autowired
    private ContatosService contatoService;

    // ENDPOINT (ADICIONA UM NOVO CONTATO A UMA PESSOA)
    @PostMapping // POST http://localhost:8080/api/contatos
    @Operation(summary = "Este endpoint é para gravar um registro de contato e deve estar vinculado a uma pessoa!")
    public ResponseEntity<Contato> save(@RequestBody Contato contato) {
        Contato newContato = contatoService.save(contato);
        return ResponseEntity.status(HttpStatus.CREATED).body(newContato); // 201 Created
    }

    // ENDPOINT (RETORNA OS DADOS DE UM CONTATO POR ID)
    @GetMapping("/{id}") // GET http://localhost:8080/api/contatos/{id}
    public ResponseEntity<Contato> findById(@PathVariable Long id) {
        Contato contato = contatoService.findById(id);
        return ResponseEntity.ok(contato); // 200 OK
    }

    // ENDPOINT (LISTA TODOS OS CONTATOS)
    @GetMapping // GET http://localhost:8080/api/contatos
    public ResponseEntity<List<Contato>> findAll() {
        List<Contato> contatos = contatoService.findAll();
        return ResponseEntity.ok(contatos); // 200 OK
    }

    // ENDPOINT (LISTA TODOS OS CONTATOS DE UMA PESSOA)
    @GetMapping("/pessoa/{idPessoa}") // GET http://localhost:8080/api/contatos/pessoa/{idPessoa}
    public ResponseEntity<List<Contato>> findAllByPessoaId(@PathVariable Long idPessoa) {
        List<Contato> contatos = contatoService.findAllByPessoaId(idPessoa);
        return ResponseEntity.ok(contatos); // 200 OK
    }

    // ENDPOINT (ATUALIZA UM CONTATO EXISTENTE)
    @PutMapping("/{id}") // PUT http://localhost:8080/api/contatos/{id}
    public ResponseEntity<Contato> update(@PathVariable Long id, @RequestBody Contato contato) {
        Contato updContato = contatoService.update(id, contato);
        return ResponseEntity.ok(updContato); // 200 OK
    }

    // ENDPOINT (REMOVE UM CONTATO POR ID)
    @DeleteMapping("/{id}") // DELETE http://localhost:8080/api/contatos/{id}
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contatoService.delete(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
