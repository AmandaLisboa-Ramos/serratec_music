package org.serratec.music.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.music.domain.Artista;
import org.serratec.music.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/artistas")
@Validated
public class ArtistaController {

	@Autowired
	private ArtistaRepository artistaRepository;

	@GetMapping
	public ResponseEntity<List<Artista>> listar() {
		return ResponseEntity.ok(artistaRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Artista> buscarId(@PathVariable Long id) {
		Optional<Artista> artista = artistaRepository.findById(id);
		if (artista.isPresent()) {
			return ResponseEntity.ok(artista.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Artista> criar(@Valid @RequestBody Artista artista) {
		Artista salvo = artistaRepository.save(artista);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Artista> atualizar(@PathVariable Long id, @Valid @RequestBody Artista artista) {
		if (!artistaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		artista.setId(id);
		Artista atualizado = artistaRepository.save(artista);
		return ResponseEntity.ok(atualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (!artistaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		artistaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
