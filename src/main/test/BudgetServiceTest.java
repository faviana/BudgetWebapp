import com.ironyard.Service.BudgetService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by favianalopez on 9/30/16.
 */
public class BudgetServiceTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getAllBudgets() throws Exception {
        BudgetService bs = new BudgetService();
        Assert.assertNotNull(bs.getAllBudgets());
    }

}