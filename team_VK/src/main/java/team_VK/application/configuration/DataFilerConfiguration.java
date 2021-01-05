package team_VK.application.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import team_VK.application.database.DataBaseFiller;

@Configuration
public class DataFilerConfiguration {

    @Value("${dataFiler.run}")
    private boolean toRun;

    @Bean
    public DataBaseFiller createDataBaseFiler() {

        if (toRun) {
            return new DataBaseFiller();
        } else return null;
    }
}
