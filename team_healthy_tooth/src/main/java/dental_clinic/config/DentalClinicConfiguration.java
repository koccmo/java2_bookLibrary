package dental_clinic.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "dental_clinic")
@PropertySource(value = "classpath:application.properties")
@EnableTransactionManagement
public class DentalClinicConfiguration {

    //JDBC
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


	//ORM
    @Bean
    public Properties hibernateProperties(
            @Value("${hibernate.show_sql}") Boolean showSql,
            @Value("${hibernate.hbm2ddl.auto") String hbm2ddl,
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

    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}

//Spring Framework поддерживает несколько способов хранения конфигурации:
//1. properties файлы
//2. xml файлы
//3. конфигурация в Java коде

//Spring аннотация @ComponentScan используется для указания главного пакета
//аппликации в котором при старте приложения будет происходить поиск классов
//проаннатированных аннотацией @Component.

//Для того, чтобы Spring на старте читал
//файл конфигурации application.properties нужно
//добавить аннотацию @PropertySource к классу
//с конфигурацией Spring Framework:

//value = "classpath:application.properties" - означает,
//что приложение будет искать файл application.properties
//в своём classpath. В структуре проекта директория
//src/main/resources входит в classpath приложения при
//старте, поэтому файл application.properties будет
//прочитан приложением в момент запуска.