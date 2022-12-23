package br.com.banco.controllers;

import java.util.Optional;

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

import br.com.banco.entities.Conta;
import br.com.banco.repositorys.ContaRepository;

@RestController
@RequestMapping("/api/conta")
public class ContaController {

	@Autowired
	private ContaRepository repository;

	@PostMapping("/cadastrar")
	public ResponseEntity<?> cadastrarConta(@RequestBody Conta conta) {
		return ResponseEntity.ok().body(repository.save(conta));
	}

	@GetMapping("/listar")
	public ResponseEntity<?> listarTodasContas() {
		return ResponseEntity.ok().body(repository.findAll());
	}

	@GetMapping("/listar/{id}")
	public ResponseEntity<?> listarTodasContas(@PathVariable Long id) {
		return ResponseEntity.ok().body(repository.findById(id));
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<?> deletarConta(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/alterar/{}")
	public ResponseEntity<?> updateById(@RequestBody Conta usuario, @PathVariable Long id) {
		Optional<Conta> op = repository.findById(id);
		if (op.isPresent()) {
			Conta obj = op.get();

			repository.save(obj);
			return ResponseEntity.ok().body(obj);
		}
		return ResponseEntity.badRequest().build();
	}
}
