package br.com.fiap.bo;

import br.com.fiap.dao.PokemonDAO;
import br.com.fiap.to.PokemonTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class PokemonBO {
    private PokemonDAO pokemonDAO;

    public ArrayList<PokemonTO> findAll() {
        pokemonDAO = new PokemonDAO();
        return pokemonDAO.findAll();
    }

    public PokemonTO findByCodigo(Long codigo) {
        if (codigo == null || codigo <= 0) {
            return null;
        }
        pokemonDAO = new PokemonDAO();
        return pokemonDAO.findByCodigo(codigo);
    }

    public PokemonTO save(PokemonTO pokemon) {
        if (pokemon.getDataDaCaptura() == null || !pokemon.getDataDaCaptura().isBefore(LocalDate.now())) {
            return null;
        }
        pokemonDAO = new PokemonDAO();
        return pokemonDAO.save(pokemon);
    }
}