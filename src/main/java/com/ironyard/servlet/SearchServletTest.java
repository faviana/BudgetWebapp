package com.ironyard.servlet;

import com.ironyard.Service.BudgetService;
import com.ironyard.data.Budget;
import com.ironyard.data.BudgetStat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by favianalopez on 10/3/16.
 */
public class SearchServletTest {


        @Before
        public void setUp() throws Exception {

        }

        @After
        public void tearDown() throws Exception {

        }

        @Test
        public void getAllBudgets() throws Exception {
            BudgetService bs = new BudgetService();
            List<Budget> allBudgets = bs.getAllBudgets();
            assertEquals(allBudgets.size(), 1);
        }

        @Test
        public void getAllBudgetStats() throws Exception {
            BudgetService bs = new BudgetService();
            List<BudgetStat> stats = bs.getBudgetStats();
            assertTrue(stats.size() > 0);
        }

        @Test
        public void searchByCategory() throws Exception {
            BudgetService bs = new BudgetService();
            List<Budget> allBudgets = bs.search("");
            assertTrue(allBudgets.size() > 0);
        }


    }
