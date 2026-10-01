package br.com.fiap.dao;

import br.com.fiap.to.PokemonTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class PokemonDAO {

    // "banco de dados" em memória, compartilhado entre as requisições
    private static final ArrayList<PokemonTO> pokemons = new ArrayList<>();
    private static long proximoCodigo = 1;

    static {
        pokemons.add(new PokemonTO(LocalDate.parse("2024-01-10"), "Rato", 6.0, 0.4, "Pikachu", proximoCodigo++));
        pokemons.add(new PokemonTO(LocalDate.parse("2024-02-15"), "Semente", 6.9, 0.7, "Bulbasaur", proximoCodigo++));
        pokemons.add(new PokemonTO(LocalDate.parse("2024-03-20"), "Lagarto", 8.5, 0.6, "Charmander", proximoCodigo++));
    }

    public ArrayList<PokemonTO> findAll() {
        return new ArrayList<>(pokemons);
    }

    public PokemonTO findByCodigo(Long codigo) {
        for (PokemonTO pokemon : pokemons) {
            if (pokemon.getCodigo().equals(codigo)) {
                return pokemon;
            }
        }
        return null;
    }

    public PokemonTO save(PokemonTO pokemon) {
        pokemon.setCodigo(proximoCodigo++);
        pokemons.add(pokemon);
        return pokemon;
    }
}
