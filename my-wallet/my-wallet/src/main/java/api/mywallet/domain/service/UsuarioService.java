package api.mywallet.domain.service;

import api.mywallet.domain.entity.Usuario;
import api.mywallet.domain.repository.UsuarioRepository;
import api.mywallet.exception.DataIntegratyViolationException;
import api.mywallet.exception.ObjectNotFoundExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Usuario create(Usuario usuario) {
        validaCPF(usuario);
        validaEmail(usuario);
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return repository.save(usuario);
    }

    public Optional<Usuario> findById(Long codigoUsuario) {
        return Optional.ofNullable(repository.findById(codigoUsuario)
                .orElseThrow(() -> new ObjectNotFoundExeption(
                        "Usuario não encontrado! ID:" + codigoUsuario)));
    }

    public Usuario update(Long codigoUsuario, Usuario usuario) {
        Optional<Usuario> optUsuario = findById(codigoUsuario);

        if (findByEmail(usuario.getEmail()).isPresent()
                && findByEmail(usuario.getEmail()).get().getId() != codigoUsuario) {
            throw new DataIntegratyViolationException("email já cadastrado na base de dados!");
        }

        optUsuario.get().setNome(usuario.getNome());
        optUsuario.get().setEmail(usuario.getEmail());
        optUsuario.get().setSenha(usuario.getSenha());

        return repository.save(optUsuario.get());
    }

    public void delete(Long codigoUsuario) {
        this.findById(codigoUsuario);
        repository.deleteById(codigoUsuario);
    }

    private Optional<Usuario> findByCPF(String cpf) {
        return repository.findByCPF(cpf);
    }

    private Optional<Usuario> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    private void validaEmail(Usuario usuario) {
        Optional<Usuario> optUsuario = findByEmail(usuario.getEmail());
        if (optUsuario.isPresent()) {
            if (!optUsuario.get().getId().equals(usuario.getId())) {
                throw new DataIntegratyViolationException("Email já cadastrado na base de dados!");
            }
        }
    }

    private void validaCPF(Usuario usuario) {
        Optional<Usuario> optUsuario = findByCPF(usuario.getCpf());
        if (optUsuario.isPresent()) {
            if (!optUsuario.get().getId().equals(usuario.getId())) {
                throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
            }
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