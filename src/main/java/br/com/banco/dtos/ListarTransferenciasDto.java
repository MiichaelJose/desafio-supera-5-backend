package br.com.banco.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.banco.entities.Transferencia;

import lombok.Data;

@Data
public class ListarTransferenciasDto {
	@JsonProperty("lista_transferencias")
	private List<Transferencia> listaTransferencias;
	
	@JsonProperty("total_linhas")
	private Long totalLinhas;
	
	@JsonProperty("saldo_total")
	private Double saldoTotal;
}
