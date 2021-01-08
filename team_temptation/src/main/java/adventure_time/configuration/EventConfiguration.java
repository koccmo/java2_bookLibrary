package adventure_time.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "adventure_time")

public class EventConfiguration {


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