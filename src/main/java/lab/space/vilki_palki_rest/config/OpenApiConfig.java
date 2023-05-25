package lab.space.vilki_palki_rest.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Vilki-Palki Rest API")
                                .version("1")
                                .description("This is a vilki-palki-rest server.")
                                .termsOfService("https://swagger.io/terms/")
                )
                .addServersItem(new Server().url("http://localhost:8081"));
    }

    @Bean
    public GroupedOpenApi myApi() {
        return GroupedOpenApi.builder()
                .group("vilki-palki-rest")
                .packagesToScan("lab.space.vilki_palki_rest.controller")
                .build();
    }
}
