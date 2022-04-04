package com.eclipze.garyuDojo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.eclipze.garyuDojo.model.Karate;
import com.eclipze.garyuDojo.repository.KarateRepository;

@RestController
@RequestMapping("/karate")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class KarateController {
	
	@Autowired
	private KarateRepository karateRepository;
	
	@GetMapping
	public ResponseEntity<List<Karate>> getAll(){
		return ResponseEntity.ok(karateRepository.findAll());
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<Karate> findByFaixa(@PathVariable(value = "id") long id){
		return karateRepository.findById(id)
				.map(resp -> ResponseEntity.status(200).body(resp))
				.orElseGet(() -> {
					throw new ResponseStatusException(HttpStatus.NO_CONTENT, "ID não encontrado");
				});
	}
	
	@GetMapping("/faixa/{faixa}")
	public ResponseEntity<List<Karate>> getByFaixa(@PathVariable String faixa){
		return ResponseEntity.ok(karateRepository.findAllByFaixaContainingIgnoreCase(faixa));
	}

}
