package estore.acceptance_tests;

import estore.core.validation.AddProductValidator;
import estore.core.validation.CoreError;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.mockito.Mockito;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//@Configuration
public class TestConfig {

    private static ApplicationContext applicationContext;

    @Bean
    @Primary
    public AddProductValidator mockNewProductCategoryValidator() {
        AddProductValidator validator = Mockito.mock(AddProductValidator.class);
        when(validator.validate(any())).thenReturn(new ArrayList<>());
        return validator;
    }
}
