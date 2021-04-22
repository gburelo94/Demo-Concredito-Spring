package com.concredito.demo.serviceImpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.concredito.demo.entities.Archivo;
import com.concredito.demo.repository.ArchivoRepository;
import com.concredito.demo.service.ArchivoService;

@Service
public class ArchivoServiceImpl implements ArchivoService {
	@Autowired
	ArchivoRepository archivoRepo;
	
	private final Path rootFolder = Paths.get("uploads");

	@Override
	public void save(MultipartFile file) throws Exception {
		Files.copy(file.getInputStream(), this.rootFolder.resolve(file.getOriginalFilename()));
	}

	@Override
	public Resource load(String name) throws Exception {
		Path file = rootFolder.resolve(name);
		Resource resource = new UrlResource(file.toUri());
		return resource;
	}

	@Override
	public void save(List<MultipartFile> file) throws Exception {
		for (MultipartFile f : file) {
			this.save(f);
		}
	}

	@Override
	public Stream<Path> loadAll() throws Exception {
		return Files.walk(rootFolder, 1).filter(path -> !path.equals(rootFolder)).map(rootFolder::relativize);
	}

	/*=========================================== GESTIÓN EN LA BBDD =================================================================*/
	
	@Override
	public Optional<Archivo> archivoById(Long id) {
		Optional<Archivo> archivo = archivoRepo.findById(id);
		return archivo;
	}

	@Override
	public Archivo createArchivo(String nombre, Long idProspecto) {
		if(nombre != null && idProspecto != null) {
			Archivo archivo = new Archivo();
			archivo.setNombre(nombre);
			archivo.setIdProspecto(idProspecto);
			return archivoRepo.save(archivo);
		}
		return new Archivo();
	}

	@Override
	public List<Archivo> readAllArchivos() {
		return archivoRepo.findAll();
	}

	@Override
	public String deleteArchivo(Long id) {
		if(archivoRepo.findById(id).isPresent()) {
			archivoRepo.deleteById(id);
			return "Archivo eliminado correctamente.";
		}
		return "¡Error! El archivo que usted desea eliminar no existe.";
	}

	@Override
	public Archivo updateArchivo(Archivo archivoUpdate) {
		Long id = archivoUpdate.getId();
		if(archivoRepo.findById(id).isPresent()) {
			Archivo a = new Archivo();
			Optional<Archivo> archivoToUpdate = archivoRepo.findById(id);		
			a.setId(id);
			a.setNombre(archivoToUpdate.get().getNombre());
			a.setUrl(archivoUpdate.getUrl());
			a.setIdProspecto(archivoToUpdate.get().getIdProspecto());
			return archivoRepo.save(a);
		}
		
		return new Archivo();
	}

}
