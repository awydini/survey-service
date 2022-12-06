package net.aydini.ml.surveyservice.controller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

/**
 * 
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 *
 */

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI publicApi() {

		return new OpenAPI()
				.info(new Info().title("Survey App API")
						.description("Dibs App Core Service API Documentation").version("v0.1.0-SNAPSHOT"))
				.addServersItem(new Server().url(""))
				.externalDocs(new ExternalDocumentation().description("Survey App API Documentation"))
				.components(new Components().addSecuritySchemes("bearer-key",
						new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));

	}

}
