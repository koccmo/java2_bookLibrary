package lesson_8.mysql_spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

@EnableWebMvc
@Configuration
public class MvcConfiguration {
    @Bean
    public SpringResourceTemplateResolver mvcConfigurator() {
        SpringResourceTemplateResolver resourceViewResolver = new SpringResourceTemplateResolver();
        resourceViewResolver.setPrefix("classpath:/templates/lesson_8/");
        resourceViewResolver.setSuffix(".html");
        resourceViewResolver.setTemplateMode(TemplateMode.HTML);
        resourceViewResolver.setCharacterEncoding("UTF-8");
        resourceViewResolver.setCheckExistence(true);
        return resourceViewResolver;
    }
}