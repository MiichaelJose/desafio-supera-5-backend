package br.com.banco.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.dtos.CadastrarTransferenciaDto;
import br.com.banco.dtos.ListarTransferenciasDto;
import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import br.com.banco.repositorys.TransferenciaRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransferenciaService {
	@Autowired
	private TransferenciaRepository transferenciaRepository;

	public void cadastrarTransferencia(CadastrarTransferenciaDto transferencia) {
		Transferencia object = new Transferencia();
		Conta conta = new Conta();
		
		conta.setId(transferencia.getConta());
		
		object.setConta(conta);
		object.setNomeOperadorTransacao(transferencia.getNomeOperadorTransacao());
		object.setTipo(transferencia.getTipo());
		object.setValor(transferencia.getValor());
		object.setDataTransferencia(formatarDataEntrada(transferencia.getDataTransferencia()));
		
		log.info("cadastrado com sucesso");
		
		transferenciaRepository.save(object);
	}

	public ListarTransferenciasDto listarTodasTransferencias() {
		ListarTransferenciasDto lis = new ListarTransferenciasDto();
		
		List<Transferencia> lisTransferencias = transferenciaRepository.findAll();
		
		lis.setListaTransferencias(lisTransferencias);
		lis.setTotalLinhas(transferenciaRepository.findByTotalTransferencias());
		
		log.info("listado com sucesso");
		
		return lis;
	}

	public ZonedDateTime formatarDataEntrada(String dataString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		LocalDate data = LocalDate.parse(dataString, formatter);
		ZoneId fusoHorario = ZoneId.of("America/Sao_Paulo");

		ZonedDateTime dataHoraComDeslocamento = data.atStartOfDay(fusoHorario);
		ZoneId fusoHorarioUTCPlus3 = ZoneId.of("UTC+3");
		
		dataHoraComDeslocamento = dataHoraComDeslocamento.withZoneSameInstant(fusoHorarioUTCPlus3);

		log.info("data formatada");
		
		return dataHoraComDeslocamento;
	}
	
	public Double calcularSaldoTotal(Double saldo) {
		Double calc = 0.00;
		
		calc = calc + saldo;
		
		log.info("saldo total calculado com sucesso");
		
		return calc;
	}
}
