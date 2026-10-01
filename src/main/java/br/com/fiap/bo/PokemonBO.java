package br.com.fiap.bo;

import br.com.fiap.dao.PokemonDAO;
import br.com.fiap.to.PokemonTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class PokemonBO {
    private PokemonDAO pokemonDAO;

    public ArrayList<PokemonTO> findAll() {
        pokemonDAO = new PokemonDAO();
        // aqui se implementam as regras de negócio
        return pokemonDAO.findAll();
    }

    public PokemonTO findByCodigo(Long codigo) {
        pokemonDAO = new PokemonDAO();
        return pokemonDAO.findByCodigo(codigo);
    }

    public PokemonTO save(PokemonTO pokemon) {
        pokemonDAO = new PokemonDAO();
        // regra de negócio: nome é obrigatório e a captura não pode ser no futuro
        if (pokemon.getNome() == null || pokemon.getNome().isBlank()) {
            return null;
        }
        if (pokemon.getDataDaCaptura() != null && pokemon.getDataDaCaptura().isAfter(LocalDate.now())) {
            return null;
        }
        return pokemonDAO.save(pokemon);
    }
}
