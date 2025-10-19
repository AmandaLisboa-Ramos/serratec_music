package org.serratec.music.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "perfil")
@Schema(description = "Informações adicionais do usuário, como telefone e data de nascimento.")
public class Perfil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do perfil", example = "1")
	private Long id;

	@Column(name = "telefone")
	@NotBlank(message = "O campo telefone não pode estar em branco.")
	@Size(max = 14, message = "o campo telefone não pode ser mais que 14 caracteres.")
    @Schema(description = "Telefone do usuário", example = "(21)99999-9999")
	private String telefone;

	@Column(name = "data_nascimento")
	@NotNull(message = "O campo data de nascimento não pode estar em branco.")
	@Past(message = "A data de nascimento não pode ser futura.")
    @Schema(description = "Data de nascimento do usuário no formato AAAA-MM-DD", example = "2000-05-15")
	private LocalDate dataNascimento;

	@OneToOne(mappedBy = "perfil")
	@JsonIgnore
	private Usuario usuario;

	public Perfil(Long id, String telefone, LocalDate dataNascimento, Usuario usuario) {
		super();
		this.id = id;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.usuario = usuario;
	}

	public Perfil() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
