package br.edu.ifpb.loteriapweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.loteriapweb.model.Sorteio;
import br.edu.ifpb.loteriapweb.repository.SorteioRepository;

@Controller
public class HomeController {

	@Autowired
	private SorteioRepository sorteioRepository;

	@GetMapping("/home")
	public ModelAndView home(ModelAndView mv) {
		mv.addObject("sorteios", sorteioRepository.findAll());
		return mv;
	}
}
