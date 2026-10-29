import { useState } from 'react';
import PokemonCard from '../PokemonCard/PokemonCard.jsx';
import styles from './PokemonList.module.css';

export default function PokemonList({ pokemons, desabilitado, aoBuscar, aoListarTodos, aoEditar, aoExcluir }) {
  const [codigoBuscado, setCodigoBuscado] = useState('');

  function buscar() {
    aoBuscar(codigoBuscado);
  }

  function listarTodos() {
    setCodigoBuscado('');
    aoListarTodos();
  }

  return (
    <section className={styles.painel}>
      <h2 className={styles.cabecalho}>Pokemons cadastrados</h2>

      <div className={styles.corpo}>
        <div className={styles.busca}>
          <input
            type="number"
            min="1"
            placeholder="Buscar por codigo"
            value={codigoBuscado}
            onChange={(evento) => setCodigoBuscado(evento.target.value)}
            onKeyDown={(evento) => {
              if (evento.key === 'Enter') {
                evento.preventDefault();
                buscar();
              }
            }}
          />
          <button type="button" className="neutro" onClick={buscar} disabled={desabilitado}>
            Buscar
          </button>
          <button type="button" className="neutro" onClick={listarTodos} disabled={desabilitado}>
            Listar todos
          </button>
        </div>

        {pokemons.length === 0 ? (
          <p className={styles.vazio}>Nenhum pokemon para mostrar.</p>
        ) : (
          pokemons.map((pokemon) => (
            <PokemonCard
              key={pokemon.codigo}
              pokemon={pokemon}
              desabilitado={desabilitado}
              aoEditar={aoEditar}
              aoExcluir={aoExcluir}
            />
          ))
        )}
      </div>
    </section>
  );
}
