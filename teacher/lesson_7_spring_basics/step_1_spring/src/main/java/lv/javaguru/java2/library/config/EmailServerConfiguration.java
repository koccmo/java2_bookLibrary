package lv.javaguru.java2.library.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lv.javaguru.java2.library.core.services.EmailServer;

@Configuration
public class EmailServerConfiguration {

	@Value("${emailserver.enabled}")
	private boolean enabled;


	@Bean
	public EmailServer createEmailServer() {
		if (enabled) {
			return new EmailServer();
		} else {
			return null;
		}
	}


}
