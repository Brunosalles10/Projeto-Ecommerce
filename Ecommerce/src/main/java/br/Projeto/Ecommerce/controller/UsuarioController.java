package br.Projeto.Ecommerce.controller;


import br.Projeto.Ecommerce.dto.UsuarioRequestDTO;
import br.Projeto.Ecommerce.model.Usuario;
import br.Projeto.Ecommerce.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        List<Usuario> usuario = repository.findAll();
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/{id}")
    public Usuario findById(@PathVariable Integer id) {
            return this.repository.findById(id).orElseThrow(() ->
        new IllegalArgumentException("Usuario não encontrado"));
    }

    @PostMapping
    public ResponseEntity<Usuario> save(@Valid @RequestBody UsuarioRequestDTO dto){

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());
        usuario.setTelefone(dto.telefone());
        usuario.setEndereco(dto.endereco());

        this.repository.save(usuario);
        return ResponseEntity.ok(usuario);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        repository.delete(usuario);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Integer id,@Valid @RequestBody UsuarioRequestDTO dto){
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());
        usuario.setTelefone(dto.telefone());
        usuario.setEndereco(dto.endereco());

        repository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

}
