package org.serratec.music.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.music.domain.Musica;
import org.serratec.music.repository.MusicaRepository;
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
@RequestMapping("/musicas")
@Validated
@Tag(name = "Música", description = "Endpoints para gerenciamento de músicas e seus artistas")
public class MusicaController {

	@Autowired
	private MusicaRepository musicaRepository;

	@Operation(summary = "Listar todas as músicas", description = "Retorna uma lista com todas as músicas cadastradas no sistema.")
	@GetMapping
	public ResponseEntity<List<Musica>> listar() {
		return ResponseEntity.ok(musicaRepository.findAll());
	}

	@Operation(summary = "Buscar música por ID", description = "Retorna uma música específica a partir do ID informado.")
	@GetMapping("/{id}")
	public ResponseEntity<Musica> buscarId(@PathVariable Long id) {
		Optional<Musica> musica = musicaRepository.findById(id);
		if (musica.isPresent()) {
			return ResponseEntity.ok(musica.get());
		}
		return ResponseEntity.notFound().build();
	}

	@Operation(summary = "Cadastrar nova música", description = "Cria uma nova música no sistema, com gênero e artistas associados.")
	@PostMapping
	public ResponseEntity<Musica> criar(@Valid @RequestBody Musica musica) {
		Musica salvo = musicaRepository.save(musica);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}

	@Operation(summary = "Atualizar música existente", description = "Atualiza os dados de uma música existente, como título, gênero e artistas a partir do ID informado.")
	@PutMapping("/{id}")
	public ResponseEntity<Musica> atualizar(@PathVariable Long id, @Valid @RequestBody Musica musica) {
		if (!musicaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		musica.setId(id);
		Musica atualizada = musicaRepository.save(musica);
		return ResponseEntity.ok(atualizada);
	}

	@Operation(summary = "Excluir música", description = "Deleta uma música do sistema a partir do ID informado.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (!musicaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		musicaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
