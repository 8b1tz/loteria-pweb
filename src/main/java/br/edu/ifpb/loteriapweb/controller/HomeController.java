package br.edu.ifpb.loteriapweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.ifpb.loteriapweb.model.Sorteio;
import br.edu.ifpb.loteriapweb.repository.SorteioRepository;

@Controller
public class HomeController {

	@Autowired
	private SorteioRepository sorteioRepository;
	
	@GetMapping("/home")
	public String home(Model model) {
		List<Sorteio> sorteios = sorteioRepository.findAll();
		model.addAttribute("sorteios", sorteios);
		return "home";
	}
}
