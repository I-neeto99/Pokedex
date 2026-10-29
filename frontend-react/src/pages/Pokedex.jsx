import { useState } from 'react';
import Alert from '../components/Alert/Alert.jsx';
import Header from '../components/Header/Header.jsx';
import PokemonForm from '../components/PokemonForm/PokemonForm.jsx';
import PokemonList from '../components/PokemonList/PokemonList.jsx';
import { usePokemons } from '../hooks/usePokemons.js';
import { FORMULARIO_VAZIO, pokemonParaFormulario } from '../utils/formatters.js';
import styles from './Pokedex.module.css';

export default function Pokedex() {
  const { pokemons, carregando, aviso, online, listar, buscarPorCodigo, cadastrar, alterar, excluir, limparAviso } =
    usePokemons();

  const [emEdicao, setEmEdicao] = useState(null);
  const [valoresIniciais, setValoresIniciais] = useState(FORMULARIO_VAZIO);

  function cancelarEdicao() {
    setEmEdicao(null);
    setValoresIniciais(FORMULARIO_VAZIO);
  }

  function iniciarEdicao(pokemon) {
    setEmEdicao(pokemon.codigo);
    setValoresIniciais(pokemonParaFormulario(pokemon));
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  async function salvar(pokemon) {
    const sucesso = emEdicao === null ? await cadastrar(pokemon) : await alterar(emEdicao, pokemon);
    if (sucesso) {
      cancelarEdicao();
    }
  }

  async function removerPokemon(pokemon) {
    const confirmado = window.confirm(`Excluir ${pokemon.nome}?`);
    if (!confirmado) {
      return;
    }
    const sucesso = await excluir(pokemon);
    if (sucesso && emEdicao === pokemon.codigo) {
      cancelarEdicao();
    }
  }

  return (
    <div className={styles.conteudo}>
      <Header online={online} />

      <Alert aviso={aviso} aoFechar={limparAviso} />

      <div className={styles.grid}>
        <PokemonForm
          valoresIniciais={valoresIniciais}
          editando={emEdicao !== null}
          desabilitado={carregando}
          aoSalvar={salvar}
          aoCancelar={cancelarEdicao}
        />

        <PokemonList
          pokemons={pokemons}
          desabilitado={carregando}
          aoBuscar={buscarPorCodigo}
          aoListarTodos={listar}
          aoEditar={iniciarEdicao}
          aoExcluir={removerPokemon}
        />
      </div>
    </div>
  );
}
