package api.mywallet.api.controller;

import api.mywallet.api.response.DadosTokenJWT;
import api.mywallet.config.security.TokenService;
import api.mywallet.api.request.UsuarioLoginRequest;
import api.mywallet.domain.service.impl.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin("*")
@RequestMapping(path = "/usuario")
public class UsuarioLoginController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping(path = "/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid UsuarioLoginRequest request) {
        var token = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha());
        var authentication = manager.authenticate(token);
        var tokenJWT = tokenService.gerarToken((UserDetailsImpl) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

}
