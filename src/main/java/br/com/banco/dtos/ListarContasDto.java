package br.com.banco.dtos;

import java.util.List;

import br.com.banco.entities.Conta;
import lombok.Data;

@Data
public class ListarContasDto {
	private List<Conta> listaContas;
}
