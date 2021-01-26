package estore.acceptance_tests;

import estore.core.validation.AddNewProductValidator;
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
    public AddNewProductValidator mockNewProductCategoryValidator() {
        AddNewProductValidator validator = Mockito.mock(AddNewProductValidator.class);
        when(validator.validate(any())).thenReturn(new ArrayList<CoreError>());
        return validator;
    }
}
