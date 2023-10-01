package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/Cart.feature","src/test/resources/features/UserLogIn.feature"},
        tags = "@Regression",
        plugin = {"pretty","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        glue = {"test.steps","logic.hooks"}
)
public class RunCucumberTests {
}
