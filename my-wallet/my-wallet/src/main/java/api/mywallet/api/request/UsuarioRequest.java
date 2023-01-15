package api.mywallet.api.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequest {

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
}
