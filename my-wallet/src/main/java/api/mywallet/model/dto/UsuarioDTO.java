package api.mywallet.model.dto;

import api.mywallet.model.domain.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "O campo NOME é requerido")
    @Size(min = 5, max = 80)
    private String nome;

    @NotBlank(message = "O campo CPF é requerido")
    @CPF
    private String cpf;

    @NotBlank(message = "O campo EMAIL é requerido")
    @Email
    private String email;

    @NotBlank(message = "O campo SENHA é requerido")
    private String senha;

    public UsuarioDTO(Usuario usuario) {
        super();
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.cpf = usuario.getCpf();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
    }
}
