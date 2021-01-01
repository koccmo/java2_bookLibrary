package team_VK.application.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "team_VK.application")
@PropertySource(value = "classpath:application.properties")

public class LibraryConfig {

}
