package api.mywallet.domain.repository;

import api.mywallet.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT obj FROM Usuario obj WHERE obj.cpf =:cpf")
    Optional<Usuario> findByCPF(@Param("cpf")String cpf);

    @Query("SELECT obj FROM Usuario obj WHERE obj.email=:email")
    Optional<Usuario> findByEmail(@Param("email")String email);
}
