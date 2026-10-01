package br.com.fiap.resource;

import br.com.fiap.bo.PokemonBO;
import br.com.fiap.to.PokemonTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemon") // localhost:8080/pokemon
public class PokemonResource {
    private PokemonBO pokemonBO = new PokemonBO();

    @GetMapping
    public ResponseEntity<List<PokemonTO>> findAll() {
        List<PokemonTO> pokemons = pokemonBO.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(pokemons);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<PokemonTO> findByCodigo(@PathVariable Long codigo) {
        PokemonTO pokemon = pokemonBO.findByCodigo(codigo);
        if (pokemon != null) {
            return ResponseEntity.status(HttpStatus.OK).body(pokemon);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<PokemonTO> save(@RequestBody PokemonTO pokemon) {
        PokemonTO salvo = pokemonBO.save(pokemon);
        if (salvo != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
