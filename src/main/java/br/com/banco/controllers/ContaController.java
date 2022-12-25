package br.com.banco.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.dtos.GeralContaDto;
import br.com.banco.services.ContaService;

@RestController
@RequestMapping("/api/conta")
public class ContaController {
	@Autowired
	private ContaService contaService;

	@PostMapping("/cadastrar")
	public ResponseEntity<?> cadastrarConta(@RequestBody GeralContaDto contaDto) {
		contaService.cadastrarConta(contaDto);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/listar")
	public ResponseEntity<?> listarTodasContas() {
		return ResponseEntity.ok().body(contaService.listarTodasContas());
	}

	@GetMapping("/listar/{id}")
	public ResponseEntity<?> listarContaId(@PathVariable Long id) {
		return ResponseEntity.ok().body(contaService.listarContaId(id));
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<?> deletarConta(@PathVariable Long id) {
		contaService.deletarConta(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/alterar/{id}")
	public ResponseEntity<?> alterarConta(@RequestBody GeralContaDto contaDto, @PathVariable Long id) {
		
		GeralContaDto contaDtoAtualizada =  contaService.alterarConta(contaDto, id);
		
		if(contaDtoAtualizada != null) {
			return ResponseEntity.ok().body(contaDtoAtualizada);
		}
		
		return ResponseEntity.badRequest().build();
	}
}
