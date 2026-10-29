export const FORMULARIO_VAZIO = {
  codigo: '',
  nome: '',
  altura: '',
  peso: '',
  categoria: '',
  dataDaCaptura: ''
};

export function formatarCodigo(codigo) {
  return `#${String(codigo).padStart(3, '0')}`;
}

export function formatarData(dataIso) {
  if (!dataIso) {
    return '-';
  }
  const [ano, mes, dia] = dataIso.split('-');
  return `${dia}/${mes}/${ano}`;
}

export function formatarAltura(altura) {
  return `${Number(altura).toFixed(2)} m`;
}

export function formatarPeso(peso) {
  return `${Number(peso).toFixed(1)} kg`;
}

export function formularioParaPokemon(formulario) {
  return {
    codigo: Number(formulario.codigo),
    nome: formulario.nome.trim(),
    altura: Number(formulario.altura),
    peso: Number(formulario.peso),
    categoria: formulario.categoria.trim(),
    dataDaCaptura: formulario.dataDaCaptura
  };
}

export function pokemonParaFormulario(pokemon) {
  return {
    codigo: String(pokemon.codigo),
    nome: pokemon.nome,
    altura: String(pokemon.altura),
    peso: String(pokemon.peso),
    categoria: pokemon.categoria,
    dataDaCaptura: pokemon.dataDaCaptura
  };
}
