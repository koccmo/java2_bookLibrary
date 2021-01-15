package lv.estore.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "lv.estore.app")
@PropertySource(value = "classpath:application.properties")
public class EStoreConfiguration {

}
