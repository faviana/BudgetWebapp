package com.ironyard.servlet;

import com.ironyard.Service.BudgetService;
import com.ironyard.data.Budget;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by favianalopez on 9/29/16.
 */
public class CreateServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            //call reference to Budget List
            BudgetService bsv = new BudgetService();

            //getting all objects from Budget List
            List<Budget> allbudgets = bsv.getAllBudgets();

            //sending Budget List into Session
            req.getSession().setAttribute("budget", allbudgets);


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

            //forward to summaryPage.jsp
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/summaryPage.jsp");
            dispatcher.forward(req, resp);

            //catch exception
        }catch (Throwable t) {
            t.printStackTrace();
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/exception.jsp");
            dispatcher.forward(req, resp);
        }
    }


}

