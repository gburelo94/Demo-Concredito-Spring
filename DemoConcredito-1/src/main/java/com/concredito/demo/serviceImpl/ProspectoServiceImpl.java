package com.concredito.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concredito.demo.entities.Prospecto;
import com.concredito.demo.repository.ProspectoRepository;
import com.concredito.demo.service.ProspectoService;

@Service
public class ProspectoServiceImpl implements ProspectoService {

	@Autowired
	ProspectoRepository prospectoRepo;

	@Override
	public Optional<Prospecto> prospectoById(Long id) {
		Optional<Prospecto> prospecto = prospectoRepo.findById(id);
		return prospecto;
	}

	@Override
	public Prospecto createProspecto(Prospecto prospectoCreate) {
		if (prospectoCreate != null) {
			return prospectoRepo.save(prospectoCreate);
		}
		return new Prospecto();
	}

	@Override
	public List<Prospecto> readAllProspectos() {
		return prospectoRepo.findAll();
	}

	@Override
	public String deleteProspecto(Long id) {
		if (prospectoRepo.findById(id).isPresent()) {
			prospectoRepo.deleteById(id);
			return "Prospecto eliminado correctamente";
		}
		return "¡Error! El prospecto que usted desea eliminar no existe.";
	}

	@Override
	public String updateProspecto(Prospecto prospectoUpdate) {
		Long id = prospectoUpdate.getId();
		if (prospectoRepo.findById(id).isPresent()) {
			Prospecto prospectoToUpdate = new Prospecto();
			prospectoToUpdate.setId(prospectoUpdate.getId());
			prospectoToUpdate.setNombre(prospectoUpdate.getNombre());
			prospectoToUpdate.setApPaterno(prospectoUpdate.getApPaterno());
			prospectoToUpdate.setApMaterno(prospectoUpdate.getApMaterno());
			prospectoToUpdate.setCalle(prospectoUpdate.getCalle());
			prospectoToUpdate.setNumero(prospectoUpdate.getNumero());
			prospectoToUpdate.setColonia(prospectoUpdate.getColonia());
			prospectoToUpdate.setCodigoPostal(prospectoUpdate.getCodigoPostal());
			prospectoToUpdate.setTelefono(prospectoUpdate.getTelefono());
			prospectoToUpdate.setRfc(prospectoUpdate.getRfc());
			prospectoToUpdate.setStatus(prospectoUpdate.getStatus());
			prospectoToUpdate.setObservaciones(prospectoUpdate.getObservaciones());
			prospectoRepo.save(prospectoToUpdate);
			return "Prospecto modificado correctamente.";
		}
		return "¡Error! Ocurrió un error inesperado al modificar el Prospecto.";
	}

	@Override
	public Prospecto updateSituacion(Prospecto prospectoUpdate) {
		Long id = prospectoUpdate.getId();
		String observaciones = prospectoUpdate.getObservaciones();
		String status = prospectoUpdate.getStatus();
		
		if (prospectoRepo.findById(id).isPresent()) {
			Prospecto p = new Prospecto();
			Optional<Prospecto> prospectoToUpdate = prospectoRepo.findById(id);
			
			p.setId(prospectoToUpdate.get().getId());
			p.setNombre(prospectoToUpdate.get().getNombre());
			p.setApPaterno(prospectoToUpdate.get().getApPaterno());
			p.setApMaterno(prospectoToUpdate.get().getApMaterno());
			p.setCalle(prospectoToUpdate.get().getCalle());
			p.setNumero(prospectoToUpdate.get().getNumero());
			p.setColonia(prospectoToUpdate.get().getColonia());
			p.setCodigoPostal(prospectoToUpdate.get().getCodigoPostal());
			p.setTelefono(prospectoToUpdate.get().getTelefono());
			p.setRfc(prospectoToUpdate.get().getRfc());
			p.setStatus(prospectoToUpdate.get().getStatus());
			p.setObservaciones(prospectoToUpdate.get().getObservaciones());
			
			if (status.equals("Rechazado")) {
				p.setObservaciones(observaciones);
				p.setStatus(status);
			} else if (status.equals("Autorizado")) {
				p.setStatus(status);
			}

			return prospectoRepo.save(p);
		}
		return new Prospecto();
	}
}
