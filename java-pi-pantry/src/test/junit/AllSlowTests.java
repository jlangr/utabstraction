import com.googlecode.junittoolbox.*;

@org.junit.runner.RunWith(WildcardPatternSuite.class)
@SuiteClasses({"**/*.class"})
@IncludeCategories(com.langrsoft.testutil.Slow.class)
public class AllSlowTests {
}
