package br.com.banco.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.dtos.TransferenciaDTO;
import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import br.com.banco.repositorys.TransferenciaRepository;

@RestController
@RequestMapping("/api/transferencia")
public class TransferenciaController {
	
	@Autowired
	private TransferenciaRepository transferenciaRepository;
	
	@GetMapping("/listar/paginas")
	public List<Transferencia> teste(@RequestParam int page, @RequestParam int size) {
		return transferenciaRepository.findByTipoContainingIgnoreCase(page, size);
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<?> cadastrarTransferencia(@RequestBody TransferenciaDTO transferencia) {
		Transferencia object = new Transferencia();
		Conta conta = new Conta();
		conta.setId(transferencia.getConta());
		
		object.setConta(conta);
		object.setNomeOperadorTransacao(transferencia.getNomeOperadorTransacao());
		object.setTipo(transferencia.getTipo());
		object.setValor(transferencia.getValor());

		return ResponseEntity.ok().body(transferenciaRepository.save(object));
	}
	
	@GetMapping("/listar")
	public ResponseEntity<?> listarTodasTransferencias() {
		 TransferenciaDTO a = new TransferenciaDTO();
		for (Transferencia  e : transferenciaRepository.findAll()) {
			System.out.println(e.getDataTransferencia());
		}
		
		return ResponseEntity.ok(transferenciaRepository.findAll());
	}
}
