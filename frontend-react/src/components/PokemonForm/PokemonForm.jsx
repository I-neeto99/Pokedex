import { useEffect, useState } from 'react';
import { FORMULARIO_VAZIO, formatarCodigo, formularioParaPokemon } from '../../utils/formatters.js';
import styles from './PokemonForm.module.css';

export default function PokemonForm({ valoresIniciais, editando, desabilitado, aoSalvar, aoCancelar }) {
  const [formulario, setFormulario] = useState(valoresIniciais ?? FORMULARIO_VAZIO);

  useEffect(() => {
    setFormulario(valoresIniciais ?? FORMULARIO_VAZIO);
  }, [valoresIniciais]);

  function mudarCampo(nome, valor) {
    setFormulario((anterior) => ({ ...anterior, [nome]: valor }));
  }

  function enviar(evento) {
    evento.preventDefault();
    aoSalvar(formularioParaPokemon(formulario));
  }

  function renderizarCampo(nome, rotulo, tipo, atributos = {}) {
    return (
      <>
        <label className={styles.campo} htmlFor={nome}>
          {rotulo}
        </label>
        <input
          id={nome}
          name={nome}
          type={tipo}
          value={formulario[nome]}
          onChange={(evento) => mudarCampo(nome, evento.target.value)}
          disabled={desabilitado || (nome === 'codigo' && editando)}
          required
          {...atributos}
        />
      </>
    );
  }

  return (
    <section className={styles.painel}>
      <h2 className={styles.cabecalho}>
        {editando ? `Alterar pokemon ${formatarCodigo(formulario.codigo)}` : 'Cadastrar pokemon'}
      </h2>

      <div className={styles.corpo}>
        <form onSubmit={enviar}>
          {renderizarCampo('codigo', 'Codigo', 'number', { min: 1 })}
          {renderizarCampo('nome', 'Nome', 'text', { maxLength: 50 })}

          <div className={styles.duplo}>
            <div>{renderizarCampo('altura', 'Altura (m)', 'number', { step: '0.01', min: '0.01' })}</div>
            <div>{renderizarCampo('peso', 'Peso (kg)', 'number', { step: '0.1', min: '0.1' })}</div>
          </div>

          {renderizarCampo('categoria', 'Categoria', 'text', { maxLength: 50 })}
          {renderizarCampo('dataDaCaptura', 'Data da captura', 'date')}

          <div className={styles.botoes}>
            <button type="submit" disabled={desabilitado}>
              {editando ? 'Salvar' : 'Cadastrar'}
            </button>
            <button type="button" className="neutro" onClick={aoCancelar} disabled={desabilitado}>
              {editando ? 'Cancelar' : 'Limpar'}
            </button>
          </div>
        </form>

        <p className={styles.dica}>A data da captura precisa estar no passado.</p>
      </div>
    </section>
  );
}
