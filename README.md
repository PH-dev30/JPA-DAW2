# Sistema de Gerenciamento de Batalhas Pokémon

## Descrição

Projeto desenvolvido para a disciplina de Desenvolvimento de Aplicações Web (DAW), utilizando Spring Boot, JPA/Hibernate e PostgreSQL.

A aplicação permite o gerenciamento de treinadores, pokémons, movimentos, times e batalhas através de uma API REST documentada com Swagger.

---

## Tecnologias Utilizadas

* Java 17
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven
* Lombok
* Swagger / OpenAPI

---

## Funcionalidades

### Treinadores

* Cadastrar treinador
* Listar treinadores
* Buscar treinador por ID
* Atualizar treinador
* Remover treinador

### Pokémons

* Cadastrar pokémon
* Listar pokémons
* Buscar pokémon por ID
* Atualizar pokémon
* Remover pokémon
* Buscar pokémons utilizando filtros

### Movimentos

* Cadastrar movimento
* Listar movimentos
* Buscar movimento por ID
* Atualizar movimento
* Remover movimento
* Buscar movimentos utilizando filtros e paginação

### Times

* Gerenciamento de times de pokémons
* Associação entre treinadores e pokémons

### Batalhas

* Registro de batalhas
* Associação entre treinadores e times
* Simulação automática de batalhas
* Definição automática do vencedor com base na soma do poder dos pokémons participantes

---

## Entidades Principais

O sistema possui as seguintes entidades:

* Treinador
* Pokemon
* Movimento
* Time
* Batalha
* Selecao
* SelecaoId
* (Demais entidades auxiliares relacionadas ao domínio)

---

## Recursos Implementados

### JPA / Hibernate

* Mapeamento de entidades com `@Entity`
* Relacionamentos:

  * `@OneToMany`
  * `@ManyToOne`
  * `@EmbeddedId`
  * `@MapsId`
* Consultas personalizadas utilizando `@Query`

### Spring Boot

* Controllers REST
* CRUD completo
* DTOs
* Mappers
* Services
* Repositories

### Funcionalidades Extras

* Paginação
* Filtragem dinâmica
* Validação de entrada com Bean Validation
* Tratamento global de exceções
* Documentação Swagger/OpenAPI

---

## Endpoint de Simulação de Batalha

A API possui um endpoint responsável por simular batalhas entre dois times.

Durante a simulação:

1. O sistema calcula a soma do poder de todos os movimentos dos pokémons do Time 1.
2. O sistema calcula a soma do poder de todos os movimentos dos pokémons do Time 2.
3. O time com maior poder total é definido como vencedor.
4. O resultado da batalha é atualizado automaticamente.

Exemplo:

```http
POST /batalha/{id}/simular
```

---

## Documentação da API

Após executar o projeto:

```http
http://localhost:8080/swagger-ui.html
```

ou

```http
http://localhost:8080/swagger-ui/index.html
```

---

## Autor

Pedro Henrique

Projeto acadêmico desenvolvido para a disciplina de Desenvolvimento de Aplicações Web (DAW).
