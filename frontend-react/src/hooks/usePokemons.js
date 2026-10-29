import { useCallback, useEffect, useState } from 'react';
import * as pokemonService from '../services/pokemonService.js';

const ERRO_DE_REDE = 'Nao foi possivel falar com a API. Verifique se ela esta rodando.';

export function usePokemons() {
  const [pokemons, setPokemons] = useState([]);
  const [carregando, setCarregando] = useState(false);
  const [aviso, setAviso] = useState(null);
  const [online, setOnline] = useState(null);

  const notificar = useCallback((texto, tipo) => {
    setAviso({ texto, tipo });
  }, []);

  const limparAviso = useCallback(() => {
    setAviso(null);
  }, []);

  const tratarFalha = useCallback(
    (erro) => {
      if (erro.name === 'ApiError') {
        setOnline(true);
        notificar(erro.message, 'erro');
        return;
      }
      setOnline(false);
      notificar(ERRO_DE_REDE, 'erro');
    },
    [notificar]
  );

  const listar = useCallback(async () => {
    setCarregando(true);
    try {
      setPokemons(await pokemonService.listarPokemons());
      setOnline(true);
    } catch (erro) {
      setPokemons([]);
      tratarFalha(erro);
    } finally {
      setCarregando(false);
    }
  }, [tratarFalha]);

  const buscarPorCodigo = useCallback(
    async (codigo) => {
      if (!codigo) {
        return listar();
      }
      setCarregando(true);
      try {
        setPokemons([await pokemonService.buscarPokemonPorCodigo(codigo)]);
        setOnline(true);
        limparAviso();
      } catch (erro) {
        setPokemons([]);
        if (erro.status === 404) {
          setOnline(true);
          notificar(`Nenhum pokemon com o codigo ${codigo}.`, 'erro');
        } else {
          tratarFalha(erro);
        }
      } finally {
        setCarregando(false);
      }
    },
    [listar, limparAviso, notificar, tratarFalha]
  );

  const cadastrar = useCallback(
    async (pokemon) => {
      setCarregando(true);
      try {
        await pokemonService.cadastrarPokemon(pokemon);
        setOnline(true);
        notificar(`${pokemon.nome} foi cadastrado.`, 'sucesso');
        await listar();
        return true;
      } catch (erro) {
        tratarFalha(erro);
        return false;
      } finally {
        setCarregando(false);
      }
    },
    [listar, notificar, tratarFalha]
  );

  const alterar = useCallback(
    async (codigo, pokemon) => {
      setCarregando(true);
      try {
        await pokemonService.alterarPokemon(codigo, pokemon);
        setOnline(true);
        notificar(`${pokemon.nome} foi alterado.`, 'sucesso');
        await listar();
        return true;
      } catch (erro) {
        tratarFalha(erro);
        return false;
      } finally {
        setCarregando(false);
      }
    },
    [listar, notificar, tratarFalha]
  );

  const excluir = useCallback(
    async (pokemon) => {
      setCarregando(true);
      try {
        await pokemonService.excluirPokemon(pokemon.codigo);
        setOnline(true);
        notificar(`${pokemon.nome} foi excluido.`, 'sucesso');
        await listar();
        return true;
      } catch (erro) {
        tratarFalha(erro);
        return false;
      } finally {
        setCarregando(false);
      }
    },
    [listar, notificar, tratarFalha]
  );

  useEffect(() => {
    listar();
  }, [listar]);

  useEffect(() => {
    if (aviso?.tipo !== 'sucesso') {
      return undefined;
    }
    const temporizador = setTimeout(() => setAviso(null), 4000);
    return () => clearTimeout(temporizador);
  }, [aviso]);

  return {
    pokemons,
    carregando,
    aviso,
    online,
    listar,
    buscarPorCodigo,
    cadastrar,
    alterar,
    excluir,
    limparAviso
  };
}
