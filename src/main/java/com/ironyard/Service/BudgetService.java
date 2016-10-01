package com.ironyard.Service;

import com.ironyard.DbService;
import com.ironyard.data.Budget;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by favianalopez on 9/29/16.
 */
public class BudgetService {

    public List<Budget> getAllBudgets() throws SQLException {

        Budget found = null;
        List<Budget> allOfThem = new ArrayList<Budget>();
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

}
