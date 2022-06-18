package com.alnicode.funvirtualreading.web.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import static com.alnicode.funvirtualreading.constants.JWTConstants.HEADER;
import static com.alnicode.funvirtualreading.constants.SwaggerConstants.API_KEY_NAME;
import static com.alnicode.funvirtualreading.constants.SwaggerConstants.API_KEY_PASS_ASS;
import static com.alnicode.funvirtualreading.constants.SwaggerConstants.CONTACT_EMAIL;
import static com.alnicode.funvirtualreading.constants.SwaggerConstants.CONTACT_NAME;
import static com.alnicode.funvirtualreading.constants.SwaggerConstants.CONTACT_URL;
import static com.alnicode.funvirtualreading.constants.SwaggerConstants.CONTROLLERS_PACKAGE;
import static com.alnicode.funvirtualreading.constants.SwaggerConstants.INFO_DESCRIPTION;
import static com.alnicode.funvirtualreading.constants.SwaggerConstants.INFO_LICENSE;
import static com.alnicode.funvirtualreading.constants.SwaggerConstants.INFO_LICENSE_URL;
import static com.alnicode.funvirtualreading.constants.SwaggerConstants.INFO_TITLE;
import static com.alnicode.funvirtualreading.constants.SwaggerConstants.INFO_VERSION;

/**
 * Enable and configure Swagger2.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Define a bean with the api configuration.
     *
     * @return the api docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securitySchemes(List.of(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage(CONTROLLERS_PACKAGE))
                .build()
                .apiInfo(info());
    }

    /**
     * Enable the APi Key and 'Authorize' button.
     *
     * @return the api key
     */
    private ApiKey apiKey() {
        return new ApiKey(API_KEY_NAME, HEADER, API_KEY_PASS_ASS);
    }

    /**
     * Build the api info.
     *
     * @return a {@link ApiInfo}
     */
    private ApiInfo info() {
        return new ApiInfoBuilder()
                .title(INFO_TITLE)
                .description(INFO_DESCRIPTION)
                .contact(contact())
                .version(INFO_VERSION)
                .license(INFO_LICENSE)
                .licenseUrl(INFO_LICENSE_URL)
                .build();
    }

    /**
     * Set the contact info.
     *
     * @return the {@link Contact}
     */
    private Contact contact() {
        return new Contact(CONTACT_NAME, CONTACT_URL, CONTACT_EMAIL);
    }
}
