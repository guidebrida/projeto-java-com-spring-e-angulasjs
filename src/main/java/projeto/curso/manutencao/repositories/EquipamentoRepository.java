package projeto.curso.manutencao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.curso.manutencao.domain.Equipamento;

import java.util.List;

/**
 *  * repositorio de equipamentos padrão essa implementação
 */
@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Integer>{
    List<Equipamento> findAllById(Integer equipamentoId);
}
