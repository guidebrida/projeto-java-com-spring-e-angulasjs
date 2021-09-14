package projeto.curso.manutencao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.curso.manutencao.domain.Endereco;

/**
 *  * repositorio de endereço padrão essa implementação
 */
    @Repository
    public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{
}
