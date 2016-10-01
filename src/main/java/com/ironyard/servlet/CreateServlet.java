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

    public static final String NAVIGATION_PARAM= "nav";
    public static final String LOCATION_BUDGET_LIST = "list";
    public static final String REQUEST_SCOPE_BUDGET = "budget";
    public static final String JSP_DEST_BUDGET_LIST = "/weeklyBudget.jsp";
    public static final String JSP_DEST_EXCEPTION = "/exception.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String nextPage = req.getParameter(NAVIGATION_PARAM);
            BudgetService budgetsrv = new BudgetService();

            // get list
            List<Budget> all = budgetsrv.getAllBudgets();

            if (nextPage == null || nextPage.equalsIgnoreCase(LOCATION_BUDGET_LIST)) {
                req.setAttribute(REQUEST_SCOPE_BUDGET, all);

                //calculate total budget amount and actual amount

                double x=0;
                double y =0;

                for (Budget tmp : all) {
                    x=x + tmp.getBudgetedAmount();
                    y=y + tmp.getActualAmount();
                }

                //forward to list page

                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(JSP_DEST_BUDGET_LIST);
                dispatcher.forward(req, resp);

            }

        } catch (Throwable t) {
            t.printStackTrace();
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(JSP_DEST_EXCEPTION);
            dispatcher.forward(req, resp);
        }
    }


}

