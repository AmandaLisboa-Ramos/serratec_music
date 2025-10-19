
<img width="400" height="400" alt="logoSerratecMusic" src="https://github.com/user-attachments/assets/3fd55139-e018-4d09-8d37-ef550562ba4f" />

# 🎵 Serratec Music API  

**Desenvolvido por [Amanda Lisboa](https://www.linkedin.com/in/amanda-lisboa-789a42330/)**  

API RESTful desenvolvida em **Java com Spring Boot** e **PostgreSQL**, responsável por gerenciar **usuários, artistas, músicas e playlists**.  
O projeto foi desenvolvido individualmente como parte da disciplina **API**, no programa **Serratec - Residência em TIC**.  

---

## Sumário
- [Descrição](#descrição)
- [Funcionalidades](#funcionalidades)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Modelagem das Entidades](#modelagem-das-entidades)
- [Relacionamentos Implementados](#relacionamentos-implementados)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Endpoints Principais](#endpoints-principais)
- [Configuração do Banco de Dados](#configuração-do-banco-de-dados)
- [Como Executar o Projeto](#como-executar-o-projeto)
- [Documentação da API (Swagger)](#documentação-da-api-swagger)
- [🦋 Autora](#-autora)

---

## Descrição

A **Serratec Music API** foi projetada para ser o coração de uma aplicação de streaming musical.  
Ela permite o gerenciamento de **usuários**, **perfis**, **artistas**, **músicas** e **playlists**, com operações de **CRUD completo** e **relacionamentos complexos** entre as entidades.

O sistema aplica os conceitos de:
- Arquitetura em camadas (Controller, Repository, Domain, Exception e Config);
- JPA/Hibernate para persistência de dados;
- Bean Validation para validação dos campos de entrada;
- Tratamento centralizado de exceções com mensagens padronizadas;
- Documentação automática e interativa via **Swagger (Springdoc OpenAPI)**.

---

## Funcionalidades

CRUD completo para:  
- `/usuarios`  
- `/artistas`  
- `/musicas`  
- `/playlists`  

🎧 Funcionalidades adicionais:
- Criação automática de **Perfil** junto com o **Usuário** (JSON aninhado);
- Associação de **Playlists** a **Usuários** existentes;
- Atualização de músicas dentro de uma playlist (adição e remoção);
- Validação de dados com `@Valid`, `@NotBlank`, `@Email`, `@Size`, etc.;
- Tratamento centralizado de erros personalizados (`@ControllerAdvice`);
- Documentação via **Swagger UI** com descrições customizadas.

---

## Estrutura do Projeto

```
src
 └── main
      ├── java
      │    └── org.serratec.music
      │         ├── controller     # Endpoints REST (Usuário, Artista, Música, Playlist)
      │         ├── domain         # Entidades JPA e Enum GeneroMusical
      │         ├── exception      # Tratamento de exceções centralizado
      │         ├── repository     # Interfaces JpaRepository
      │         └── config         # Configuração do Swagger/OpenAPI
      │
      └── resources
           ├── application.properties  # Configurações do banco e Hibernate
           └── static / templates
```

---

## Modelagem das Entidades

| Entidade | Atributos Principais | Descrição |
|-----------|----------------------|------------|
| **Usuário** | id, nome, email | Representa o usuário do sistema |
| **Perfil** | id, telefone, dataNascimento | Dados pessoais do usuário |
| **Artista** | id, nome, nacionalidade | Artista responsável pelas músicas |
| **Música** | id, título, minutos, gênero | Música pertencente a um ou mais artistas |
| **Playlist** | id, nome, descrição | Conjunto de músicas associadas a um usuário |
| **GeneroMusical (Enum)** | AXE, FORRO, MPB, PAGODE, ROCK, SAMBA, SERTANEJO | Define o estilo musical das músicas |

---

## Relacionamentos Implementados

| Relacionamento | Tipo | Descrição |
|----------------|------|------------|
| **Usuário ↔ Perfil** | OneToOne | Um usuário possui um único perfil. |
| **Usuário ↔ Playlist** | OneToMany | Um usuário pode criar várias playlists. |
| **Música ↔ Artista** | ManyToMany | Uma música pode ter vários artistas (tabela `musica_artista`). |
| **Playlist ↔ Música** | ManyToMany | Uma playlist contém várias músicas (tabela `playlist_musica`). |

---

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Web**
- **Spring Data JPA**
- **PostgreSQL**
- **Hibernate Validator (Bean Validation)**
- **Springdoc OpenAPI (Swagger UI)**
- **Maven**
- **Jakarta Persistence / Validation**

---

## Endpoints Principais

| Método | Endpoint | Descrição |
|--------|-----------|-----------|
| `GET` | `/usuarios` | Lista todos os usuários |
| `POST` | `/usuarios` | Cria um novo usuário com perfil |
| `GET` | `/artistas` | Lista todos os artistas |
| `POST` | `/musicas` | Cria uma nova música e associa artistas |
| `POST` | `/playlists` | Cria uma playlist associada a um usuário |
| `PUT` | `/playlists/{id}` | Atualiza músicas da playlist |
| `DELETE` | `/usuarios/{id}` | Remove um usuário |

---

## Configuração do Banco de Dados

Arquivo `src/main/resources/application.properties`:

```properties
spring.application.name=serratec_music
spring.datasource.url=jdbc:postgresql://localhost:5432/serratec_music
spring.datasource.username=postgres
spring.datasource.password=1234
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> 💡 *Crie o banco `serratec_music` no PostgreSQL antes de executar o projeto.*

---

## Como Executar o Projeto

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/AmandaLisboa-Ramos/serratec_music.git
   ```
2. **Acesse o diretório:**
   ```bash
   cd serratec_music
   ```
3. **Compile e execute:**
   ```bash
   mvn spring-boot:run
   ```
4. **Acesse a documentação interativa:**
   ```
   http://localhost:8080/swagger-ui/index.html
   ```

---

## Documentação da API (Swagger)

- **Swagger UI:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)  
- **OpenAPI JSON:** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

O Swagger foi configurado com:
- Informações de contato da desenvolvedora;  
- Descrição detalhada dos endpoints e modelos;  
- Interface amigável para teste direto das rotas.

---

## 🦋 Autora

**Amanda Lisboa**  
💼 Desenvolvedora Back-end em formação  
📧 [amandalisboa.am@gmail.com](mailto:amandalisboa.am@gmail.com)  
🔗 [LinkedIn](https://www.linkedin.com/in/amanda-lisboa-789a42330/)  
💻 [GitHub](https://github.com/AmandaLisboa-Ramos)
