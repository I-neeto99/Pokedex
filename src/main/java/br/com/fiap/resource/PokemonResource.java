package br.com.fiap.resource;

import br.com.fiap.bo.PokemonBO;
import br.com.fiap.to.PokemonTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pokedex")
public class PokemonResource {
    private final PokemonBO pokemonBO = new PokemonBO();

    @GetMapping
    public ResponseEntity<List<PokemonTO>> findAll() {
        List<PokemonTO> pokemons = pokemonBO.findAll();
        if (pokemons == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(pokemons);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<PokemonTO> findByCodigo(@PathVariable Long codigo) {
        PokemonTO pokemon = pokemonBO.findByCodigo(codigo);
        if (pokemon != null) {
            return ResponseEntity.status(HttpStatus.OK).body(pokemon);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<PokemonTO> save(@Valid @RequestBody PokemonTO pokemon) {
        PokemonTO resultado = pokemonBO.save(pokemon);
        if (resultado != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<PokemonTO> update(@PathVariable Long codigo, @Valid @RequestBody PokemonTO pokemon) {
        pokemon.setCodigo(codigo);
        PokemonTO resultado = pokemonBO.update(pokemon);
        if (resultado != null) {
            return ResponseEntity.status(HttpStatus.OK).body(resultado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> delete(@PathVariable Long codigo) {
        if (pokemonBO.delete(codigo)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
