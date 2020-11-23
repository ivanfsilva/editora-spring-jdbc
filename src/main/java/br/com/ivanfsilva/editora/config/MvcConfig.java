package br.com.ivanfsilva.editora.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan("br.com.ivanfsilva.editora")
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Bean(name = "jspViewResolver")
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();

        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);

        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/static/css/");

        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/static/js/");

        registry.addResourceHandler("/files/**", "/img/**", "/images/**")
                .addResourceLocations("/WEB-INF/static/images/");

    }
}
