import { formatarAltura, formatarCodigo, formatarData, formatarPeso } from '../../utils/formatters.js';
import styles from './PokemonCard.module.css';

export default function PokemonCard({ pokemon, desabilitado, aoEditar, aoExcluir }) {
  return (
    <article className={styles.cartao}>
      <div className={styles.numero}>{formatarCodigo(pokemon.codigo)}</div>

      <div className={styles.dados}>
        <span className={styles.nome}>{pokemon.nome}</span>
        <span className={styles.categoria}>{pokemon.categoria}</span>
        <div className={styles.medidas}>
          {formatarAltura(pokemon.altura)} &middot; {formatarPeso(pokemon.peso)} &middot; capturado em{' '}
          {formatarData(pokemon.dataDaCaptura)}
        </div>
      </div>

      <div className={styles.acoes}>
        <button type="button" className="neutro mini" onClick={() => aoEditar(pokemon)} disabled={desabilitado}>
          Alterar
        </button>
        <button type="button" className="mini" onClick={() => aoExcluir(pokemon)} disabled={desabilitado}>
          Excluir
        </button>
      </div>
    </article>
  );
}
