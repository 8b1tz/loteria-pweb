package br.edu.ifpb.loteriapweb.controller;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifpb.loteriapweb.dto.RequisicaoSorteio;
import br.edu.ifpb.loteriapweb.model.Sorteio;
import br.edu.ifpb.loteriapweb.repository.SorteioRepository;

@Controller
@RequestMapping("sorteio")
public class SorteioController {

	@Autowired
	private SorteioRepository sorteioRepository;

	@GetMapping("criacao")
	public String formulario() {
		return "sorteio/criacao";
	}

	@PostMapping("criar")
	public String criar(@Valid RequisicaoSorteio requisicao, BindingResult resultadoValidacao) throws ParseException{
		if(resultadoValidacao.hasErrors()) {
			return "sorteio/criacao";
		}
		Sorteio sorteio = requisicao.toSorteio();
		sorteioRepository.save(sorteio);
		return "sorteio/criacao";
	}
}
