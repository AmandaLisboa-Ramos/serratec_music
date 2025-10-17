package org.serratec.music.domain;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome")
	@NotBlank(message = "O campo Nome não pode estar em branco")
	@Size(max = 100, message = "O campo nome deve ter no máximo 100 caracteres")
	private String nome;

	@Column(name = "email",unique = true)
	@Email(message = "O seu Email é inválido")
	@NotBlank(message = "O campo Email não pode estar em branco")
	@Size(max = 80, message = "O campo Email não pode ter mais de 80 caracteres")
	private String email;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "perfil_id")
	@Valid
	private Perfil perfil;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Playlist> playlists;

	public Usuario(Long id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Playlist> getPlaylist() {
		return playlists;
	}

	public void setPlaylist(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	public Usuario() {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
