package com.concredito.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.concredito.demo.entities.Archivo;

public interface ArchivoRepository extends JpaRepository<Archivo, Long> {

}
