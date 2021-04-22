package com.concredito.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ArchivoController {
	
	public ResponseEntity<String> uploadFiles(List<MultipartFile> files);

}
