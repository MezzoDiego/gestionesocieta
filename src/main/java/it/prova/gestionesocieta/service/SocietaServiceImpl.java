package it.prova.gestionesocieta.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionesocieta.model.Societa;
import it.prova.gestionesocieta.repository.SocietaRepository;

@Service
public class SocietaServiceImpl implements SocietaService{
	
	@Autowired
	private SocietaRepository societaRepository;

	@Override
	public List<Societa> listAllSocieta() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Societa caricaSingolaSocieta(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiorna(Societa societaInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inserisciNuovo(Societa societaInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rimuovi(Societa societaInstance) {
		// TODO Auto-generated method stub
		
	}
	

}
