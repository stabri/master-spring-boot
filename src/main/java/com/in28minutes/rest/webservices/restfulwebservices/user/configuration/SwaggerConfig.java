package com.in28minutes.rest.webservices.restfulwebservices.user.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final Contact DEFAULT_CONTACT = new Contact("Pawel", "www.pawel.com", "pawel@pawel.com");
    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Social App",
            "Social App - API Documentation", "1.0", "urn:tos",
            DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUM =
            new HashSet<>(Arrays.asList("application/json", "application/xml"));


    // Bean Docket - add swagger to all paths, all the APIs
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUM)
                .consumes(DEFAULT_PRODUCES_AND_CONSUM);
    }


}
