package br.Projeto.Ecommerce.controller;

import br.Projeto.Ecommerce.dto.CategoriaRequestDTO;
import br.Projeto.Ecommerce.model.Categoria;
import br.Projeto.Ecommerce.repository.CategoriaRepository;
import br.Projeto.Ecommerce.response.CategoriaResponseDTO;
import br.Projeto.Ecommerce.response.ProdutoResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

        @Autowired
        private CategoriaRepository repository;

        @GetMapping
        public ResponseEntity<List<Categoria>> findAll() {
            List<Categoria> categoria = repository.findAll();
            return ResponseEntity.ok(categoria);
        }



        @GetMapping("/{id}")
        public Categoria findById(@PathVariable Integer id) {
            return this.repository.findById(id).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));


        }

        @PostMapping
        public ResponseEntity<Categoria> save(@Valid @RequestBody CategoriaRequestDTO dto){

            Categoria categoria = new Categoria();
            categoria.setNome(dto.nome());
            categoria.setDescricao(dto.descricao());

            this.repository.save(categoria);
            return ResponseEntity.ok(categoria);

        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Integer id){
          Categoria categoria = repository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrado"));
            repository.delete(categoria);
            return ResponseEntity.noContent().build();
        }

        @PutMapping("/{id}")
        public ResponseEntity<Categoria> update(@PathVariable Integer id,@Valid @RequestBody CategoriaRequestDTO dto){
            Categoria categoria = repository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrado"));

            categoria.setNome(dto.nome());
            categoria.setDescricao(dto.descricao());

            repository.save(categoria);
            return ResponseEntity.ok(categoria);
        }

    }


