package octoping.ticketing.api.config.swagger

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.servers.Server
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@OpenAPIDefinition(
    info =
        Info(
            title = "scenario-study",
            description = "scenario-study API",
            version = "v1",
        ),
    servers = [
//        Server(url = "https://api.until.blog", description = "Production server"),
        Server(url = "http://localhost:8080", description = "Local server"),
    ],
)
@Configuration
class SwaggerConfig {
    @Bean
    fun chatOpenApi(): GroupedOpenApi =
        GroupedOpenApi
            .builder()
            .group("scenario-study")
            .pathsToMatch(*arrayOf("/**"))
            .build()
}
