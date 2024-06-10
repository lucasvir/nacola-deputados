package br.com.lucasvir.nacola_deputados.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private final String schemeName = "bearerAuth";
    private final String bearerFormat = "JWT";
    private final String scheme = "bearer";

    private final SecurityScheme securityScheme = new SecurityScheme()
            .name(schemeName)
            .type(SecurityScheme.Type.HTTP)
            .bearerFormat(bearerFormat)
            .in(SecurityScheme.In.HEADER)
            .scheme(scheme);

    private Contact createContact() {
        Contact contact = new Contact();
        contact.setName("Lucas do Amaral Virmond");
        contact.setEmail("lavirmond@gmail.com");
        contact.setUrl("http://github.com/lucasvir");

        return contact;
    }

    @Bean
    public OpenAPI cadeOpenAPI() {


        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(schemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(schemeName, securityScheme)
                )
                .info(
                        new Info()
                                .title("Na Cola")
                                .description("Agregador de informação de parlamentares por estado.")
                                .version("1.0")
                );
    }
}
