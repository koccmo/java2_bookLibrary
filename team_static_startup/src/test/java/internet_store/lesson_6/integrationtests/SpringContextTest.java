package internet_store.lesson_6.integrationtests;

import internet_store.lesson_6.config.ProductListConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ProductListConfiguration.class})
public class SpringContextTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void start() {
        assertNotNull(applicationContext);
    }

}


