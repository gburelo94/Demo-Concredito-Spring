package com.concredito.demo.service;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.concredito.demo.entities.Archivo;

public interface ArchivoService {
	
	/* Métodos para manipular los archivos */
	
	public void save(MultipartFile file) throws Exception;
	
	public Resource load(String name) throws Exception;
	
	public void save(List<MultipartFile> file) throws Exception;
	
	public Stream<Path> loadAll() throws Exception;
	
	
	/* Métodos para guardar datos en la BBDD*/
	
	public Optional<Archivo> archivoById(Long id);
	
	public Archivo createArchivo(String nombre, Long idProspecto);
	
	public List<Archivo> readAllArchivos();

	public String deleteArchivo(Long id);

	public Archivo updateArchivo(Archivo archivoToUpdate);
	

}
