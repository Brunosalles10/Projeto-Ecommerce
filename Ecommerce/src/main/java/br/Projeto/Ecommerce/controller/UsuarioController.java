package br.Projeto.Ecommerce.controller;

import br.Projeto.Ecommerce.model.Usuario;
import br.Projeto.Ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/Ecommerce")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        List<Usuario> usuario = this.repository.findAll();
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/{id}")
    public Usuario findById(@PathVariable Integer id) {
            return this.repository.findById(id).orElseThrow(() ->
        new IllegalArgumentException("Usuario não encontrado"));
    }

}
