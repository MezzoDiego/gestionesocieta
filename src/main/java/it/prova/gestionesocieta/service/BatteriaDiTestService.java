package it.prova.gestionesocieta.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionesocieta.exception.SocietaConDipendentiAssociatiException;
import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.model.Societa;

@Service
public class BatteriaDiTestService {

	@Autowired
	private SocietaService societaService;
	@Autowired
	private DipendenteService dipendenteService;

	public void testInserimentoSocieta() throws ParseException {
		System.out.println("\n testInserimentoSocieta INIZIO...");

		// creo una nuova societa
		Societa societaInstance = new Societa("Annecchiarico S.R.L.", "Via Don Torello 56",
				new SimpleDateFormat("dd-MM-yyyy").parse("01-04-1997"));

		// la inserisco
		societaService.inserisciNuovo(societaInstance);

		// verifico corretto inserimento
		if (societaInstance.getId() == null)
			throw new RuntimeException("testInserimentoSocieta FAILED: societa non inserita.");

		System.out.println("\n testInserimentoSocieta FINE: PASSED...");

	}

	public void testFindByExampleSocieta() throws ParseException {
		System.out.println("\n testFindByExampleSocieta INIZIO...");

		// creo una nuova societa
		Societa societaInstance = new Societa("Solving Team S.R.L.", "Via Mosca 52",
				new SimpleDateFormat("dd-MM-yyyy").parse("13-06-2002"));

		// la inserisco
		societaService.inserisciNuovo(societaInstance);

		// verifico corretto inserimento
		if (societaInstance.getId() == null)
			throw new RuntimeException("testInserimentoSocieta FAILED: societa non inserita.");

		// esecuzione query di ricerca
		List<Societa> societaSearchedByExample = societaService.findByExample(societaInstance);

		// verifica corretto funzionamento query di ricerca
		if (societaSearchedByExample.size() != 1)
			throw new RuntimeException("testFindByExampleSocieta FAILED: errore nella query di ricerca.");

		System.out.println("\n testFindByExampleSocieta FINE: PASSED...");

	}

