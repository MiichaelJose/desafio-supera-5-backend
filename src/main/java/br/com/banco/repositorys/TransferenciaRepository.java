package br.com.banco.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.banco.entities.Transferencia;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
	@Query(value = "SELECT COUNT(*) FROM transferencia;", nativeQuery = true)
	Long findByTotalTransferencias();
}
