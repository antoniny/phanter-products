package br.com.awm.pantherproducts.config.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.SpringDocUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(@Value("${spring.application.name}") String appDescription, @Value("${spring.application.version}") String appVersion) {

        return new OpenAPI().info(getApiInfo(appDescription,appVersion));
    }

    private Info getApiInfo(String appDesciption, String appVersion) {
        return new Info()
                .title("Gerenciador de Produtos")
                .description(appDesciption)
                .version(appVersion)
                .termsOfService("http://swagger.io/terms/")
                .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                .contact(contato());
    }

    private Contact contato() {
        Contact contact = new Contact();
        contact.setName("Anderson Antoniny - AWM");
        contact.email("anderson.an@gmail.com");
        contact.setUrl("https://www.linkedin.com/antoniny");

        return contact;
    }

    // It's important to set it in order to handle Pageable
    static {
        SpringDocUtils.getConfig()
                .replaceWithClass(org.springframework.data.domain.Pageable.class, org.springdoc.core.converters.models.Pageable.class);
    }
}
