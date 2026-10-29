# Pokedex - Front-end

Interface em React que consome a API REST da Pokedex.

## Requisitos

- Node.js 20 ou superior
- A API Spring Boot rodando em `http://localhost:8080`

## Instalacao

```bash
npm install
```

## Configuracao

O endereco da API fica no arquivo `.env`:

```
VITE_API_URL=http://localhost:8080/pokedex
```

Para apontar para outro ambiente, copie `.env.example` para `.env` e altere o valor.

## Execucao

```bash
npm run dev
```

A aplicacao abre em `http://localhost:5173`.

## Build de producao

```bash
npm run build
```

Os arquivos sao gerados em `dist/`.

## Estrutura

```
src/
├── main.jsx                 ponto de entrada da aplicacao
├── App.jsx                  componente raiz
├── services/                acesso a API
│   ├── api.js               requisicoes e tratamento de erro
│   └── pokemonService.js    um metodo por endpoint
├── hooks/
│   └── usePokemons.js       estado e orquestracao das operacoes
├── components/              componentes de apresentacao
│   ├── Header/
│   ├── Alert/
│   ├── PokemonForm/
│   ├── PokemonList/
│   └── PokemonCard/
├── pages/
│   └── Pokedex.jsx          monta a tela e liga o hook aos componentes
├── utils/
│   └── formatters.js        formatacao e conversao de dados
└── styles/
    └── global.css           variaveis de tema e estilos base
```

## Endpoints consumidos

| Operacao | Verbo | Rota |
| --- | --- | --- |
| Listar | GET | `/pokedex` |
| Buscar por codigo | GET | `/pokedex/{codigo}` |
| Cadastrar | POST | `/pokedex` |
| Alterar | PUT | `/pokedex/{codigo}` |
| Excluir | DELETE | `/pokedex/{codigo}` |
