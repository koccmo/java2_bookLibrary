package internet_store.core.integretion_test;

import internet_store.configuration.StoreConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StoreConfiguration.class)
public class SpringContextTest {
    @Autowired
    private ApplicationContext context;

    @Test
    public void contextStarUp() {
        assertNotNull(context);
    }
}