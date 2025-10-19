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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/artistas")
@Validated
@Tag(name = "Artistas", description = "Endpoints para gerenciamento de artistas musicais")
public class ArtistaController {

	@Autowired
	private ArtistaRepository artistaRepository;

	@Operation(summary = "Listar todos os artistas", description = "Retorna uma lista com todos os artistas cadastrados.")
	@GetMapping
	public ResponseEntity<List<Artista>> listar() {
		return ResponseEntity.ok(artistaRepository.findAll());
	}

	@Operation(summary = "Buscar artista por ID", description = "Retorna um artista específico a partir do ID informado.")
	@GetMapping("/{id}")
	public ResponseEntity<Artista> buscarId(@PathVariable Long id) {
		Optional<Artista> artista = artistaRepository.findById(id);
		if (artista.isPresent()) {
			return ResponseEntity.ok(artista.get());
		}
		return ResponseEntity.notFound().build();
	}

	@Operation(summary = "Cadastrar novo Artista", description = "Cria um novo Artista no sistema.")
	@PostMapping
	public ResponseEntity<Artista> criar(@Valid @RequestBody Artista artista) {
		Artista salvo = artistaRepository.save(artista);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}

	@Operation(summary = "Atualizar artista existente", description = "Atualiza os dados de um artista a partir do ID informado.")
	@PutMapping("/{id}")
	public ResponseEntity<Artista> atualizar(@PathVariable Long id, @Valid @RequestBody Artista artista) {
		if (!artistaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		artista.setId(id);
		Artista atualizado = artistaRepository.save(artista);
		return ResponseEntity.ok(atualizado);
	}

	@Operation(summary = "Excluir artista", description = "Deleta um artista do sistema a partir do ID informado.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (!artistaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		artistaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
