import { BASE_URL } from '../../services/api.js';
import styles from './Header.module.css';

const TEXTO_STATUS = {
  verificando: 'Verificando...',
  conectada: 'API conectada',
  offline: 'API fora do ar'
};

function textoDoStatus(online) {
  if (online === null) return TEXTO_STATUS.verificando;
  return online ? TEXTO_STATUS.conectada : TEXTO_STATUS.offline;
}

export default function Header({ online }) {
  const aceso = online === true;

  return (
    <header className={styles.aparelho}>
      <div className={styles.lente} />

      <div className={styles.luzes}>
        <span className={`${styles.luz} ${aceso ? styles.vermelha : ''}`} />
        <span className={`${styles.luz} ${aceso ? styles.amarela : ''}`} />
        <span className={`${styles.luz} ${aceso ? styles.verde : ''}`} />
      </div>

      <div className={styles.titulo}>
        <h1>POKEDEX</h1>
        <p>Front-end React consumindo {BASE_URL}</p>
      </div>

      <span className={styles.status}>{textoDoStatus(online)}</span>
    </header>
  );
}
