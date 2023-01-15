package api.mywallet.api.mapper;

import api.mywallet.api.request.UsuarioRequest;
import api.mywallet.api.request.UsuarioUpdateRequest;
import api.mywallet.api.response.UsuarioResponse;
import api.mywallet.domain.entity.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    @Autowired
    private ModelMapper mapper;

    public Usuario toUsuario (UsuarioRequest request){
        return mapper.map(request, Usuario.class);
    }

    public Usuario toUsuario (UsuarioUpdateRequest request){
        return mapper.map(request, Usuario.class);
    }

    public UsuarioResponse toUsuarioResponse(Usuario usuario){
        return mapper.map(usuario, UsuarioResponse.class);
    }


//    public static Usuario toUsuario(UsuarioRequest request){
//        Usuario usuario = new Usuario();
//        usuario.setNome(request.getNome());
//        usuario.setCpf(request.getCpf());
//        usuario.setEmail(request.getEmail());
//        usuario.setSenha(request.getSenha());
//        return usuario;
//    }

//    public static UsuarioResponse toUsuarioResponse(Usuario usuario){
//        UsuarioResponse response = new UsuarioResponse();
//        response.setId(usuario.getId());
//        response.setNome(usuario.getNome());
//        response.setCpf(usuario.getCpf());
//        response.setEmail(usuario.getEmail());
//        response.setSenha(usuario.getSenha());
//        return response;
//    }
}
