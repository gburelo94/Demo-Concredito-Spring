package com.concredito.demo.controllersImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.concredito.demo.entities.Archivo;
import com.concredito.demo.entities.Response;
import com.concredito.demo.service.ArchivoService;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PATCH, RequestMethod.DELETE})
@RestController
public class ArchivoControllerImpl {
	
	@Autowired
	private ArchivoService archivoService;
	
	@RequestMapping(value = "/archivos/upload", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Response> uploadFiles(@RequestParam("files") List<MultipartFile> files){
		try {
			archivoService.save(files);
			return ResponseEntity.status(HttpStatus.OK).body(new Response("Los archivos fueron cargados correctamente al servidor."));
			}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Ocurrió un error al subir los archivos al servidor."));
		}		
	}
	
	@RequestMapping(value = "/archivos/{filename:.+}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Resource> getFile(@PathVariable String filename) throws Exception{
		Resource resource = archivoService.load(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
	}
	
	@RequestMapping(value = "/archivos", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Archivo>> getAllFiles() throws Exception{
		List<Archivo> files = archivoService.loadAll().map(path -> {
			String filename = path.getFileName().toString();
			String url = MvcUriComponentsBuilder.fromMethodName(ArchivoControllerImpl.class,"getFile", path.getFileName().toString()).build().toString();
			return new Archivo(url,filename);
		}).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(files);
	}
	
	@RequestMapping(value = "/archivos/add", method = RequestMethod.POST, produces = "application/json")
	public Archivo addArchivo(@RequestBody Archivo archivo) {
		String nombre = archivo.getNombre();
		Long idProspecto = archivo.getId();
		return archivoService.createArchivo(nombre, idProspecto);
	}
	
	@RequestMapping(value = "/archivos/update", method = RequestMethod.PATCH, produces = "application/json")
	public Archivo updateArchivo(@RequestBody Archivo archivoUpdate) {
		return archivoService.updateArchivo(archivoUpdate);
	}
}
