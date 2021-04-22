package com.concredito.demo.service;

import java.util.List;
import java.util.Optional;

import com.concredito.demo.entities.Prospecto;

public interface ProspectoService {

	public Optional<Prospecto> prospectoById(Long id);

	public Prospecto createProspecto(Prospecto prospectoCreate);

	public List<Prospecto> readAllProspectos();

	public String deleteProspecto(Long id);

	public String updateProspecto(Prospecto prospectoUpdate);

	public Prospecto updateSituacion(Prospecto prospectoUpdate);

}
