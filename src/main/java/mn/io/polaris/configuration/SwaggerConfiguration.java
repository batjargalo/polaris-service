package mn.io.polaris.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Value("${info.name}")
    private String name;

    @Value("${info.description}")
    private String description;

    @Value("${info.version}")
    private String version;

    @Value("${info.contact.owner}")
    private String owner;

    @Value("${info.contact.email}")
    private String email;

    @Bean
    GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder().group("public-apis").pathsToMatch("/**").build();
    }

    @Bean
    OpenAPI customOpenApi() {
        return new OpenAPI().info(new Info().title(name).version(version).contact(getContact()).description(description));
    }

    private Contact getContact() {
        return new Contact().name(owner).email(email);
    }

}