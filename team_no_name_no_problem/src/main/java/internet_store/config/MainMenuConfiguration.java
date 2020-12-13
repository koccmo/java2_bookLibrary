package internet_store.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "internet_store")
@PropertySource(value = "classpath:application.properties")
public class MainMenuConfiguration {

}
