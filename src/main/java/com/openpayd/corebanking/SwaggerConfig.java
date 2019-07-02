package com.openpayd.corebanking;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


    @Configuration
    @EnableSwagger2
    public class SwaggerConfig extends WebMvcConfigurerAdapter {
        @Bean
        public Docket customImplementation(){
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(getApiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.openpayd.corebanking"))
                    .paths(PathSelectors.any())
                    .build();
        }
        private ApiInfo getApiInfo() {
            return new ApiInfoBuilder()
                    .title("corebanking")
                    .description("DESCRIPTION")
                    .version("VERSION")
                    .termsOfServiceUrl("http://terms-of-services.url")
                    .license("LICENSE")
                    .licenseUrl("http://url-to-license.com")
                    .build();
        }

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry)
        {
            //enabling swagger-ui part for visual documentation
            registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
}