package br.com.nnt.AppCadastro.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_contatos")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer tipoContato; // 0 = Telefone, 1 = Celular

    @Column(nullable = false)
    private String contato;

    @ManyToOne
    @JsonIgnoreProperties("contatos")
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id", nullable = false)
    private Pessoa pessoa;

    public Contato() {}

    public Contato(Long id, Integer tipoContato, String contato, Pessoa pessoa) {
        this.id = id;
        this.tipoContato = tipoContato;
        this.contato = contato;
        this.pessoa = pessoa;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getTipoContato() { return tipoContato; }
    public void setTipoContato(Integer tipoContato) { this.tipoContato = tipoContato; }

    public String getContato() { return contato; }
    public void setContato(String contato) { this.contato = contato; }

    public Pessoa getPessoa() { return pessoa; }
    public void setPessoa(Pessoa pessoa) { this.pessoa = pessoa; }

    @Override
    public String toString() {
        return "Contato {" +
                "id=" + id +
                ", tipoContato=" + tipoContato +
                ", contato='" + contato + '\'' +
                ", pessoaId=" + (pessoa != null ? pessoa.getId() : "null") +
                '}';
    }
}
