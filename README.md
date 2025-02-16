# Cadastro API

Este projeto é uma API desenvolvida com Spring Boot para gerenciar cadastros.

## Tecnologias Utilizadas
- Java 21
- Spring Boot 3.4.2
- Maven
- OpenAPI (Swagger UI)
- H2 Database (ou outro banco configurado no `application.properties`)
- JPA/Hibernate
- Spring Security (caso autenticado)

## Configuração e Execução
### Pré-requisitos
- Java 17 ou superior
- Maven instalado

### Passos para executar o projeto
1. Clone este repositório:
   ```sh
   git clone <https://github.com/NonatoEnio26/AppCadastro.git>
   ```
2. Acesse a pasta do projeto:
   ```sh
   cd Cadastro
   ```
3. Compile e execute a aplicação:
   ```sh
   mvn spring-boot:run
   ```

A aplicação estará rodando na porta padrão `8080`.

## Acessar a Documentação da API
A documentação da API gerada pelo OpenAPI pode ser acessada através do seguinte link:

[Swagger UI](http://localhost:8080/swagger-ui.html)

## Estrutura do Projeto
- `src/main/java` - Código-fonte da aplicação
- `src/main/resources` - Arquivos de configuração, como `application.properties`
- `pom.xml` - Configurações do Maven

## Banco de Dados
Por padrão, a aplicação usa um banco de dados H2 em memória. Para acessar o console do H2, utilize:

[Console H2](http://localhost:8080/h2-console)

As configurações do banco podem ser ajustadas no arquivo `application.properties`.

## Serviços da API
### Gerenciamento de Pessoas
#### Criar Pessoa
**Endpoint:** `POST /api/pessoas`

**Exemplo de Request:**
```json
{
  "nome": "Antonio Nonato",
  "endereco": "Rua das Palmeiras, 456",
  "cep": "89012-345",
  "cidade": "Florianópolis",
  "uf": "SC"
}
```

#### Listar Todas as Pessoas
**Endpoint:** `GET /api/pessoas`

#### Buscar Pessoa por ID
**Endpoint:** `GET /api/pessoas/{id}`

#### Atualizar Pessoa
**Endpoint:** `PUT /api/pessoas/{id}`

**Exemplo de Request:**
```json
{
  "nome": "Carlos Silva Atualizado",
  "endereco": "Avenida Brasil, 789",
  "cep": "89045-678",
  "cidade": "Curitiba",
  "uf": "PR"
}
```

#### Deletar Pessoa
**Endpoint:** `DELETE /api/pessoas/{id}`

### Gerenciamento de Contatos
#### Criar Contato
**Endpoint:** `POST /api/contatos`

**Exemplo de Request:**
```json
{
  "tipoContato": 1,
  "contato": "carlos@email.com",
  "pessoa": {
    "id": 1
  }
}
```

#### Listar Contatos de uma Pessoa
**Endpoint:** `GET /api/contatos/pessoa/{id}`

#### Buscar Contato por ID
**Endpoint:** `GET /api/contatos/{id}`

#### Atualizar Contato
**Endpoint:** `PUT /api/contatos/{id}`

**Exemplo de Request:**
```json
{
  "tipoContato": 1,
  "contato": "novoemail@email.com"
}
```

#### Deletar Contato
**Endpoint:** `DELETE /api/contatos/{id}`
