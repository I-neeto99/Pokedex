import styles from './Alert.module.css';

export default function Alert({ aviso, aoFechar }) {
  if (!aviso) {
    return null;
  }

  return (
    <div className={`${styles.aviso} ${styles[aviso.tipo]}`} role="status">
      <span>{aviso.texto}</span>
      <button type="button" className={styles.fechar} onClick={aoFechar} aria-label="Fechar aviso">
        &times;
      </button>
    </div>
  );
}
