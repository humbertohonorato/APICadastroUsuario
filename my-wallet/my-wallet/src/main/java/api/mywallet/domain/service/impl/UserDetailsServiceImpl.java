package api.mywallet.domain.service.impl;

import api.mywallet.domain.entity.Usuario;
import api.mywallet.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> optUsuario = repository.findByEmail(email);
        optUsuario.orElseThrow(() -> new UsernameNotFoundException(email + ": Não encontrado!."));
        return optUsuario.map(UserDetailsImpl::new).get();
    }
}
