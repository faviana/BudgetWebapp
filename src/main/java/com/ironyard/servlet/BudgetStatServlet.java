package com.ironyard.servlet;

import com.ironyard.Service.BudgetService;
import com.ironyard.data.Budget;
import com.ironyard.data.BudgetStat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by favianalopez on 10/3/16.
 */
public class BudgetStatServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //
        String destination = "/summaryPage.jsp";
        try {
            //get stats by cat
            BudgetService ms = new BudgetService();
            req.setAttribute("budgetstats", ms.getBudgetStats() );

            // get stats for all items (totals)
            List<Budget> allbudgets = ms.getAllBudgets();

            //define variables
            double x = 0;
            double y = 0;

            //for loop
            for (Budget b : allbudgets) {

                //calculates total for budgetAmount and actualAmount
                x = x + b.getBudgetedAmount();
                y = y + b.getActualAmount();
            }

            //sending total of budgetAmount and actualAmount into Session
            req.getSession().setAttribute("totalbudgetedAmount", x);
            req.getSession().setAttribute("totalactualAmount", y);

            BudgetService bs = new BudgetService();

            req.setAttribute("budgetTotal", bs.getBudgetStats());


        } catch (SQLException e) {
            e.printStackTrace();
            destination = "/exception.jsp";
        }

        // forward to proper JSP
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destination);
        dispatcher.forward(req,resp);
    }


    private List<BudgetStat> calculateBudgetStats() throws SQLException{
        BudgetService ms = new BudgetService();
        // put stats into request scope
        List<Budget> all = ms.getAllBudgets();

        // store budget sum by cat
        HashMap<String, BudgetStat> budgetStats = new HashMap<>();

        for(Budget tmpB: all){
            BudgetStat bStat = budgetStats.get(tmpB.getCategory());
            if(bStat == null){
                bStat = new BudgetStat();
                bStat.setCategory(tmpB.getCategory());
                // set values
                bStat.setTotalBudgetedAmount(bStat.getTotalBudgetedAmount());
                // set actual
            }else{
                // add to existing values
                //totalNow = totalBudgetedSoFar + tmpB.getBudgetedAmount();
                bStat.setTotalBudgetedAmount(bStat.getTotalBudgetedAmount()+tmpB.getBudgetedAmount());
                //totalNow = totalBudgetedSoFar + tmpB.getBudgetedAmount();
                bStat.setTotalActualAmount(bStat.getTotalActualAmount()+tmpB.getActualAmount());

            }
            budgetStats.put(tmpB.getCategory(), bStat);
        }
        // hash map you want to get values as list

        List<BudgetStat> budget = new ArrayList<BudgetStat>();
        for (BudgetStat b : budgetStats.values()) {
            budget.add(b);
        }

        return budget;
    }
}
