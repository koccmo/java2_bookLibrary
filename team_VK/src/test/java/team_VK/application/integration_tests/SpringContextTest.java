package team_VK.application.integration_tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import team_VK.application.ApplicationContext;
import team_VK.application.configuration.SpringCoreConfiguration;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringCoreConfiguration.class})

public class SpringContextTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void applicationContextTest() {

        assertNotNull(context);
    }
}
