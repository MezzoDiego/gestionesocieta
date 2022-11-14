package it.prova.gestionesocieta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatteriaDiTestService {

	@Autowired
	private SocietaService societaService;
	@Autowired
	private DipendenteService dipendenteService;
}
