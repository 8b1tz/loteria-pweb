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

import br.edu.ifpb.loteriapweb.enums.StatusAposta;
import br.edu.ifpb.loteriapweb.enums.StatusSorteio;
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
			Integer num3, Integer num4, Integer num5, Integer num6,
			Integer num7, Integer num8, Integer num9, Integer num10) {

		Sorteio sorteio = sorteioRepository.findById(idsorteio).get();
		model.addObject("sorteio", sorteio);
		
		Aposta aposta = new Aposta();
		aposta.setSorteio(sorteio);
		ArrayList<Integer> numeros = new ArrayList();
		ArrayList<Integer> numerosValidos = new ArrayList();
		
		numeros.add(num1); numeros.add(num2);
		numeros.add(num3); numeros.add(num4);
		numeros.add(num5); numeros.add(num6);
		numeros.add(num7); numeros.add(num8);
		numeros.add(num9); numeros.add(num10);
		
		for(int i = 0; i < numeros.size() ; i++) {
				
			for(int j = 0; j < numeros.size() ; j++) {
					
				if((numeros.get(i) <= 60) && (numeros.get(i) >= 1 )){
					if((numeros.get(i) == numeros.get(j)) && ( i != j ) ) {
						return "redirect:/home";
						}	
					}	
				
				}
			if((numeros.get(i) <= 60) && (numeros.get(i) >= 1 )){
				numerosValidos.add(numeros.get(i));
			}
			}

		
		for(int i = 0; i < numerosValidos.size() ; i++) {
			
			if ( numerosValidos.size() >= 6 && 
				 numerosValidos.size() <= 10 ) {
					aposta.adicionarNumero(numerosValidos.get(i));	
			}
			else {
				return "redirect:/home";
			}	
		}
		
		aposta.setStatus(StatusAposta.PARTICIPANDO);

		apostaRepository.save(aposta);
		sorteio.adicionarAposta(aposta);
		sorteioRepository.save(sorteio);

		return "redirect:/home";
		}
	

	@RequestMapping(value = "sorteio/{idsorteio}/sortearaposta", method = RequestMethod.POST)
	public String sortear(ModelAndView mv, @PathVariable Integer idsorteio) {
		
		Sorteio sorteio = sorteioRepository.findById(idsorteio).get();
		List<Integer> resultado = new ArrayList<>();
		List<Aposta> apostas = sorteio.getApostas() ;
		Integer acertos = 0;
		
		// Est?? fixo em 1,2,3,4,5,6 por enquanto... s?? pra testes,
		// o  c??digo com random est?? coment??do no fim do arquivo !
		for(int i = 1; i < 7; i++) {
			int numero = i;
			sorteio.adicionarDezenasSorteadas(numero);
			resultado.add(numero);
		}


		for(int i = 0; i < apostas.size() ; i++) {
			
				List<Integer> apostaNumero = apostas.get(i).getNumeros();

				for(int j = 0; j < apostaNumero.size() ; j++) {

					if (resultado.contains(apostaNumero.get(j))) {
						acertos ++ ;
					}
				}
				
				if(acertos >= 6) {
					apostas.get(i).setStatus(StatusAposta.GANHOU);
				}
				else {
					apostas.get(i).setStatus(StatusAposta.PERDEU);
				}
				apostaRepository.save(apostas.get(i));
				acertos = 0;
			}
				
		sorteio.setFoiSorteado(true);
		sorteio.setStatus(StatusSorteio.FECHADO);
		sorteioRepository.save(sorteio);
		
		mv.addObject("sorteio", sorteio);
		mv.addObject("resultado", resultado);
				
		return "redirect:/home";
	}
	
	
	 public static int aleatoriar(int minimo, int maximo) {
	        Random random = new Random();
	        return random.nextInt((maximo - minimo) + 1) + minimo;
	    }
}

/*
for(int i = 0; i < 6; i++) {
	int numero = aleatoriar(1,60);
	sorteio.adicionarDezenasSorteadas(numero);
	resultado.add(numero);
}
*/