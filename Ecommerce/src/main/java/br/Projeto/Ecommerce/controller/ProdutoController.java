package br.Projeto.Ecommerce.controller;


import br.Projeto.Ecommerce.dto.ProdutoRequestDTO;
import br.Projeto.Ecommerce.model.Produto;
import br.Projeto.Ecommerce.repository.ProdutoRepository;
import br.Projeto.Ecommerce.response.ProdutoResponseDTO;
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
    public ResponseEntity <List<ProdutoResponseDTO>> findAll() {
        List<ProdutoResponseDTO> produto = repository.findAll()
                .stream().map(ProdutoResponseDTO::new).toList();//Transforma cada produto em um objeto de responsedto
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> findById(@PathVariable Integer id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
        return ResponseEntity.ok(new ProdutoResponseDTO(produto));
    }


    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> save(@Valid @RequestBody ProdutoRequestDTO dto){

        Produto produto = new Produto();
        produto.setName(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setPreco(dto.preco());
        produto.setEstoque(dto.estoque());



        Produto savedProduto = this.repository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProdutoResponseDTO(savedProduto));// salva e retorna apenas campos essenciais

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
        repository.delete(produto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> update(@PathVariable Integer id,@Valid @RequestBody ProdutoRequestDTO dto){
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        produto.setName(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setPreco(dto.preco());
        produto.setEstoque(dto.estoque());


        Produto savedProduto = this.repository.save(produto);
        return ResponseEntity.ok(new ProdutoResponseDTO(savedProduto));
    }
}
