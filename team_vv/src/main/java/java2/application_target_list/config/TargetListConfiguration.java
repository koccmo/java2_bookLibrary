package java2.application_target_list.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "java2.application_target_list")
@PropertySource(value = "classpath:application.properties")

public class TargetListConfiguration {
}
