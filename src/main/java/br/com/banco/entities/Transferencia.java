package br.com.banco.entities;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
public class Transferencia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	
	@Column(name = "data_transferencia" , nullable = false)
	@JsonProperty("data_transferencia")
	private ZonedDateTime dataTransferencia;
	
	@Column(name = "valor", nullable = false)
	private BigDecimal valor;
	
	@Column(name = "tipo", nullable = false, length = 15)
	private String tipo;
	
	@Column(name = "nome_operador_transacao", length = 50)
	@JsonProperty("nome_operador_transacao")
	private String nomeOperadorTransacao;
	
	@ManyToOne
	@JoinColumn(name = "conta_id", nullable = false)
	//@JsonBackReference // para evitar o conflito de referência
	private Conta conta;
}
