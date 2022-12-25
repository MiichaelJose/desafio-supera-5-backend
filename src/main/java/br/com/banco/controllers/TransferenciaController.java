package br.com.banco.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.dtos.CadastrarTransferenciaDto;
import br.com.banco.services.TransferenciaService;

@RestController
@RequestMapping("/api/transferencia")
public class TransferenciaController {
	@Autowired
	private TransferenciaService transferenciaService;
	
	@PostMapping
	public ResponseEntity<?> cadastrarTransferencia(@RequestBody CadastrarTransferenciaDto transferencia) {
		transferenciaService.cadastrarTransferencia(transferencia);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ResponseEntity<?> listarTodasTransferencias() {
		return ResponseEntity.ok().body(transferenciaService.listarTodasTransferencias());
	}
}
