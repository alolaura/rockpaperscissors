
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import shapes.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PaperTest.class,
        RockTest.class,
        ScissorsTest.class,
        JsonSchemaValidation.class
})
public class TestSuite {
}