	public void testEliminazioneSocietaCustomExceptionSeDipendentiAssociati() throws ParseException {
		System.out.println("\n testEliminazioneSocietaCustomExceptionSeDipendentiAssociati INIZIO...");

		// creo una nuova societa
		Societa societaInstance = new Societa("Solving Team S.R.L.", "Via Mosca 52",
				new SimpleDateFormat("dd-MM-yyyy").parse("13-06-2002"));

		// la inserisco
		societaService.inserisciNuovo(societaInstance);

		// verifico corretto inserimento
		if (societaInstance.getId() == null)
			throw new RuntimeException(
					"testEliminazioneSocietaCustomExceptionSeDipendentiAssociati FAILED: societa non inserita.");

		// creo nuovo dipendente e lo associo alla societa
		Dipendente dipendenteInstance = new Dipendente("Diego", "Mezzo",
				new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2002"), 50000, societaInstance);

		// salvo
		dipendenteService.inserisciNuovo(dipendenteInstance);

		// verifico corretto inserimento
		if (dipendenteInstance.getId() == null)
			throw new RuntimeException(
					"testEliminazioneSocietaCustomExceptionSeDipendentiAssociati FAILED: dipendente non inserito.");

		try {
			societaService.rimuovi(societaInstance);
		} catch (SocietaConDipendentiAssociatiException ex) {
			ex.printStackTrace();
		}

		System.out.println("\n testEliminazioneSocietaCustomExceptionSeDipendentiAssociati FINE: PASSED...");

	}

	public void testInserimentoDipendente() throws ParseException {

		System.out.println("\n testInserimentoDipendente INIZIO...");

		// creo una nuova societa
		Societa societaInstance = new Societa("Solving Team S.R.L.", "Via Mosca 52",
				new SimpleDateFormat("dd-MM-yyyy").parse("13-06-2002"));

		// la inserisco
		societaService.inserisciNuovo(societaInstance);

		// verifico corretto inserimento
		if (societaInstance.getId() == null)
			throw new RuntimeException("testInserimentoDipendente FAILED: societa non inserita.");

		// creo nuovo dipendente e lo associo alla societa
		Dipendente dipendenteInstance = new Dipendente("Diego", "Mezzo",
				new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2002"), 50000, societaInstance);

		// salvo
		dipendenteService.inserisciNuovo(dipendenteInstance);

		// verifico corretto inserimento
		if (dipendenteInstance.getId() == null)
			throw new RuntimeException("testInserimentoDipendente FAILED: dipendente non inserito.");

		System.out.println("\n testInserimentoDipendente FINE: PASSED...");

	}

	public void testModificaDipendente() throws ParseException {

		System.out.println("\n testModificaDipendente INIZIO...");

		// creo una nuova societa
		Societa societaInstance = new Societa("Solving Team S.R.L.", "Via Mosca 52",
				new SimpleDateFormat("dd-MM-yyyy").parse("13-06-2002"));

		// la inserisco
		societaService.inserisciNuovo(societaInstance);

		// verifico corretto inserimento
		if (societaInstance.getId() == null)
			throw new RuntimeException("testModificaDipendente FAILED: societa non inserita.");

		// creo nuovo dipendente e lo associo alla societa
		Dipendente dipendenteInstance = new Dipendente("Diego", "Mezzo",
				new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2002"), 50000, societaInstance);

		// salvo
		dipendenteService.inserisciNuovo(dipendenteInstance);

		// verifico corretto inserimento
		if (dipendenteInstance.getId() == null)
			throw new RuntimeException("testModificaDipendente FAILED: dipendente non inserito.");

		// esecuzione update
		String vecchioNome = dipendenteInstance.getNome();
		dipendenteInstance.setNome("Mirko");
		dipendenteService.aggiorna(dipendenteInstance);

		// verifica corretto funzionamento update
		Dipendente dipendenteRicaricatoDaDB = dipendenteService.caricaSingoloDipendente(dipendenteInstance.getId());
		if (vecchioNome.equals(dipendenteRicaricatoDaDB.getNome()))
			throw new RuntimeException("testModificaDipendente FAILED: dipendente non modificato.");

		System.out.println("\n testModificaDipendente FINE: PASSED...");

	}

	public void testCercaTutteLeSocietaConDipendentiConRALMaggioreDi() throws ParseException {

		System.out.println("\n testCercaTutteLeSocietaConDipendentiConRALMaggioreDi INIZIO...");

		// creo una nuova societa
		Societa societaInstance = new Societa("Solving Team S.R.L.", "Via Mosca 52",
				new SimpleDateFormat("dd-MM-yyyy").parse("13-06-2002"));

		// la inserisco
		societaService.inserisciNuovo(societaInstance);

		// verifico corretto inserimento
		if (societaInstance.getId() == null)
			throw new RuntimeException("testCercaTutteLeSocietaConDipendentiConRALMaggioreDi FAILED: societa non inserita.");

		// creo nuovo dipendente e lo associo alla societa
		Dipendente dipendenteInstance = new Dipendente("Diego", "Mezzo",
				new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2002"), 50000, societaInstance);

		// salvo
		dipendenteService.inserisciNuovo(dipendenteInstance);

		// verifico corretto inserimento
		if (dipendenteInstance.getId() == null)
			throw new RuntimeException("testCercaTutteLeSocietaConDipendentiConRALMaggioreDi FAILED: dipendente non inserito.");

		// creo nuovo dipendente e lo associo alla societa
		Dipendente dipendenteInstance1 = new Dipendente("Bruno", "Alighieri",
				new SimpleDateFormat("dd-MM-yyyy").parse("11-07-1975"), 20000, societaInstance);

		// salvo
		dipendenteService.inserisciNuovo(dipendenteInstance1);

		// verifico corretto inserimento
		if (dipendenteInstance1.getId() == null)
			throw new RuntimeException("testCercaTutteLeSocietaConDipendentiConRALMaggioreDi FAILED: dipendente non inserito.");
		
		//esecuzione query di ricerca
		List<Societa> listaSocietaConDipendentiAventiRALMaggioreDi = societaService.cercaTutteLeSocietaConDipendentiConRALMaggioreDi(30000);
		
		System.out.println(listaSocietaConDipendentiAventiRALMaggioreDi);

		if(listaSocietaConDipendentiAventiRALMaggioreDi.size() != 1)
			throw new RuntimeException("testCercaTutteLeSocietaConDipendentiConRALMaggioreDi FAILED: errore query di ricerca.");
		
		System.out.println("\n testCercaTutteLeSocietaConDipendentiConRALMaggioreDi FINE: PASSED...");


	}
	
	public void testCercaPerDataAssunzioneMenoRecenteEDataFondazioneSocietaMinoreDi() throws ParseException {
		
		System.out.println("\n testCercaPerDataAssunzioneMenoRecenteEDataFondazioneSocietaMinoreDi INIZIO...");
		
		// creo una nuova societa
				Societa societaInstance = new Societa("Solving Team S.R.L.", "Via Mosca 52",
						new SimpleDateFormat("dd-MM-yyyy").parse("13-06-1885"));

				// la inserisco
				societaService.inserisciNuovo(societaInstance);

				// verifico corretto inserimento
				if (societaInstance.getId() == null)
					throw new RuntimeException("testCercaPerDataAssunzioneMenoRecenteEDataFondazioneSocietaMinoreDi FAILED: societa non inserita.");

				// creo nuovo dipendente e lo associo alla societa
				Dipendente dipendenteInstance = new Dipendente("Diego", "Mezzo",
						new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2002"), 50000, societaInstance);

				// salvo
				dipendenteService.inserisciNuovo(dipendenteInstance);

				// verifico corretto inserimento
				if (dipendenteInstance.getId() == null)
					throw new RuntimeException("testCercaPerDataAssunzioneMenoRecenteEDataFondazioneSocietaMinoreDi FAILED: dipendente non inserito.");

				// creo nuovo dipendente e lo associo alla societa
				Dipendente dipendenteInstance1 = new Dipendente("Bruno", "Alighieri",
						new SimpleDateFormat("dd-MM-yyyy").parse("11-07-1975"), 20000, societaInstance);

				// salvo
				dipendenteService.inserisciNuovo(dipendenteInstance1);

				// verifico corretto inserimento
				if (dipendenteInstance1.getId() == null)
					throw new RuntimeException("testCercaPerDataAssunzioneMenoRecenteEDataFondazioneSocietaMinoreDi FAILED: dipendente non inserito.");
				
				//esecuzione query di ricerca
				Date dataConfronto = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-1990");
				Dipendente dipendentePiuAnzianoDiSocietaFondataPrimaDel = dipendenteService.cercaPerDataAssunzioneMenoRecenteEDataFondazioneSocietaMinoreDi(dataConfronto);

				//verifica corretto funzionamento
				if(dipendentePiuAnzianoDiSocietaFondataPrimaDel == null)
					throw new RuntimeException("testCercaPerDataAssunzioneMenoRecenteEDataFondazioneSocietaMinoreDi FAILED: non trovato.");
				
				System.out.println("\n testCercaPerDataAssunzioneMenoRecenteEDataFondazioneSocietaMinoreDi FINE: PASSED...");

		
	}
}
