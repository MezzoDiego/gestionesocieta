package it.prova.gestionesocieta.service;

import java.util.Date;
import java.util.List;

import it.prova.gestionesocieta.model.Dipendente;


public interface DipendenteService {

	public List<Dipendente> listAllDipendenti();

	public Dipendente caricaSingoloDipendente(Long id);

	public void aggiorna(Dipendente dipendenteInstance);

	public void inserisciNuovo(Dipendente dipendenteInstance);

	public void rimuovi(Dipendente dipendenteInstance);
	
	public List<Dipendente> cercaPerCognomeEager(String cognome);
	
	public Dipendente cercaPerDataAssunzioneMenoRecenteEDataFondazioneSocietaMinoreDi(Date input);
}
