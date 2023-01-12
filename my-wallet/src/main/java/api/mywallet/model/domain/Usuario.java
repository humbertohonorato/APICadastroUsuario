package api.mywallet.model.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;

@Entity
@Table(name = "tb_usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_pessoa")
    private Long id;

    @Column(name = "nome", nullable = false, length = 80)
    @NotBlank
    @Size(min = 5, max = 80)
    private String nome;

    @Column(name = "cpf", nullable = false, length = 11)
    @CPF
    private String cpf;

    @Column(name = "email", nullable = false, length = 120)
    @NotBlank
    @Email
    @Size(min = 10, max = 80)
    private String email;

    @Column(name = "senha", nullable = false)
    @NotBlank
    @Size(min = 8)
    private String senha;
}