package org.serratec.music.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Musica")
public class Musica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "titulo")
	@NotBlank(message = "O campo Titulo não pode estar em branco")
	@Size(max = 100, message = "O campo Titulo não pode estar em branco")
	private String titulo;

	@Column(name = "minutos")
	@NotNull(message = "O campo Minutos não pode estar em branco")
	@Max(value = 4, message = "O campo Minutos não pode ter mais de 4 números")
	private Integer minutos;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	@NotNull(message = "o Campo genero não estar em branco")
	private GeneroMusical genero;
	
	@ManyToMany
	@JoinTable(
	name = "musica_artista",
	joinColumns = @JoinColumn(name = "musica_id"),
	inverseJoinColumns = @JoinColumn(name = "artista_id"))
	private List<Artista> artistas;
	
	@ManyToMany(mappedBy = "musicas")
	private List<Playlist> playlists;

	public Musica(Long id, String titulo, Integer minutos, GeneroMusical genero) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.minutos = minutos;
		this.genero = genero;
	}

	public Musica() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getMinutos() {
		return minutos;
	}

	public void setMinutos(Integer minutos) {
		this.minutos = minutos;
	}

	public GeneroMusical getGenero() {
		return genero;
	}

	public void setGenero(GeneroMusical genero) {
		this.genero = genero;
	}
}
