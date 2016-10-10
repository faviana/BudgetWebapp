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
public class BudgetSelectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String destination = "/updateBudget.jsp";

        // get id from the request
        String id = req.getParameter("id");
        long idConv = Long.parseLong(id);

        // fetch budget by id
        BudgetService bServ = new BudgetService();
        Budget found = null;
        try {
            found = bServ.getBudgetById(idConv);
        }catch(SQLException e){
            e.printStackTrace();
            destination = "/exception.jsp";
        }

        // put budget into request
        req.setAttribute("myUpdate", found);

        // forward
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destination);
        dispatcher.forward(req,resp);
    }
}