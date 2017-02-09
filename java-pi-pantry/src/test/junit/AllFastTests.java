import com.googlecode.junittoolbox.*;
import org.junit.experimental.categories.Category;

@org.junit.runner.RunWith(WildcardPatternSuite.class)
@SuiteClasses({"**/*.class"})
@ExcludeCategories(com.langrsoft.testutil.Slow.class)

@Category(com.langrsoft.testutil.Slow.class)
public class AllFastTests {
}
