package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL_PADRAO = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";

    private static final String URL = valorOuPadrao("ORACLE_URL", URL_PADRAO);
    private static final String USUARIO = obrigatorio("ORACLE_USER");
    private static final String SENHA = obrigatorio("ORACLE_PASSWORD");

    private static String valorOuPadrao(String nome, String padrao) {
        String valor = System.getenv(nome);
        return (valor == null || valor.isBlank()) ? padrao : valor;
    }

    private static String obrigatorio(String nome) {
        String valor = System.getenv(nome);
        if (valor == null || valor.isBlank()) {
            throw new IllegalStateException(
                    "Variavel de ambiente " + nome + " nao definida. "
                            + "Configure ORACLE_USER e ORACLE_PASSWORD antes de iniciar a aplicacao.");
        }
        return valor;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
