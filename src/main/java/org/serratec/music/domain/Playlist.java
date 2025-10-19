package org.serratec.music.domain;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Playlist")
@Schema(description = "Entidade que representa uma playlist criada por um usuário.")
public class Playlist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Identificador único da playlist", example = "1")
	private Long id;

	@Column(name = "nome")
	@NotBlank(message = "O campo Nome não pode estar em branco")
	@Size(max = 80, message = "O campo Nome não pode ter mais de 80 caracteres")
	@Schema(description = "Nome da playlist", example = "Hits MPB Romântico")
	private String nome;

	@Column(name = "descricao")
	@NotBlank(message = "O campo Descriçao não pode estar em branco")
	@Size(max = 200, message = "o campo Descrição não pode ter mais de 200 caracteres.")
	@Schema(description = "Descrição opcional da playlist", example = "Músicas para quando o coração tá apaixonado.")
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	@Schema(description = "Usuário dono da playlist.")
	private Usuario usuario;

	@ManyToMany
	@JoinTable(name = "playlist_musica", joinColumns = @JoinColumn(name = "playlist_id"), inverseJoinColumns = @JoinColumn(name = "musica_id"))
	@Schema(description = "Lista de músicas que compõem a playlist.")
	private List<Musica> musicas;

	public Playlist(Long id, String nome, String descricao, List<Musica> musicas) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.musicas = musicas;
	}

	public Playlist() {
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}
}
