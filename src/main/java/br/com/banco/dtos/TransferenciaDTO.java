package br.com.banco.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import lombok.Data;

@Data
public class TransferenciaDTO {
	private String dataTransferencia;

	private BigDecimal valor;

	private String tipo;

	private String nomeOperadorTransacao;

	private Long conta;
	
	public ZonedDateTime formatarDataEntrada(String dataString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		LocalDate data = LocalDate.parse(dataString, formatter);
		ZoneId fusoHorario = ZoneId.of("America/Sao_Paulo");
		
		ZonedDateTime dataHoraComDeslocamento = data.atStartOfDay(fusoHorario);
		ZoneId fusoHorarioUTCPlus3 = ZoneId.of("UTC+3");
		dataHoraComDeslocamento = dataHoraComDeslocamento.withZoneSameInstant(fusoHorarioUTCPlus3);
		
		return dataHoraComDeslocamento;
	}
	

	public String formatarDataSaida(String dataString) {
		DateTimeFormatter formatterEntrada = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssxxx");
		
		ZonedDateTime data = ZonedDateTime.parse(dataString, formatterEntrada);
		
		DateTimeFormatter formatterSaida = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataFormatada = formatterSaida.format(data);

		
		return dataFormatada;
	}
}
