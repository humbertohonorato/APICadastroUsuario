package api.mywallet.repository;

import api.mywallet.model.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
    @Query("SELECT obj FROM Usuario obj WHERE obj.cpf =:cpf")
    Usuario findByCPF(@Param("cpf")String cpf);

    @Query("SELECT obj FROM Usuario obj WHERE obj.email=:email")
    Usuario findByEmail(@Param("email")String email);

    @Query("SELECT obj FROM Usuario obj WHERE obj.email=:email")
    UserDetails findByLogin(@Param("email")String email);
}
