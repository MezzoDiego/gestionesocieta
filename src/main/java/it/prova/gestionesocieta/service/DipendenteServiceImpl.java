package it.prova.gestionesocieta.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.repository.DipendenteRepository;

@Service
public class DipendenteServiceImpl implements DipendenteService{
	
	@Autowired
	private DipendenteRepository dipendenteRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Dipendente> listAllDipendenti() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dipendente caricaSingoloDipendente(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiorna(Dipendente dipendenteInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inserisciNuovo(Dipendente dipendenteInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rimuovi(Dipendente dipendenteInstance) {
		// TODO Auto-generated method stub
		
	}

}
