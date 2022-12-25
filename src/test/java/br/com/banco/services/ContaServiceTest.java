package br.com.banco.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.banco.dtos.ListarContasDto;
import br.com.banco.entities.Conta;
import br.com.banco.repositorys.ContaRepository;

@SpringBootTest
public class ContaServiceTest {
	
	@Autowired
	private ContaService contaService;
	
	@MockBean
	private ContaRepository contaRepository;
	
	@Test
	public void testListarContas() {
		Conta conta = new Conta();
		conta.setNomeResponsavel("michael");
		when(contaRepository.findAll()).thenReturn(Arrays.asList(conta, conta));
		
		ListarContasDto lis = contaService.listarTodasContas();
		
		assertEquals(2, lis.getListaContas().size());
	}
}
