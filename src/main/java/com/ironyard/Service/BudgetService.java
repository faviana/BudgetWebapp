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

    /**
     * Get all budget in the database
     * @return
     * @throws SQLException
     */

    public List<Budget> getAllBudgets() throws SQLException {

        Budget found = null;
        List<Budget> allOfThem = new ArrayList<>();
        DbService myDba = new DbService();
        Connection conn = myDba.getConnection();
        PreparedStatement stmt = conn.prepareCall("SELECT * FROM budget");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            found = new Budget("Snack", "Chips", 5, 2.50);
            found.setDescription(rs.getString("bud_description"));
            found.setCategory(rs.getString("bud_category"));
            found.setBudgetedAmount(rs.getDouble("bud_budgeted_amount"));
            found.setActualAmount(rs.getDouble("bud_actual_amount"));
            found.setId(rs.getLong("bud_id"));
            allOfThem.add(found);
        }
        return allOfThem;

    }

    /**
     * Retrieves data that with starts with "%" from category
     * @param search String to search by %
     * @return returns a list<Budget>
     * @throws SQLException
     */

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

    /**
     * Generates budget statistics on all budgets
     * @return returns list of budget statistics
     * @throws SQLException
     */

    public List<BudgetStat> getBudgetStats() throws SQLException {
        List<BudgetStat> found = new ArrayList<>();
        DbService Dbs = new DbService();
        Connection c = Dbs.getConnection();
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery("SELECT bud_category, SUM (bud_budgeted_amount) AS bud_total, sum(bud_actual_amount) AS bud_total_a FROM budget GROUP BY bud_category;");
        while(rs.next()){
            BudgetStat tmp = new BudgetStat();
            tmp.setCategory(rs.getString("bud_category"));
            tmp.setTotalBudgetedAmount(rs.getDouble("bud_total"));
            tmp.setTotalActualAmount(rs.getInt("bud_total_a"));

            found.add(tmp);
        }

        return found;
    }

    /**
     * Retrieves budget objects as a List<Budget>
     * @param rs convertResultsToList
     * @return returns the List<Budget>
     * @throws SQLException
     */

    private List<Budget> convertResultsToList(ResultSet rs) throws SQLException {
        List<Budget> found = new ArrayList<>();
        while (rs.next()) {
            Budget tmp = new Budget("Snack", "Chips", 5, 2.50);
            tmp.setId(rs.getInt("bud_id"));
            tmp.setDescription(rs.getString("bud_description"));
            tmp.setCategory(rs.getString("bud_category"));
            tmp.setBudgetedAmount(rs.getDouble("bud_budgeted_amount"));
            tmp.setActualAmount(rs.getDouble("bud_actual_amount"));
            found.add(tmp);
        }
        return found;
    }

    /**
     * Generates a new budget item list<Budget> with a unique ID
     * saves new list into database
     * @param myBudget
     * @throws SQLException
     */


    public void createBudget (Budget myBudget) throws SQLException {
        DbService myDbs = new DbService();
        Connection con = null;
        try{
            con = myDbs.getConnection();
            PreparedStatement ps = con.prepareCall("INSERT INTO budget  (bud_id, bud_description, bud_category, bud_budgeted_amount, bud_actual_amount) VALUES (  nextval('BUDGET_SEQ'),?,?,?,?)");
            ps.setString(1, myBudget.getDescription());
            ps.setString(2, myBudget.getCategory());
            ps.setDouble(3, myBudget.getBudgetedAmount());
            ps.setDouble(4, myBudget.getBudgetedAmount());
            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
            con.rollback();
            throw e;

        }finally {
            con.close();
        }
    }

    /**
     * retrieves budget objects by ID from database
     * @param idConv
     * @return returns budget by id
     * @throws SQLException
     */

    public Budget getBudgetById (long idConv) throws SQLException {
        DbService dbServ = new DbService();
        Connection con = null;
        Budget foundById = null;

        try {
            con = dbServ.getConnection();

            // do a starts with search
            PreparedStatement pstm = con.prepareStatement("SELECT  * FROM budget WHERE bud_id = ?;");
            pstm.setLong(1, idConv);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()) {
                foundById = new Budget("Snack", "Chips", 5, 2.50);
                foundById.setDescription(rs.getString("bud_description"));
                foundById.setCategory(rs.getString("bud_category"));
                foundById.setBudgetedAmount(rs.getDouble("bud_budgeted_amount"));
                foundById.setActualAmount(rs.getDouble("bud_actual_amount"));
                foundById.setId(rs.getLong("bud_id"));
            }
        }catch(SQLException t){
            t.printStackTrace();
            con.rollback();
            throw t;
        }finally {
            con.close();

        }
        return foundById;
    }

    /**
     * Retrieves data by id from category
     * executes update
     * @param aBudget
     * @throws SQLException
     */

    public void update(Budget aBudget) throws SQLException{
        DbService dbServ = new DbService();
        Connection con = null;
        try {
            con = dbServ.getConnection();

            // do a starts with search
            PreparedStatement ps = con.prepareStatement("UPDATE budget SET bud_description=?, bud_category=?, bud_budgeted_amount=?, bud_actual_amount=? WHERE bud_id= ?;");
            ps.setString(1, aBudget.getDescription());
            ps.setString(2, aBudget.getCategory());
            ps.setDouble(3, aBudget.getBudgetedAmount());
            ps.setDouble(4, aBudget.getActualAmount());
            ps.setLong(5, aBudget.getId());
            ps.executeUpdate();

        }catch(SQLException t){
            t.printStackTrace();
            con.rollback();
            throw t;
        }finally {
            con.close();

        }
    }

    /**
     * Retrieves id object from database
     * nulls objet id and updates id object.
     * @param id
     * @throws SQLException
     */


    public void delete(long id) throws SQLException{
        DbService dbServ = new DbService();
        Connection c = null;
        try {
            c = dbServ.getConnection();
            // do a starts with search
            PreparedStatement ps = c.prepareStatement("DELETE FROM budget where bud_id=?");
            ps.setLong(1, id);
            ps.executeUpdate();
        }catch(SQLException t){
            t.printStackTrace();
            c.rollback();
            throw t;
        }finally {
            c.close();

        }
    }

}

