import com.ironyard.DbService;
import com.ironyard.Service.BudgetService;
import com.ironyard.data.Budget;
import com.ironyard.data.BudgetStat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by favianalopez on 9/29/16.
 */
public class DbServiceTest {

    @Before
    public void setUp() throws Exception {
        DbService dbService = new DbService();
        Connection con = dbService.getConnection();
        Statement stm = con.createStatement();
        stm.executeUpdate("TRUNCATE budget");
        con.close();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllBudgets() throws Exception {
        BudgetService bserv = new BudgetService();
        List<Budget> allOfThem = bserv.getAllBudgets();
        assertEquals(0, allOfThem.size());
        // create 2 budgets

        // get all

        // assert equal 2
    }

    @Test
    public void search() throws Exception {
        BudgetService bserv = new BudgetService();
        List<Budget> found = bserv.search("%");
        assertTrue(found.size() > 0);
    }


    @Test
    public void getBudgetStats() throws Exception {
        BudgetService bserv = new BudgetService();
        List<BudgetStat> found = bserv.getBudgetStats();
        assertTrue(found.size() > 0);
    }


    @Test
    public void createBudget() throws Exception {
        BudgetService bserv = new BudgetService();
        Budget b = new Budget("Snack", "Chips", 5, 2.50);
        b.setDescription("Chips");
        b.setCategory("Snack");
        b.setBudgetedAmount(5);
        b.setActualAmount(1.50);

        bserv.createBudget(b);


        List<Budget> found = bserv.search("Chips");
        assertNotNull(found);
        assertTrue(found.size() == 1);
        Budget foundBudget = found.get(0);
        assertEquals(foundBudget.getDescription(), "Chips");
        assertEquals(foundBudget.getCategory(), "Snack");
        assertEquals(foundBudget.getBudgetedAmount(), 5);
        assertEquals(foundBudget.getActualAmount(),1.50);
        assertTrue(foundBudget.getId()>0);

    }

    @Test
    public void Update() throws Exception {
        BudgetService bserv = new BudgetService();
        bserv.createBudget(new Budget("Snack", "Chips",5,2.50));

        List<Budget> found = bserv.search("category") ;

        Budget bfound = null;
        assertEquals(bfound.getDescription(), "Chips");
        assertEquals(bfound.getCategory(), "Snack");
        assertEquals(bfound.getBudgetedAmount(), 5);
        assertEquals(bfound.getActualAmount(),1.50);


        bserv.update(bfound);
        found = bserv.search("hello");
        
    }

    @Test
    public void Delete() throws Exception {

        BudgetService bserv = new BudgetService();
        bserv.createBudget(new Budget("Chips", "Snack", 5, 1.5));
        List<Budget> found = bserv.search("category");
        // get ref single budge object

        // call delete on service

        // search again and verify nothing found

    }
}