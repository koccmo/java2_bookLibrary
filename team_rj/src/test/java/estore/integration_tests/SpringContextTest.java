package estore.integration_tests;

import estore.config.ProductConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

//аннотация для запуска тестов под управлением
//специального Spring JUnit Runner класса, который обеспечивает поднятие Spring контекста
//при старте тестов
@RunWith(SpringJUnit4ClassRunner.class)
//аннотация
//позволяет указать главный класс с конфигурацией приложения
@ContextConfiguration(classes = {ProductConfiguration.class})
public class SpringContextTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void start() {
        assertNotNull(applicationContext);
    }
}
