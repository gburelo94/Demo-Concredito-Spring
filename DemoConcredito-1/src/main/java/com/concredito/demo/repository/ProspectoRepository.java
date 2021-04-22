package com.concredito.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.concredito.demo.entities.Prospecto;

public interface ProspectoRepository extends JpaRepository<Prospecto, Long> {
	
}
