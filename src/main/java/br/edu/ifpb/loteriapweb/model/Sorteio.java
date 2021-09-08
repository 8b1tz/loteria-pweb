package br.edu.ifpb.loteriapweb.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.edu.ifpb.loteriapweb.enums.StatusSorteio;

@Entity
public class Sorteio {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numeroDoSorteio;
	private Double valorDoPremio;
	private LocalDate dataParaFim;
	@ElementCollection
	private List<Integer> dezenasSorteadas = new ArrayList<>();
	@Enumerated(EnumType.STRING)
	private StatusSorteio status;
	
	public Double getValorDoPremio() {
		return valorDoPremio;
	}

	public void setValorDoPremio(Double valorDoPremio) {
		this.valorDoPremio = valorDoPremio;
	}

	public LocalDate getDataParaFim() {
		return dataParaFim;
	}

	public void setDataParaFim(LocalDate dataParaFim) {
		this.dataParaFim = dataParaFim;
	}

	public List<Integer> getDezenasSorteadas() {
		return dezenasSorteadas;
	}

	public void setDezenasSorteadas(List<Integer> dezenasSorteadas) {
		this.dezenasSorteadas = dezenasSorteadas;
	}
	public void setNumeroDoSorteio(Long numeroDoSorteio) {
		this.numeroDoSorteio = numeroDoSorteio;
	}
	public Long getNumeroDoSorteio() {
		return numeroDoSorteio;
	}

}
