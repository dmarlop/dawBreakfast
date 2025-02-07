package com.daw.web;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.daw.persistence.entities.Usuario;
import com.daw.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> list() {
        return ResponseEntity.ok(this.usuarioService.getAll());
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Usuario> findById(@PathVariable int idUsuario) {
        Optional<Usuario> optUsuario = this.usuarioService.getById(idUsuario);

        if (optUsuario.isPresent()) {
            return ResponseEntity.ok(optUsuario.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(this.usuarioService.save(usuario), HttpStatus.CREATED);
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<Usuario> update(@PathVariable int idUsuario, @RequestBody Usuario usuario) {
        if (idUsuario != usuario.getId()) {
            return ResponseEntity.badRequest().build();
        }
        if (!this.usuarioService.exists(idUsuario)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(this.usuarioService.save(usuario));
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> delete(@PathVariable int idUsuario) {
        if (this.usuarioService.delete(idUsuario)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{idUsuario}/cambiar-password")
    public ResponseEntity<Usuario> changePassword(@PathVariable int idUsuario, @RequestParam String nuevaPassword) {
        Optional<Usuario> optUsuario = this.usuarioService.getById(idUsuario);
        if (optUsuario.isPresent()) {
            Usuario usuario = optUsuario.get();
            usuario.setPassword(nuevaPassword);
            return ResponseEntity.ok(this.usuarioService.save(usuario));
        }
        return ResponseEntity.notFound().build();
    }
}

