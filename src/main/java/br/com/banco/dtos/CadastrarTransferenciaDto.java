package br.com.banco.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CadastrarTransferenciaDto {
	private String dataTransferencia;

	private BigDecimal valor;

	private String tipo;

	private String nomeOperadorTransacao;

	private Long conta;
}
