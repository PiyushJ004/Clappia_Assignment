//
//package com.example.demo.config;
//
//import static springfox.documentation.builders.PathSelectors.regex;
//import java.util.List;
//import javax.servlet.ServletContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
//import org.springframework.http.HttpMethod;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.paths.RelativePathProvider;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig extends WebMvcConfigurationSupport {
//
//	@Override
//	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//		argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
//	}
//
//	@Bean
//	public Docket productApi(ServletContext servletContext) {
//		return new Docket(DocumentationType.SWAGGER_2).select()
//				.apis(RequestHandlerSelectors.basePackage("com.gaian.template")).paths(regex("/.*")).build()
//				.apiInfo(metaData()).pathProvider(new RelativePathProvider(servletContext) {
//					@Override
//					public String getApplicationBasePath() {
//						return "/template-service";
//					}
//				});
//	}
//
//	@SuppressWarnings("deprecation")
//	private ApiInfo metaData() {
//		ApiInfo apiInfo = new ApiInfo("TemplateService RestCall Docs", "Template-Service", "1.0", "Terms of service",
//				"Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0", "Gaian Solution");
//		return apiInfo;
//	}
//
//	@Override
//	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//	}
//
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**").allowCredentials(true).allowedOrigins("*")
//				.allowedMethods(HttpMethod.PUT.name(), HttpMethod.POST.name(), HttpMethod.GET.name(),
//						HttpMethod.DELETE.name(), HttpMethod.OPTIONS.name(), HttpMethod.HEAD.name(),
//						HttpMethod.PATCH.name(), HttpMethod.TRACE.name())
//				.allowedHeaders("Content-Type", "token", "Access-Control-Request-Method", "Access-Control-Allow-Origin",
//						"Access-Control-Request-Headers", "Access-Control-Allow-Headers",
//						"Access-Control-Allow-Methods")
//				.exposedHeaders("Content-Type", "Accept", "token", "Access-Control-Allow-Origin");
//	}
//
//}
