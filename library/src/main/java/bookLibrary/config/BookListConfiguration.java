package bookLibrary.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "bookLibrary")
@PropertySource(value = "classpath:application.properties")
public class BookListConfiguration {
}
