import com.ironyard.DbService;
import com.ironyard.Service.BudgetService;
import com.ironyard.data.Budget;
import com.ironyard.data.BudgetStat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


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
        assertEquals(allOfThem.size(), 1);
    }

    @Test
    public void search() throws Exception {
        BudgetService bserv = new BudgetService();
        List<Budget> allOfThem = bserv.search("fo");
        assertTrue(allOfThem.size() > 0);
    }



    @Test
    public void getBudgetStats() throws Exception {
        BudgetService bserv = new BudgetService();
        List<BudgetStat> stats = bserv.getBudgetStats();
        assertTrue(stats.size() > 0);
    }

    @Test
        public void getConnection() throws Exception {
            DbService dbService = new DbService();
            Connection con = dbService.getConnection();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM budget");

            boolean foundSomething = false;
            while(rs.next()){
                foundSomething = true;
                System.out.println(rs.getString("bud_description"));
            }
            assertTrue(foundSomething);
        }

    @Test
    public void testSaveNewMovie() throws Exception {
        BudgetService bserv = new BudgetService();
        Budget b = new Budget();
        b.setDescription("Chips");
        b.setCategory("Snack");
        b.setBudgetedAmount(5);
        b.setActualAmount(1.50);

        try {
            bserv.save(b);
        } catch (SQLException e) {
            e.printStackTrace();
        }

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

    }