package br.edu.ifpb.loteriapweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	
	@RequestMapping(value = "sorteio/{idsorteio}/apostas", method = RequestMethod.GET)
	public ModelAndView findById(@PathVariable Integer idsorteio, ModelAndView mv) {
		mv.setViewName("aposta/apostas");
		Sorteio sorteio = sorteioRepository.findById(idsorteio).get();
		List<Aposta> apostas = sorteio.getApostas();
		mv.addObject("apostas", apostas);
		mv.addObject("sorteio", sorteio);
		return mv;
	}
	
	@RequestMapping(value = "sorteio/{idsorteio}/formularioaposta", method = RequestMethod.GET)
	public ModelAndView formaposta(@PathVariable Integer idsorteio, ModelAndView mv) {
		mv.setViewName("aposta/criacaoaposta");
		return mv;
	}
	
	@RequestMapping(value = "sorteio/{idsorteio}/criaraposta", method = RequestMethod.POST)
	public ModelAndView saveaposta(@PathVariable Integer idsorteio, ModelAndView mv) {
	 //implementar
		return mv;
	}
}
