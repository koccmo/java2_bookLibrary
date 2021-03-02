package java2.application_target_list.core.validators.target;

import java2.application_target_list.core.database.target.TargetRepository;
import java2.application_target_list.core.database.target.InMemoryTargetRepositoryImpl;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.requests.target.ChangeTargetNameRequest;
import java2.application_target_list.core.responses.CoreError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class ChangeTargetNameValidatorTest {

    @Autowired
    private ChangeTargetNameValidator validator;

    @BeforeEach
    public void setup() { ;
        TargetRepository targetRepository = new InMemoryTargetRepositoryImpl();
        targetRepository.addTarget(new Target("name", "description", 1L));
    }
    @Test
    public void testValidate_validRequest() {
        ChangeTargetNameRequest request = new ChangeTargetNameRequest(1L, "new name");
        List<CoreError> actualErrors = validator.validate(request);
        Assertions.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_invalidRequestId() {
        ChangeTargetNameRequest request = new ChangeTargetNameRequest(-1L, "new name");
        List<CoreError> actualErrors = validator.validate(request);
        Assertions.assertEquals(actualErrors.size(), 1);
        Assertions.assertTrue(actualErrors.get(0).getField().contains("Target ID"));
        Assertions.assertTrue(actualErrors.get(0).getMessage().contains("must not be negative!"));
    }

    @Test
    public void testValidate_invalidRequestNewName_v1() {
        ChangeTargetNameRequest request = new ChangeTargetNameRequest(1L, "");
        List<CoreError> actualErrors = validator.validate(request);
        Assertions.assertEquals(actualErrors.size(), 1);
        Assertions.assertTrue(actualErrors.get(0).getField().contains("Target new name"));
        Assertions.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidRequestNewName_v2() {
        ChangeTargetNameRequest request = new ChangeTargetNameRequest(0L, null);
        List<CoreError> actualErrors = validator.validate(request);
        Assertions.assertEquals(actualErrors.size(), 1);
        Assertions.assertTrue(actualErrors.get(0).getField().contains("Target new name"));
        Assertions.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

}