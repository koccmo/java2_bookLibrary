package adventure_time.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

// @EnableTransactionManagement //  включает управление транзакциями
@Configuration
@ComponentScan(basePackages = "adventure_time")
@PropertySource(value = "classpath:application.properties")

public class AdminConfiguration {
    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${driverClass}")
    private String driverClass;

    @Value("${database.user.name}")
    private String userName;

    @Value("${database.user.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(jdbcUrl);
        dataSource.setDriverClassName(driverClass);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /*

    @Bean
	public Properties hibernateProperties(
			@Value("${hibernate.show_sql}") Boolean showSql,
			@Value("${hibernate.hbm2ddl.auto}") String hbm2ddl,
			@Value("${hibernate.dialect}") String dialect) {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", showSql);
		properties.put("hibernate.hbm2ddl.auto", hbm2ddl);
		properties.put("hibernate.dialect", dialect);
		return properties;
	}

	@Bean
	public SessionFactory sessionFactory(DataSource dataSource,
										 @Value("${hibernate.packagesToScan}") String packagesToScan,
										 Properties hibernateProperties
	) throws IOException {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setPackagesToScan(packagesToScan);
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setHibernateProperties(hibernateProperties);
		sessionFactoryBean.afterPropertiesSet();
		return sessionFactoryBean.getObject();
	}

	@Bean // TransactionManager нужен для управления транзакциями к базе данных в Spring приложении.
	public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
	}



     */




}

/*

Аннотация @Configuration используется для указания класса конфигурации.

Аннотация @ComponentScan указывает на главный пакет аппликации,
    в котором при старте приложения будет происходить поиск классов,
    проаннотированных аннотацией @Component.

Все найденные классы с аннотацией @Component будут включены в контекст приложения,
    и для них будут настроены зависимости.

Spring Framework поддерживает несколько способов хранения конфигурации:
    1. properties файлы
    2. xml файлы
    3. конфигурация в Java коде

Здесь, в классе EventConfiguration, реализовано хранение конфигурации в коде.


value = "classpath:application.properties" - означает, что приложение будет искать файл application.properties в своём classpath.
В структуре проекта директория src/main/resources входит в classpath приложения при старте, поэтому файл application.properties будет
прочитан приложением в момент запуска.

 */