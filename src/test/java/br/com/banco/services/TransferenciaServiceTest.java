package br.com.banco.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.banco.entities.Transferencia;
import br.com.banco.repositorys.TransferenciaRepository;

@SpringBootTest
public class TransferenciaServiceTest {

	@Autowired
	private TransferenciaService transferenciaService;

	@MockBean
	private TransferenciaRepository transferenciaRepository;

	@Test
	public void CalcularSaldoTotalTest() {
		Transferencia transferencia = new Transferencia();
		transferencia.setValor(new BigDecimal(10.00));
		
		when(transferenciaRepository.save(any(Transferencia.class))).thenReturn(transferencia);
		
		Double valorUm = transferenciaRepository.findAll().stream().mapToDouble(t -> t.getValor().doubleValue()).sum();

		Double valorDois = transferenciaRepository.findAll().stream()
				.map(p -> transferenciaService.calcularSaldoTotal(p.getValor().doubleValue())).reduce(0.0, Double::sum);
		
		assertEquals(valorUm, valorDois);
	}
}
