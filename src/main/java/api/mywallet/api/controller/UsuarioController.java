package api.mywallet.api.controller;

import api.mywallet.api.mapper.UsuarioMapper;
import api.mywallet.api.request.UsuarioRequest;
import api.mywallet.api.request.UsuarioUpdateRequest;
import api.mywallet.api.response.UsuarioResponse;
import api.mywallet.domain.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.stream.Stream;


@RestController
//@CrossOrigin("*")
@RequestMapping(path = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private UsuarioMapper mapper;

    @PostMapping(path ="/cadastrar")
    public ResponseEntity<UsuarioResponse> create(@Valid @RequestBody UsuarioRequest request) {
        Optional<UsuarioResponse> optUsuario = Stream.of(request)
                .map(mapper::toUsuario)
                .map(service::create)
                .map(mapper::toUsuarioResponse)
                .findFirst();

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(optUsuario.get().getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(path = "/{codigoUsuario}")
    public ResponseEntity<Optional<UsuarioResponse>> findById(@PathVariable Long codigoUsuario) {
        Optional<UsuarioResponse> optUsuario = service.findById(codigoUsuario)
                .map(mapper::toUsuarioResponse);


        return ResponseEntity.status(HttpStatus.OK).body(optUsuario);
    }

    @PutMapping(value = "{codigoUsuario}")
    public ResponseEntity<UsuarioResponse> update(@PathVariable Long codigoUsuario, @Valid @RequestBody UsuarioUpdateRequest request) {
        return Stream.of(request)
                .map(mapper::toUsuario)
                .map(paciente -> service.update(codigoUsuario, paciente))
                .map(mapper::toUsuarioResponse)
                .map(pacienteResponse -> ResponseEntity.status(HttpStatus.OK).body(pacienteResponse))
                .findFirst()
                .get();
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