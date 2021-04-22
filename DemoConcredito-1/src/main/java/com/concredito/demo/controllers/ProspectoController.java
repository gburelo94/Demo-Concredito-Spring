package com.concredito.demo.controllers;

import java.util.List;
import java.util.Optional;

import com.concredito.demo.entities.Prospecto;

public interface ProspectoController {
	
	public List<Prospecto> getProspectos();
	
	public Optional<Prospecto> getProspectosById(Long id);
	
	public Prospecto addProspecto(Prospecto prospecto);
	
	public String deleteProspecto(Long id);
	
	public String updateProspecto(Prospecto prospectoUpdate);
	
	public Prospecto updateSituacion(Prospecto prospecto);
	
	public String test();

}
