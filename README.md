# Pokédex

API Restful com Spring Boot para cadastro de Pokémons (uma Pokédex), feita durante o curso na FIAP.

## Estrutura (arquitetura em camadas)

- `resource/PokemonResource` — controller REST (`/pokemon`).
- `bo/PokemonBO` — regras de negócio (nome obrigatório; data de captura não pode ser futura).
- `dao/PokemonDAO` — acesso aos dados (lista em memória, com 3 pokémons de exemplo).
- `to/PokemonTO` — código, nome, altura, peso, categoria e data da captura.

## Como rodar

Precisa de Java 17+ e Maven.

```bash
mvn spring-boot:run
```

A API sobe em `http://localhost:8080`.

## Endpoints

| Método | Rota                | O que faz                                          |
|--------|---------------------|----------------------------------------------------|
| GET    | `/pokemon`          | Lista todos os pokémons                            |
| GET    | `/pokemon/{codigo}` | Busca um pokémon pelo código (404 se não existir)  |
| POST   | `/pokemon`          | Cadastra um pokémon (400 se for inválido)          |

Exemplo de cadastro:

```bash
curl -X POST http://localhost:8080/pokemon \
  -H "Content-Type: application/json" \
  -d '{"nome":"Squirtle","altura":0.5,"peso":9.0,"categoria":"Tartaruga","dataDaCaptura":"2024-04-01"}'
```

## Tecnologias

- Java 17
- Spring Boot 3 (Spring Web)
- Maven
