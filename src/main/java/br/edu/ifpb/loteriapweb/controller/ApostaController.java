package br.edu.ifpb.loteriapweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.loteriapweb.model.Aposta;
import br.edu.ifpb.loteriapweb.model.Sorteio;
import br.edu.ifpb.loteriapweb.repository.ApostaRepository;
import br.edu.ifpb.loteriapweb.repository.SorteioRepository;

@Controller
public class ApostaController {

	@Autowired
	private SorteioRepository sorteioRepository;
	
	@Autowired
	private ApostaRepository apostaRepository;
	
	@GetMapping("sorteio/{idsorteio}/apostas")
	public String findById(@PathVariable Integer idsorteio, ModelAndView mv) {
		List<Aposta> apostas = sorteioRepository.findById(idsorteio).get().getApostas();;
		mv.addObject("apostas", apostaRepository.findAll());
		return "aposta/apostas";
	}
	
	@PostMapping("sorteio/{idsorteio}/criaraposta")
	public String saveAposta(@PathVariable Integer idsorteio, Aposta aposta, ModelAndView mv) {
		Sorteio sorteio = sorteioRepository.getById(idsorteio);
		sorteio.adicionarAposta(aposta);
		apostaRepository.save(aposta);
		return "aposta/apostas";
	}
}
