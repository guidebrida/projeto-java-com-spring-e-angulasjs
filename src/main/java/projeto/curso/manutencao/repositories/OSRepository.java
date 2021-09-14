package projeto.curso.manutencao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.curso.manutencao.domain.OrdemDeServico;

/**
 * repositorio de ordem de serviços padrão essa implementação
 */
@Repository
public interface OSRepository extends JpaRepository<OrdemDeServico, Integer> {

}
