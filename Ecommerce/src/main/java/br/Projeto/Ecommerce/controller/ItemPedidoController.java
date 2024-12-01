package br.Projeto.Ecommerce.controller;

import br.Projeto.Ecommerce.dto.ItemPedidoRequestDTO;
import br.Projeto.Ecommerce.model.ItemPedido;
import br.Projeto.Ecommerce.model.Pedido;
import br.Projeto.Ecommerce.model.Produto;
import br.Projeto.Ecommerce.repository.ItemPedidoRepository;
import br.Projeto.Ecommerce.repository.PedidoRepository;
import br.Projeto.Ecommerce.repository.ProdutoRepository;
import br.Projeto.Ecommerce.response.ItemPedidoResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping({"/api/item-pedidos"})
public class ItemPedidoController {

    @Autowired
    private ItemPedidoRepository repository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

//    @GetMapping
//    public ResponseEntity <List<ItemPedido>> findAll(){
//        List <ItemPedido> itemPedido = repository.findAll();
//        return ResponseEntity.ok(itemPedido);
//
//    }


    @GetMapping
    public ResponseEntity <List<ItemPedidoResponseDTO>> findAll() {
        List<ItemPedido> itens = repository.findAll();
        List<ItemPedidoResponseDTO> responseDTOS = itens.stream().
                map(ItemPedidoResponseDTO::new).toList();
        return  ResponseEntity.ok(responseDTOS);

    }




//   @GetMapping("/{id}")
//    public ResponseEntity <ItemPedido> findById(@PathVariable Integer id){
//      ItemPedido itempedido = repository.findById(id).orElseThrow(
//        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "item do pedido não encontrado"));
//
//              return ResponseEntity.ok(itempedido);
//    }


    @GetMapping("/{id}")
 public ResponseEntity <ItemPedidoResponseDTO> findById(@PathVariable Integer id){
        ItemPedido itens = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"ITEM DO PEDIDO NÃO ENCONTRADO")
        );
        ItemPedidoResponseDTO responseDTO = new ItemPedidoResponseDTO(itens);
        return ResponseEntity.ok(responseDTO);
 }


    @PostMapping
    public ResponseEntity <ItemPedido> create(@Valid @RequestBody ItemPedidoRequestDTO dto) {
       Pedido pedido = pedidoRepository.findById(dto.pedidoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

     Produto produto = produtoRepository.findById(dto.produtoId())
         .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));


        ItemPedido itempedido = new ItemPedido();
        itempedido.setPedidoId(pedido);
        itempedido.setProdutoId(produto);

        itempedido.setPreco(dto.preco());
        itempedido.setQuantidade(dto.quantidade());


        repository.save(itempedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(itempedido);

    }

    @PutMapping("/{id}")
    public ResponseEntity <ItemPedido> update(@PathVariable Integer id, @Valid @RequestBody ItemPedidoRequestDTO dto) {
        ItemPedido itempedido = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "item do pedido não encontrado"));

        Pedido pedido = pedidoRepository.findById(dto.pedidoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

        Produto produto = produtoRepository.findById(dto.produtoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        itempedido.setPedidoId(pedido);
        itempedido.setProdutoId(produto);
        itempedido.setPreco(dto.preco());
        itempedido.setQuantidade(dto.quantidade());

        repository.save(itempedido);
        return ResponseEntity.ok(itempedido);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity <ItemPedido> delete(@PathVariable Integer id){
        ItemPedido itempedido = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "item do pedido não encontrado"));

        repository.delete(itempedido);
        return ResponseEntity.noContent().build();
    }
}
