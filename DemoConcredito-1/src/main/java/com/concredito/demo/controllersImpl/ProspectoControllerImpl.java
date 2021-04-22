package com.concredito.demo.controllersImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.concredito.demo.controllers.ProspectoController;
import com.concredito.demo.entities.Prospecto;
import com.concredito.demo.service.ProspectoService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PATCH, RequestMethod.DELETE})
public class ProspectoControllerImpl implements ProspectoController {

	@Autowired
	ProspectoService prospectoService;
	
	/* http://localhost:8080/prospectos (GET) */
	@RequestMapping(value = "/prospectos", method = RequestMethod.GET, produces = "application/json")
	@Override
	public List<Prospecto> getProspectos() {
		return prospectoService.readAllProspectos();
	}

	/* http://localhost:8080/prospectos/1 (GET) */
	@RequestMapping(value = "/prospectos/{id}", method = RequestMethod.GET, produces = "application/json")
	@Override
	public Optional<Prospecto> getProspectosById(@PathVariable Long id) {
		return prospectoService.prospectoById(id);
	}
	
	/* http://localhost:8080/prospectos/add (POST) */
	@RequestMapping(value = "/prospectos/add", method = RequestMethod.POST, produces = "application/json")
	@Override
	public Prospecto addProspecto(@RequestBody Prospecto prospecto) {
		return prospectoService.createProspecto(prospecto);
	}

	/* http://localhost:8080/prospectos/delete/1 (GET) */
	@RequestMapping(value = "/prospectos/delete/{id}", method = RequestMethod.GET, produces = "application/json")
	@Override
	public String deleteProspecto(@PathVariable Long id) {
		return prospectoService.deleteProspecto(id);
	}

	/* http://localhost:8080/prospectos/update (PATCH) */
	@RequestMapping(value = "/prospectos/update", method = RequestMethod.PATCH, produces = "application/json")
	@Override
	public String updateProspecto(@RequestBody Prospecto prospectoUpdate) {
		return prospectoService.updateProspecto(prospectoUpdate);
	}

	/* http://localhost:8080/prospectos/cambiarsituacion (PATCH) */
	@RequestMapping(value = "/prospectos/cambiarsituacion", method = RequestMethod.PATCH, produces = "application/json")
	@Override
	public Prospecto updateSituacion(@RequestBody Prospecto prospecto) {
		return prospectoService.updateSituacion(prospecto);
	}
	
	/* http://localhost:8080/test (GET) */
	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json")
	@Override
	public String test() {
		return "Test hecho.";
	}

}
