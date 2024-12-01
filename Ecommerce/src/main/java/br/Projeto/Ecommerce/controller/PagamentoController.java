package br.Projeto.Ecommerce.controller;

import br.Projeto.Ecommerce.dto.PagamentoDTO;
import br.Projeto.Ecommerce.model.Pagamento;
import br.Projeto.Ecommerce.model.Pedido;
import br.Projeto.Ecommerce.repository.PagamentoRepository;
import br.Projeto.Ecommerce.repository.PedidoRepository;
import br.Projeto.Ecommerce.response.PagamentoResponseDTO;
import br.Projeto.Ecommerce.response.PedidoResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping({"/api/pagamentos"})
public class PagamentoController {

    @Autowired
    private PagamentoRepository repository;

    @Autowired
    private PedidoRepository pedidoRepository;



    @GetMapping
    public ResponseEntity<List<PagamentoResponseDTO>> findAll() {
        List<Pagamento> pagamentos = repository.findAll();
        List<PagamentoResponseDTO> responseDTOs = pagamentos.stream()
                .map(PagamentoResponseDTO::new)
                .toList();
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoResponseDTO> findById(@PathVariable Integer id) {
        Pagamento pagamentos = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pagamento não encontrado"));
        PagamentoResponseDTO responseDTO = new PagamentoResponseDTO(pagamentos);
        return ResponseEntity.ok(responseDTO);
    }

//
//    @GetMapping
//    public ResponseEntity <List<Pagamento>> findAll() {
//        List <Pagamento> pagamento = repository.findAll();
//        return ResponseEntity.ok(pagamento);
//    }
//
//
//
//    @GetMapping("/{id}")
//   public ResponseEntity <Pagamento> findById(@PathVariable Integer id){
//        Pagamento pagamento = repository.findById(id).orElseThrow(
//
//                           () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pagamento não encontrado"));
//
//     return ResponseEntity.ok(pagamento);
// }

    @PostMapping
    public ResponseEntity <Pagamento> create(@Valid @RequestBody PagamentoDTO dto) {
        Pedido pedido = pedidoRepository.findById(dto.pedido())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));


        Pagamento pagamento = new Pagamento();
        pagamento.setFormaPagamento(dto.formaPagamento());
        pagamento.setValor(dto.valor());
        pagamento.setDataPagamento(dto.dataPagamento());
        pagamento.setPedido(pedido);


        repository.save(pagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(pagamento);

    }

    @PutMapping("/{id}")
    public ResponseEntity <Pagamento> udate(@PathVariable Integer id,@Valid @RequestBody PagamentoDTO dto) {
        Pedido pedido = pedidoRepository.findById(dto.pedido())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));


        Pagamento pagamento = new Pagamento();
        pagamento.setFormaPagamento(dto.formaPagamento());
        pagamento.setValor(dto.valor());
        pagamento.setDataPagamento(dto.dataPagamento());
        pagamento.setPedido(pedido);

        repository.save(pagamento);
        return ResponseEntity.ok(pagamento);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Pagamento> delete(@PathVariable Integer id){
        Pagamento pagamento = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pagamento não encontrado"));

        repository.delete(pagamento);
        return ResponseEntity.noContent().build();
    }


}
