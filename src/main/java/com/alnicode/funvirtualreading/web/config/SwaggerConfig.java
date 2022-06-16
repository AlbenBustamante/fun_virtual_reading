package com.alnicode.funvirtualreading.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import static com.alnicode.funvirtualreading.constants.DateFormatConstants.CONTROLLERS_PACKAGE;

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
                .select()
                .apis(RequestHandlerSelectors.basePackage(CONTROLLERS_PACKAGE))
                .build()
                .apiInfo(this.info());
    }

    /**
     * Build the api info.
     *
     * @return a {@link ApiInfo}
     */
    private ApiInfo info() {
        return new ApiInfoBuilder()
                .title("Fun Virtual Reading")
                .description("You do like to read virtual books? This is your site!\n" +
                        "Here you can register and post your own books.\n" +
                        "You can comment anothers books.\n" +
                        "Did you like some book? You can add to your \"likes\" collection or create your collections.\n" +
                        "\nHave fun!")
                .contact(this.contact())
                .version("1.0")
                .license("Apache License, Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }

    /**
     * Set the contact info.
     *
     * @return the {@link Contact}
     */
    private Contact contact() {
        return new Contact(
                "alnicode",
                "https://www.linkedin.com/in/alben-bustamante/",
                "albenbustamante@gmail.com"
        );
    }
}
