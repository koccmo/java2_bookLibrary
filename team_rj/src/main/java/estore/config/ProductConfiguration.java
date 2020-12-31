package estore.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "estore")
//value = "classpath:application.properties" - означает, что приложение будет искать файл application.properties
//в своём classpath. В структуре проекта директория src/main/resources входит в classpath приложения при
// тарте, поэтому файл application.properties будет прочитан приложением в момент запуска.
@PropertySource(value = "classpath:application.properties")
public class ProductConfiguration {
}

//Для того, чтобы получить доступ к прочитанным Spring свойствам конфирации нужно воспользоваться аннотацией @Value
//      @Value("${search.ordering.enabled}")
//      private boolean orderingEnabled;

//В любом классе, помеченном аннотацией @Component или @Configuration, вы можете объявить свойство на уровне класса
// и установить аннотацию @Value на нём. Как параметр при объявлении аннотации указывается название свойства из
// файла конфигурации application.properties. Spring прочитает и присвоит в указанное свойство значение взятое из
// конфигурационного файла в момент старта приложения.