package ca.mcgill.ecse428.ESCAPE.features.config;

import ca.mcgill.ecse428.ESCAPE.EscapeApplication;
import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = EscapeApplication.class, loader = SpringBootContextLoader.class)
public class CucumberSpringConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(CucumberSpringConfiguration.class);

    /**
     * Need this method so the cucumber will recognize this class as glue and load spring context configuration
     */
    @Before
    public void setUp() {
        LOG.info("-------------- Spring Context Initialized For Executing Cucumber Tests --------------");
    }
}
