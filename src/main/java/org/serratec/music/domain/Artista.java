package org.serratec.music.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Artista")
@Schema(description = "Representa um artista musical, podendo ter várias músicas associadas.")
public class Artista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do artista", example = "1")
	private Long id;

	@Column(name = "nome")
	@NotBlank(message = "O campo Nome não pode estar em branco")
	@Size(max = 100, message = "O campo Nome deve ter no máximo 100 caracteres")
    @Schema(description = "Nome artístico", example = "Frejat")
	private String nome;

	@Column(name = "nacionalidade")
	@NotBlank(message = "O campo Naciolidade não pode estar em branco")
	@Size(max = 50, message = "O campo Nacionalidade não pode estar em branco")
    @Schema(description = "Nacionalidade do artista", example = "Brasileira")
	private String nacionalidade;

	@ManyToMany(mappedBy = "artistas")
	@JsonIgnore
	private List<Musica> musicas;

	public Artista(Long id, String nome, String nacionalidade, List<Musica> musicas) {
		super();
		this.id = id;
		this.nome = nome;
		this.nacionalidade = nacionalidade;
		this.musicas = musicas;
	}

	public Artista() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public List<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}
}
