package lesson_8.mysql_spring.configuration;

import lesson_8.mysql_spring.ClientDb;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("lesson_8.mysql_spring.persistence")
@ComponentScan("lesson_8.mysql_spring")
public class SqlConfiguration {
    @Bean
    public ClientDb main() {
        return new ClientDb();
    }
}