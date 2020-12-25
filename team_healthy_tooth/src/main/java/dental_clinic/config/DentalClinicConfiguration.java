package dental_clinic.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "dental_clinic")
@PropertySource(value = "classpath:application.properties")
public class DentalClinicConfiguration {
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