import { requisitar } from './api.js';

export function listarPokemons() {
  return requisitar();
}

export function buscarPokemonPorCodigo(codigo) {
  return requisitar(`/${codigo}`);
}

export function cadastrarPokemon(pokemon) {
  return requisitar('', {
    method: 'POST',
    body: JSON.stringify(pokemon)
  });
}

export function alterarPokemon(codigo, pokemon) {
  return requisitar(`/${codigo}`, {
    method: 'PUT',
    body: JSON.stringify(pokemon)
  });
}

export function excluirPokemon(codigo) {
  return requisitar(`/${codigo}`, {
    method: 'DELETE'
  });
}
