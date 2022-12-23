package br.com.banco.repositorys;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.banco.entities.Transferencia;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
	//LIMIT numero maximo de linhas, OFFSET pular determinado numeros de linhas
	//primeiro retorna 3 linhas e nas proximas consultas pula 3 de acordo com o numero da page
	@Query(value = "SELECT * FROM transferencia LIMIT :size OFFSET :size * (:page - 1)", nativeQuery = true)
	List<Transferencia> findByTipoContainingIgnoreCase(@Param("page") int page, @Param("size") int size);
	
	@Query(value = "SELECT * FROM transferencia WHERE data_transferencia >= :inicio AND data_transferencia <= :fim", nativeQuery = true)
	List<Transferencia> findByDataRange(@Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);
}
