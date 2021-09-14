package projeto.curso.manutencao.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import projeto.curso.manutencao.domain.Cliente;

/**
 *  * repositorio de cliente padrão essa implementação
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Transactional(readOnly=true)
    Cliente findByEmail(String email);
}
