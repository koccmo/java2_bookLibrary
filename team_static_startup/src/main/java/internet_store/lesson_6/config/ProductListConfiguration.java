package internet_store.lesson_6.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "internet_store.lesson_6")
@PropertySource(value = "classpath:application.properties")
public class ProductListConfiguration {
}
