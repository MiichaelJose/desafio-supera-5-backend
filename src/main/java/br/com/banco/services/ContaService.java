package br.com.banco.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.dtos.GeralContaDto;
import br.com.banco.dtos.ListarContasDto;
import br.com.banco.entities.Conta;
import br.com.banco.repositorys.ContaRepository;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class ContaService {
	
	@Autowired
	private ContaRepository contaRepository;

	public void cadastrarConta(GeralContaDto contaDto) {
		Conta conta = new Conta();
		
		conta.setId(contaDto.getId());
		conta.setNomeResponsavel(contaDto.getNomeResponsavel());
		
		log.info("cadastrado com sucesso");
		
		contaRepository.save(conta);
	}

	public ListarContasDto listarTodasContas() {
		ListarContasDto lis = new ListarContasDto();
		
		List<Conta> lisContas = contaRepository.findAll();
		
		lis.setListaContas(lisContas);

		log.info("listado com sucesso");
		
		return lis;
	}

	public GeralContaDto listarContaId(Long id) {
		GeralContaDto contaDto = new GeralContaDto();
		Conta conta = contaRepository.findById(id).get();
		
		contaDto.setId(conta.getId());
		contaDto.setNomeResponsavel(conta.getNomeResponsavel());
		
		log.info("listado por id com sucesso");
		
		return contaDto;
	}

	public void deletarConta(Long id) {
		log.info("deletado com sucesso");
		
		contaRepository.deleteById(id);
	}

	public GeralContaDto alterarConta(GeralContaDto contaDto, Long id) {
		Optional<Conta> op = contaRepository.findById(id);
		
		if (op.isPresent()) {
			Conta conta = op.get();
			GeralContaDto contaDtoAlterada = new GeralContaDto();
			
			conta.setNomeResponsavel(contaDto.getNomeResponsavel());
			
			contaDtoAlterada.setId(conta.getId());
			contaDtoAlterada.setNomeResponsavel(contaDto.getNomeResponsavel());
			
			log.info("alterado com sucesso");
			
			contaRepository.save(conta);
			
			return contaDtoAlterada;
		}
		return null;
	}
}
