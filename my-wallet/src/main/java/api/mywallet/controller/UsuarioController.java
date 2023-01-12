package api.mywallet.controller;

import api.mywallet.model.UsuarioLogin;
import api.mywallet.model.domain.Usuario;
import api.mywallet.model.dto.UsuarioDTO;
import api.mywallet.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/{codigoUsuario}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long codigoUsuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO(service.findById(codigoUsuario));
        return ResponseEntity.ok().body(usuarioDTO);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioDTO usuarioDto){
        Usuario novoUsuario = service.create(usuarioDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(novoUsuario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "{codigoUsuario}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long codigoUsuario, @Valid @RequestBody UsuarioDTO usuarioDto){
        UsuarioDTO usuarioAtualizado = new UsuarioDTO(service.update(codigoUsuario, usuarioDto));
        return ResponseEntity.ok().body(usuarioAtualizado);
    }

    @DeleteMapping(value = "/{codigoUsuario}")
    public ResponseEntity<?> delete(@PathVariable Long codigoUsuario) {
        service.delete(codigoUsuario);
        return ResponseEntity.noContent().build();
    }

//    @PostMapping("/logar")
//    public ResponseEntity<UsuarioLogin> autentication(@RequestBody Optional<UsuarioLogin> user) {
//
//        return service.logar(user).map(resp -> ResponseEntity.ok(resp))
//                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
//    }
}