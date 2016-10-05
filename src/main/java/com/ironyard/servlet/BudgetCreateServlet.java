package com.ironyard.servlet;

import com.ironyard.Service.BudgetService;
import com.ironyard.data.Budget;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by favianalopez on 10/4/16.
 */
public class BudgetCreateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Set destination to createBudget.jsp
        String destination = "/createBudget.jsp";

        // get the data from request
        String description = req.getParameter("bDescription");
        String category = req.getParameter("bCategory");
        String budgetedAmount = req.getParameter("bB_Amount");
        String actualAmount = req.getParameter("bA_Amount");

        // create a new budget object
        Budget myBudget = new Budget();
        myBudget.setDescription(description);
        myBudget.setCategory(category);
        myBudget.setBudgetedAmount(Double.parseDouble(budgetedAmount));
        myBudget.setActualAmount(Double.parseDouble(actualAmount));

        // call save on budget service with our new object
        try{
            BudgetService bServ = new BudgetService();
            bServ.createBudget(myBudget);
            }catch (Exception x){
            x.printStackTrace();
            destination = "/exception.jsp";
            }

        // forward to list page
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destination);
        dispatcher.forward(req,resp);

    }
}
