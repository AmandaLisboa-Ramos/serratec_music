package org.serratec.music.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.music.domain.Playlist;
import org.serratec.music.repository.PlaylistRepository;
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
@RequestMapping("/playlists")
@Validated
@Tag(name = "Playlists", description = "Endpoints para gerenciamento de playlists e associação com músicas e usuários")

public class PlaylistController {

	@Autowired
	private PlaylistRepository playlistRepository;

	@Operation(summary = "Listar todas as playlists", description = "Retorna todas as playlists criadas/cadastradas pelos usuários.")
	@GetMapping
	public ResponseEntity<List<Playlist>> listar() {
		return ResponseEntity.ok(playlistRepository.findAll());
	}

	@Operation(summary = "Buscar playlist por ID", description = "Retorna os detalhes de uma playlist específica com suas músicas e usuário a partir do ID informado.")
	@GetMapping("/{id}")
	public ResponseEntity<Playlist> buscarPorId(@PathVariable Long id) {
		Optional<Playlist> playlist = playlistRepository.findById(id);
		if (playlist.isPresent()) {
			return ResponseEntity.ok(playlist.get());
		}
		return ResponseEntity.notFound().build();
	}

	@Operation(summary = "Criar nova playlist", description = "Cria uma nova playlist que estará ligada a um usuário e contendo músicas existentes.")
	@PostMapping
	public ResponseEntity<Playlist> criar(@Valid @RequestBody Playlist playlist) {
		Playlist salva = playlistRepository.save(playlist);
		return ResponseEntity.status(HttpStatus.CREATED).body(salva);
	}

	@Operation(summary = "Atualizar playlist existente", description = "Atualiza o nome, descrição ou lista de músicas de uma playlist existente.")
	@PutMapping("/{id}")
	public ResponseEntity<Playlist> atualizar(@PathVariable Long id, @Valid @RequestBody Playlist playlist) {
		if (!playlistRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		playlist.setId(id);
		Playlist atualizada = playlistRepository.save(playlist);
		return ResponseEntity.ok(atualizada);
	}

	@Operation(summary = "Excluir playlist", description = "Deleta uma playlist do sistema com base no ID informado.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (!playlistRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		playlistRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
