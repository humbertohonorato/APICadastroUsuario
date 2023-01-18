package api.mywallet.api.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioUpdateRequest(

        @NotBlank(message = "O campo NOME é requerido")
        @Size(min = 5, max = 80)
        String nome,

        @NotBlank(message = "O campo EMAIL é requerido")
        @Email
        String email,

        @NotBlank(message = "O campo SENHA é requerido")
        String senha
) {
}
