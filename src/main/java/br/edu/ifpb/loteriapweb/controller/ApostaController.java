package br.edu.ifpb.loteriapweb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

		Sorteio sorteio = sorteioRepository.findById(idsorteio).get();
		mv.addObject("sorteio", sorteio);

		return mv;
	}

	@RequestMapping(value = "sorteio/{idsorteio}/formularioaposta", method = RequestMethod.POST)
	public String saveaposta(@PathVariable Integer idsorteio, ModelAndView model, Integer num1, Integer num2,
			Integer num3, Integer num4, Integer num5, Integer num6) {

		Sorteio sorteio = sorteioRepository.findById(idsorteio).get();
		model.addObject("sorteio", sorteio);

		Aposta aposta = new Aposta();
		aposta.setSorteio(sorteio);

		aposta.adicionarNumero(num1);
		aposta.adicionarNumero(num2);
		aposta.adicionarNumero(num3);
		aposta.adicionarNumero(num4);
		aposta.adicionarNumero(num5);
		aposta.adicionarNumero(num6);

		apostaRepository.save(aposta);
		sorteio.adicionarAposta(aposta);
		sorteioRepository.save(sorteio);

		return "redirect:/home";

	}
	
	@RequestMapping(value = "sorteio/{idsorteio}/sortearaposta", method = RequestMethod.POST)
	public String sortear(ModelAndView mv, @PathVariable Integer idsorteio) {
		Sorteio sorteio = sorteioRepository.findById(idsorteio).get();
		List<Integer> resultado = new ArrayList<>();
		for(int i = 0; i < 6; i++) {
			int numero = aleatoriar(1, 60);
			sorteio.adicionarDezenasSorteadas(numero);
			resultado.add(numero);
		}
		sorteio.setFoiSorteado(true);
		sorteioRepository.save(sorteio);
		System.out.println(resultado.toString());
		mv.addObject("sorteio", sorteio);
		mv.addObject("resultado", resultado);
		return "redirect:/home";
	}
	
	 public static int aleatoriar(int minimo, int maximo) {
	        Random random = new Random();
	        return random.nextInt((maximo - minimo) + 1) + minimo;
	    }
}


