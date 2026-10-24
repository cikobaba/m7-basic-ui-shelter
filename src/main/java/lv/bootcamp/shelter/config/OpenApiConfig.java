
package lv.bootcamp.shelter.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Defines the public API metadata shown in Swagger UI.
 * Do not include secrets, internal hostnames,
 * or sensitive data in descriptions.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI basicUiOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Shelter Animal Management API")
                        .version("v1")
                        .description("REST API for managing shelter animals, including registration and adoption."));
    }
}
