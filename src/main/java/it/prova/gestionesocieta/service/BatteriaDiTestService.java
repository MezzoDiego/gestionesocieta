package it.prova.gestionesocieta.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionesocieta.model.Societa;

@Service
public class BatteriaDiTestService {

	@Autowired
	private SocietaService societaService;
	@Autowired
	private DipendenteService dipendenteService;
	
	public void testInserimentoSocieta() throws ParseException {
		System.out.println("testInserimentoSocieta INIZIO...");
		
		//creo una nuova societa
		Societa societaInstance = new Societa("Annecchiarico S.R.L.", "Via Don Torello 56", new SimpleDateFormat("dd-MM-yyyy").parse("01-04-1997"));
		
		//la inserisco
		societaService.inserisciNuovo(societaInstance);
		
		//verifico corretto inserimento
		if(societaInstance.getId() == null)
			throw new RuntimeException("testInserimentoSocieta FAILED: societa non inserita.");
		
		System.out.println("testInserimentoSocieta FINE: PASSED...");

		
	}
}
