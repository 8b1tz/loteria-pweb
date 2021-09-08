package br.edu.ifpb.loteriapweb.dto;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.edu.ifpb.loteriapweb.model.Sorteio;

public class RequisicaoSorteio {

	@NotBlank
	@Positive
	@NotNull
	private String valorDoPremio;
	@NotBlank
	@NotNull
	@FutureOrPresent
	private String dataParaFim;
	DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	public String getValorDoPremio() {
		return valorDoPremio;
	}

	public void setValorDoPremio(String valorDoPremio) {
		this.valorDoPremio = valorDoPremio;
	}

	public String getDataParaFim() {
		return dataParaFim;
	}

	public void setDataParaFim(String dataParaFim) {
		this.dataParaFim = dataParaFim;
	}

	public Sorteio toSorteio() throws ParseException {

		Sorteio sorteio = new Sorteio();
		sorteio.setValorDoPremio(Double.parseDouble(valorDoPremio));
		sorteio.setDataParaFim(LocalDate.parse(dataParaFim, df));

		return sorteio;
	}

}
