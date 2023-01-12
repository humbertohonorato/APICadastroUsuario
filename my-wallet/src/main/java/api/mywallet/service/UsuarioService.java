package api.mywallet.service;

import api.mywallet.exception.DataIntegratyViolationException;
import api.mywallet.exception.ObjectNotFoundExeption;
import api.mywallet.model.UsuarioLogin;
import api.mywallet.model.domain.Usuario;
import api.mywallet.model.dto.UsuarioDTO;
import api.mywallet.repository.UsuarioRepository;
import io.micrometer.observation.ObservationFilter;
import jakarta.validation.Valid;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Optional;

@SuppressWarnings("ALL")
@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

   // private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Usuario findById(Long codigoUsuario) {
        Optional<Usuario> usuario = repository.findById(codigoUsuario);
        return usuario.orElseThrow(() -> new ObjectNotFoundExeption(
                "Usuario não encontrado! ID:" + codigoUsuario + ", Tipo: " + Usuario.class.getName()));
    }

    public Usuario create(UsuarioDTO usuarioDTO) {

        validaEmailECPF(usuarioDTO);

        return repository.save(new Usuario
                (null, usuarioDTO.getNome(), usuarioDTO.getCpf(), usuarioDTO.getEmail(),
                        usuarioDTO.getSenha()));
    }

    public Usuario update(Long codigoUsuario, @Valid UsuarioDTO usuarioDTO) {
        Usuario usuario = findById(codigoUsuario);

        if (findByEmail(usuarioDTO.getEmail()) != null
                && findByEmail(usuarioDTO.getEmail()).getId() != codigoUsuario) {
            throw new DataIntegratyViolationException("email já cadastrado na base de dados!");
        }

        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuario.getSenha());
        return repository.save(usuario);
    }

    public void delete(Long codigoUsuario) {
        findById(codigoUsuario);
        repository.deleteById(codigoUsuario);
    }

    private Usuario findByCPF(UsuarioDTO usuarioDTO) {
        Usuario usuario = repository.findByCPF(usuarioDTO.getCpf());

        if (usuario != null) {
            return usuario;
        }
        return null;
    }

    private Usuario findByEmail(String email) {
        Usuario usuario = repository.findByEmail(email);

        if (usuario != null) {
            return usuario;
        }
        return null;
    }

    private void validaEmailECPF(UsuarioDTO usuarioDTO) {
        if (findByCPF(usuarioDTO) != null) {
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
        }

        if (findByEmail(usuarioDTO.getEmail()) != null) {
            throw new DataIntegratyViolationException("EMAIL já cadastrado na base de dados!");
        }

    }

//    public Optional<UsuarioLogin> logar(Optional<UsuarioLogin> user) {
//        Usuario usuario = findByEmail(user.get().getEmail());
//
//        if (usuario.getEmail().isEmpty()){
//            throw new DataIntegratyViolationException("EMAIL não cadastrado na base de dados!");
//        }
//
//        if (encoder.matches(user.get().getSenha(),usuario.getSenha())){
//            return user;
//        }
//
//        return null;
//    }


}