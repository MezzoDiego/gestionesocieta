package it.prova.gestionesocieta.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionesocieta.model.Societa;
import it.prova.gestionesocieta.repository.SocietaRepository;

@Service
public class SocietaServiceImpl implements SocietaService{
	
	@Autowired
	private SocietaRepository societaRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	public List<Societa> listAllSocieta() {
		return (List<Societa>) societaRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Societa caricaSingolaSocieta(Long id) {
		return societaRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Societa societaInstance) {
		societaRepository.save(societaInstance);
		
	}

	@Transactional
	public void inserisciNuovo(Societa societaInstance) {
		societaRepository.save(societaInstance);
		
	}

	@Transactional
	public void rimuovi(Societa societaInstance) {
		societaRepository.delete(societaInstance);
		
	}

	@Override
	public List<Societa> findByExample(Societa example) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
