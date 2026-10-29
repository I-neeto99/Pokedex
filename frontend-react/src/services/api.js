

export class ApiError extends Error {
  constructor(mensagem, status) {
    super(mensagem);
    this.name = 'ApiError';
    this.status = status;
  }
}

async function extrairMensagem(resposta) {
  try {
    const corpo = await resposta.json();

    if (Array.isArray(corpo.errors) && corpo.errors.length > 0) {
      return corpo.errors.map((erro) => erro.defaultMessage).join(' | ');
    }
    if (corpo.message) {
      return corpo.message;
    }
  } catch (erro) {
    return `HTTP ${resposta.status} ${resposta.statusText}`;
  }
  return `HTTP ${resposta.status} ${resposta.statusText}`;
}

export async function requisitar(caminho = '', opcoes = {}) {
  const resposta = await fetch(`${BASE_URL}${caminho}`, {
    ...opcoes,
    headers: opcoes.body ? { 'Content-Type': 'application/json' } : undefined
  });

  if (!resposta.ok) {
    throw new ApiError(await extrairMensagem(resposta), resposta.status);
  }

  if (resposta.status === 204) {
    return null;
  }

  return resposta.json();
}

export { BASE_URL };
