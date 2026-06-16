package br.edu.ifpb.es.daw.todo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Pokemon API")
                        .description("API REST do projeto Sistema de Batalha Pokemon")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Pedro")
                                .url("https://github.com/PH-dev30/Pokemon-JPA"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0")))
                .tags(Arrays.asList(
                        new Tag().name("pokemon").description("API Pokémon"),
                        new Tag().name("movimento").description("API Movimento"),
                        new Tag().name("treinador").description("API Treinador"),
                        new Tag().name("time").description("API Time"),
                        new Tag().name("ginasio").description("API Ginásio"),
                        new Tag().name("insignia").description("API Insígnia"),
                        new Tag().name("item").description("API Item"),
                        new Tag().name("batalha").description("API Batalha"),
                        new Tag().name("selecao").description("API Seleção")
                ));
    }
}