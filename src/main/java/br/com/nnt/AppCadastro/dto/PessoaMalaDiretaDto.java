package br.com.nnt.AppCadastro.dto;


import br.com.nnt.AppCadastro.model.Pessoa;

public record PessoaMalaDiretaDto(
    Long id,
    String nome,
    String malaDireta
) {
    public static PessoaMalaDiretaDto fromEntity(Pessoa pessoa) {
        String malaDireta = String.format("%s – CEP: %s – %s/%s",
            pessoa.getEndereco(),
            pessoa.getCep(),
            pessoa.getCidade(),
            pessoa.getUf()
        );
        
        return new PessoaMalaDiretaDto(pessoa.getId(), pessoa.getNome(), malaDireta);
    }
}

		
