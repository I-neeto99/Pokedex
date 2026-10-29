package br.com.fiap.bo;

import br.com.fiap.dao.PokemonDAO;
import br.com.fiap.to.PokemonTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class PokemonBO {
    private final PokemonDAO pokemonDAO = new PokemonDAO();

    public ArrayList<PokemonTO> findAll() {
        return pokemonDAO.findAll();
    }

    public PokemonTO findByCodigo(Long codigo) {
        if (codigo == null || codigo <= 0) {
            return null;
        }
        return pokemonDAO.findByCodigo(codigo);
    }

    public PokemonTO save(PokemonTO pokemon) {
        if (!dataDaCapturaValida(pokemon)) {
            return null;
        }
        return pokemonDAO.save(pokemon);
    }

    public PokemonTO update(PokemonTO pokemon) {
        if (!dataDaCapturaValida(pokemon)) {
            return null;
        }
        return pokemonDAO.update(pokemon);
    }

    public boolean delete(Long codigo) {
        if (codigo == null || codigo <= 0) {
            return false;
        }
        return pokemonDAO.delete(codigo);
    }

    private boolean dataDaCapturaValida(PokemonTO pokemon) {
        return pokemon.getDataDaCaptura() != null
                && pokemon.getDataDaCaptura().isBefore(LocalDate.now());
    }
}
