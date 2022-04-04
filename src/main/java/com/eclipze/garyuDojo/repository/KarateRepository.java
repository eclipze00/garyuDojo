package com.eclipze.garyuDojo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eclipze.garyuDojo.model.Karate;

public interface KarateRepository extends JpaRepository <Karate, Long>{
	
	public List<Karate> findAllByFaixaContainingIgnoreCase(String faixa);

}
