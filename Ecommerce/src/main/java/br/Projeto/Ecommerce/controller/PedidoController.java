package br.Projeto.Ecommerce.controller;

import br.Projeto.Ecommerce.dto.PedidoRequestDTO;
import br.Projeto.Ecommerce.model.Pedido;
import br.Projeto.Ecommerce.model.Usuario;
import br.Projeto.Ecommerce.repository.PedidoRepository;
import br.Projeto.Ecommerce.repository.UsuarioRepository;
import br.Projeto.Ecommerce.response.PedidoResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private UsuarioRepository repositoryUsuario;


    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> findAll() {
        List<Pedido> pedidos = repository.findAll();
        List<PedidoResponseDTO> responseDTOs = pedidos.stream()
                .map(PedidoResponseDTO::new)
                .toList();
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> findById(@PathVariable Integer id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
        PedidoResponseDTO responseDTO = new PedidoResponseDTO(pedido);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    public ResponseEntity<Pedido> save(@Valid @RequestBody PedidoRequestDTO dto) {
        Usuario usuario = repositoryUsuario.findById(dto.usuario_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        Pedido pedido = new Pedido();
        pedido.setUsuario_id(usuario);
        pedido.setDataPedido(dto.dataPedido());
        pedido.setStatus(dto.status());
        pedido.setValorTotal(dto.valorTotal());


        this.repository.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        Pedido pedido  = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
        repository.delete(pedido);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> update(@PathVariable Integer id,@Valid @RequestBody PedidoRequestDTO dto){
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

        Usuario usuario = repositoryUsuario.findById(dto.usuario_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        pedido.setUsuario_id(usuario);
        pedido.setDataPedido(dto.dataPedido());
        pedido.setStatus(dto.status());
        pedido.setValorTotal(dto.valorTotal());

        repository.save(pedido);
        return ResponseEntity.ok(pedido);
    }

}
