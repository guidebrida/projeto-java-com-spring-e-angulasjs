package projeto.curso.manutencao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import projeto.curso.manutencao.domain.Funcionario;

/**
 * * repositorio de Funcionario padrão essa implementação
 */

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    @Transactional(readOnly=true)
    Funcionario findByUsuario(String usuario);
}
