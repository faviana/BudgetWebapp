package com.ironyard.servlet;

import com.ironyard.Service.BudgetService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by favianalopez on 10/3/16.
 */

public class BudgetSearchServlet extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            // get reference to budget list
            BudgetService bs = new BudgetService();

            // set destination to weeklyPage.jsp
            String destination = "/weeklyBudget.jsp";
            try {
                // get the search text
                String searchByText = req.getParameter("searchtext");

                //set budget data
                req.setAttribute("budget", bs.search(searchByText));

                //catch exception
            } catch (SQLException e) {
                e.printStackTrace();
                destination = "/exception.jsp";
            }

            // forward to proper JSP
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destination);
            dispatcher.forward(req,resp);
        }
    }
