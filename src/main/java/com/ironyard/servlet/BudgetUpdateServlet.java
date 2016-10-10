package com.ironyard.servlet;

import com.ironyard.Service.BudgetService;
import com.ironyard.data.Budget;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by favianalopez on 10/5/16.
 */
public class BudgetUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String destination = "/detail";


        // get the request parameters
        String description = req.getParameter("bDescription");
        String category = req.getParameter("bCategory");
        String budgetedAmount = req.getParameter("bB_Amount");
        String actualAmount = req.getParameter("bA_Amount");
        String id = req.getParameter("bId");

        double baConv = Double.parseDouble(budgetedAmount);
        double aaConv = Double.parseDouble(actualAmount);
        long idConv = Long.parseLong(id);

        // create a budget object (with ID!)
        Budget updateMe = new Budget("Snack", "Chips", 5, 2.50);
        updateMe.setId(idConv);
        updateMe.setDescription(description);
        updateMe.setCategory(category);
        updateMe.setBudgetedAmount(baConv);
        updateMe.setActualAmount(aaConv);


        // call update on budget service
        BudgetService bServ = new BudgetService();
        try {
            bServ.update(updateMe);
        }catch (SQLException e){
            e.printStackTrace();
            destination = "/exception.jsp";
        }
        // forward list
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destination);
        dispatcher.forward(req,resp);

    }
}
