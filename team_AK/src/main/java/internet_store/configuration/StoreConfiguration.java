package internet_store.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "internet_store")
@PropertySource({"classpath:telegram.properties",
        "classpath:mail.properties"})
public class StoreConfiguration {
}