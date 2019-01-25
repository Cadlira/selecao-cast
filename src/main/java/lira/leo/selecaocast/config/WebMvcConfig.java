package lira.leo.selecaocast.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class WebMvcConfig {

	
    @Bean
    public WebMvcConfigurer forwardToIndex() {
        return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
            	registry.addViewController("/").setViewName("forward:/index.html");
            }
            
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry){

                registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
            }
        };
    }
    
    @Bean
    public InternalResourceViewResolver htmlViewResolver() {
        InternalResourceViewResolver resolver= new InternalResourceViewResolver();
        resolver.setPrefix("/static/");
        resolver.setSuffix(".html");
        return resolver;
    }  
	
}
