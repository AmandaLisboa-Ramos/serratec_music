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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/playlists")
@Validated
public class PlaylistController {

	@Autowired
	private PlaylistRepository playlistRepository;

	@GetMapping
	public ResponseEntity<List<Playlist>> listar() {
		return ResponseEntity.ok(playlistRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Playlist> buscarPorId(@PathVariable Long id) {
		Optional<Playlist> playlist = playlistRepository.findById(id);
		if (playlist.isPresent()) {
			return ResponseEntity.ok(playlist.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Playlist> criar(@Valid @RequestBody Playlist playlist) {
		Playlist salva = playlistRepository.save(playlist);
		return ResponseEntity.status(HttpStatus.CREATED).body(salva);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Playlist> atualizar(@PathVariable Long id, @Valid @RequestBody Playlist playlist) {
		if (!playlistRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		playlist.setId(id);
		Playlist atualizada = playlistRepository.save(playlist);
		return ResponseEntity.ok(atualizada);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (!playlistRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		playlistRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
