package br.com.fiap.dao;

import br.com.fiap.to.PokemonTO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PokemonDAO {

    public ArrayList<PokemonTO> findAll() {
        ArrayList<PokemonTO> pokemons = new ArrayList<>();
        String sql = "SELECT codigo, nome, altura, peso, categoria, data_da_captura FROM pokemon ORDER BY codigo";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {

            while (resultado.next()) {
                PokemonTO pokemon = new PokemonTO(
                        resultado.getLong("codigo"),
                        resultado.getString("nome"),
                        resultado.getDouble("altura"),
                        resultado.getDouble("peso"),
                        resultado.getString("categoria"),
                        resultado.getDate("data_da_captura").toLocalDate()
                );
                pokemons.add(pokemon);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar: " + e.getMessage());
            return null;
        }
        return pokemons;
    }

    public PokemonTO save(PokemonTO pokemon) {
        String sql = "INSERT INTO pokemon (codigo, nome, altura, peso, categoria, data_da_captura) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setLong(1, pokemon.getCodigo());
            comando.setString(2, pokemon.getNome());
            comando.setDouble(3, pokemon.getAltura());
            comando.setDouble(4, pokemon.getPeso());
            comando.setString(5, pokemon.getCategoria());
            comando.setDate(6, Date.valueOf(pokemon.getDataDaCaptura()));

            if (comando.executeUpdate() > 0) {
                return pokemon;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir: " + e.getMessage());
        }
        return null;
    }
    public PokemonTO findByCodigo(Long codigo) {
        String sql = "SELECT codigo, nome, altura, peso, categoria, data_da_captura FROM pokemon WHERE codigo = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setLong(1, codigo);

            try (ResultSet resultado = comando.executeQuery()) {
                if (resultado.next()) {
                    return new PokemonTO(
                            resultado.getLong("codigo"),
                            resultado.getString("nome"),
                            resultado.getDouble("altura"),
                            resultado.getDouble("peso"),
                            resultado.getString("categoria"),
                            resultado.getDate("data_da_captura").toLocalDate()
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar por codigo: " + e.getMessage());
        }
        return null;
    }
    public boolean delete(Long codigo) {
        String sql = "DELETE FROM pokemon WHERE codigo = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setLong(1, codigo);
            return comando.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        }
        return false;
    }
    public PokemonTO update(PokemonTO pokemon) {
        String sql = "UPDATE pokemon SET nome = ?, altura = ?, peso = ?, categoria = ?, data_da_captura = ? WHERE codigo = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, pokemon.getNome());
            comando.setDouble(2, pokemon.getAltura());
            comando.setDouble(3, pokemon.getPeso());
            comando.setString(4, pokemon.getCategoria());
            comando.setDate(5, Date.valueOf(pokemon.getDataDaCaptura()));
            comando.setLong(6, pokemon.getCodigo());

            if (comando.executeUpdate() > 0) {
                return pokemon;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }
        return null;
    }
}