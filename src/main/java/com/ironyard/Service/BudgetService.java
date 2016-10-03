package com.ironyard.Service;

import com.ironyard.DbService;
import com.ironyard.data.Budget;
import com.ironyard.data.BudgetStat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by favianalopez on 9/29/16.
 */
public class BudgetService {

    public List<Budget> getAllBudgets() throws SQLException {

        Budget found = null;
        List<Budget> allOfThem = new ArrayList<>();
        DbService myDba = new DbService();
        Connection conn = myDba.getConnection();
        PreparedStatement stmt = conn.prepareCall("SELECT * FROM budget");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            found = new Budget();
            found.setDescription(rs.getString("bud_description"));
            found.setCategory(rs.getString("bud_category"));
            found.setBudgetedAmount(rs.getDouble("bud_budgeted_amount"));
            found.setActualAmount(rs.getDouble("bud_actual_amount"));
            found.setId(rs.getLong("bud_id"));
            allOfThem.add(found);
        }
        return allOfThem;

    }

    public List<Budget> search(String search) throws SQLException {
        List<Budget> found;
        DbService Dbs = new DbService();
        Connection c = Dbs.getConnection();
        // do a starts with search
        search = search + "%";
        PreparedStatement ps = c.prepareStatement("SELECT * FROM budget WHERE (bud_category ILIKE ?);");
        ps.setString(1, search);
        ResultSet rs = ps.executeQuery();
        found = convertResultsToList(rs);
        return found;
    }

    public List<BudgetStat> getBudgetStats() throws SQLException {
        List<BudgetStat> found = new ArrayList<>();
        DbService Dbs = new DbService();
        Connection c = Dbs.getConnection();
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery("SELECT bud_category, SUM (bud_budgeted_amount) AS bud_total FROM budget GROUP BY bud_category;");
        while(rs.next()){
            BudgetStat tmp = new BudgetStat();
            tmp.setCategory(rs.getString("bud_category"));
            tmp.setTotalBudgetedAmount(rs.getInt("bud_budgeted_amount"));
            tmp.setTotalActualAmount(rs.getInt("bud_actual_amount"));
            found.add(tmp);
        }

        return found;
    }

    private List<Budget> convertResultsToList(ResultSet rs) throws SQLException {
        List<Budget> found = new ArrayList<>();
        while(rs.next()){
            Budget tmp = new Budget();
            tmp.setId(rs.getInt("bud_id"));
            tmp.setDescription(rs.getString("bud_description"));
            tmp.setCategory(rs.getString("bud_category"));
            tmp.setBudgetedAmount(rs.getDouble("bud_budgeted_amount"));
            tmp.setActualAmount(rs.getDouble("bud_actual_amount"));
            found.add(tmp);
        }
        return found;
    }
}

