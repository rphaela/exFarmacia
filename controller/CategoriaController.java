package org.generation.exFarmacia.controller;

import java.util.List;

import org.generation.exFarmacia.model.Categoria;
import org.generation.exFarmacia.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {
	
	@Autowired
	public CategoriaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<Categoria> GetById(@PathVariable Long id) {
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
		
	@GetMapping("/uso/{uso}")
	public ResponseEntity<List<Categoria>> GetByUso (@PathVariable String uso) {
		return ResponseEntity.ok(repository.findAllByUsoContainingIgnoreCase(uso));
		}
	
	@GetMapping("/indicacao/{indicacao}")
	public ResponseEntity<List<Categoria>> GetByIndicacao (@PathVariable String indicacao) {
		return ResponseEntity.ok(repository.findAllByIndicacaoContainingIgnoreCase(indicacao));
		}
	
	@PostMapping
	public ResponseEntity<Categoria> post (@RequestBody Categoria categoria) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria));
		
	}
	
	@PutMapping
	public ResponseEntity<Categoria> put (@RequestBody Categoria categoria) {
		return ResponseEntity.ok(repository.save(categoria));
	}
	
	@DeleteMapping("/{id}")
	public void delete (@PathVariable Long id) {
		repository.deleteById(id);
	}

}
