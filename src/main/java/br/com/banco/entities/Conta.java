package br.com.banco.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
public class Conta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_conta", nullable = false)
	private Long id;
	
	@Column(name = "nome_responsavel", nullable = false)
	@JsonProperty("nome_responsavel")
	private String nomeResponsavel;
	
	//@OneToMany(mappedBy = "conta")
	//@JsonManagedReference
	//private List<Transferencia> transferencias;
}
