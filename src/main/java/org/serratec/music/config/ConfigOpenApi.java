package org.serratec.music.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

@Configuration
public class ConfigOpenApi {

    @Bean
    public OpenAPI serratecMusicOpenAPI() {

        Contact contato = new Contact()
                .name("Amanda Lisboa")
                .email("amandalisboa.am@gmail.com")
                .url("https://amandalisboa-ramos.github.io/Portifolio/");

        Server localServer = new Server()
                .url("http://localhost:8080")
                .description("Servidor local de desenvolvimento");

        Info info = new Info()
                .title("Serratec Music API")
                .version("1.0.0")
                .description("""
                    API RESTful desenvolvida em Java com Spring Boot e PostgreSQL.
                    Permite o gerenciamento completo de usuários, artistas, músicas e playlists.
                    Projeto acadêmico desenvolvido para o Serratec - Residência em TIC.
                    """)
                .contact(contato);

        return new OpenAPI()
                .info(info)
                .servers(List.of(localServer));
    }
}