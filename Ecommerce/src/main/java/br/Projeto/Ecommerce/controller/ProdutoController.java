package br.Projeto.Ecommerce.controller;

import br.Projeto.Ecommerce.dto.CategoriaRequestDTO;
import br.Projeto.Ecommerce.dto.ProdutoRequestDTO;
import br.Projeto.Ecommerce.model.Categoria;
import br.Projeto.Ecommerce.model.Produto;
import br.Projeto.Ecommerce.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    public ResponseEntity <List<Produto>> findAll() {
        List<Produto> produto = repository.findAll();
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Integer id) {
        Produto produto = this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
        return ResponseEntity.ok(produto);
    }

    @PostMapping
    public ResponseEntity<Produto> save(@Valid @RequestBody ProdutoRequestDTO dto){

        Produto produto = new Produto();
        produto.setName(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setPreco(dto.preco());
        produto.setEstoque(dto.estoque());



        this.repository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
        repository.delete(produto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable Integer id,@Valid @RequestBody ProdutoRequestDTO dto){
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        produto.setName(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setPreco(dto.preco());
        produto.setEstoque(dto.estoque());


        repository.save(produto);
        return ResponseEntity.ok(produto);
    }
}
