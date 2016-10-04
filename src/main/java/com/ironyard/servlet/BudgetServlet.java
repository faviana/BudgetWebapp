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
public class BudgetServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            //get reference to Budget List
            BudgetService bsv = new BudgetService();

            //getting all objects from Budget List
            List<Budget> allbudgets = bsv.getAllBudgets();

            //request all objects
            req.setAttribute("budget", allbudgets);

            //forward to summaryPage.jsp
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/weeklyBudget.jsp");
            dispatcher.forward(req, resp);

            //catch exception
        }catch (Throwable t) {
            t.printStackTrace();
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/exception.jsp");
            dispatcher.forward(req, resp);
        }
    }


}

