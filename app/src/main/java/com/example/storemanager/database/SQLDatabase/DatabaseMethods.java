package com.example.storemanager.database.SQLDatabase;



import com.example.storemanager.database.Dish;
import com.example.storemanager.database.FinanceForMonths;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseMethods {
    private Connection conn;
    private PreparedStatement stmt;
    
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/swen2007project","swen2007Project","testingSWEN");
            System.out.println("Connected");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public boolean openConnection(){
        try {
            // db parameters
            String url = "jdbc:mysql://localhost:3306/swen2007project";
            String user = "swen2007Project";
            String password = "testingSWEN";
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            if (conn!= null){
                System.out.println("Connection Successful");
                return true;
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }// openConnection

    public void closeConnection() {
        try
        {
            if(conn!=null)
                conn.close();
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }// closeConnection
    
    /**
     * Used in Balance GUI
     * @param m
     * @return 
     **/
    
    public FinanceForMonths findMonth(int m){
        FinanceForMonths theOne = null;
        //the mysql insert statement
        String query = "select * from financeformonths where months = ?";

        try{
            //create the mysql insert preparedstatement
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, m);

            //execute the preparedstatement
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                theOne = new FinanceForMonths(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        }
        catch (SQLException e){
            System.out.println("Got an exception!");
            System.out.println(e.getMessage());
        }
        return theOne;
    }// findMonth
    
    public void updateMonthSPay(String spent, int month){
        String query = "UPDATE financeformonths SET moneyspent = (?) WHERE months = (?)";
        try{
            stmt = conn.prepareStatement(query); // our SQL SELECT query.
            stmt.setString(1, spent);
            stmt.setInt(2, month);
            stmt.execute();
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }// updateMonthSPay
    
    public void updateMonthEPay(String earned, int month){
        String query = "UPDATE financeformonths SET moneyearned = (?) WHERE months = (?)";
        try{
            stmt = conn.prepareStatement(query); // our SQL SELECT query.
            stmt.setString(1, earned);
            stmt.setInt(2, month);
            stmt.execute();
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }// updateMonthSPay

    // Add new meals
    public Dish addNewMeals(Dish meal){
        try{
            String query = "insert into trudish (dishName, price, timesOrder) values (?,?,?)";
            stmt = conn.prepareStatement(query); // our SQL SELECT query.
            stmt.setString(1, meal.getDish());
            stmt.setDouble(2, meal.getPrice());
            stmt.setInt(3, meal.getTimesOrder());
            stmt.execute();
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        return null;
    }// addNewMeals
    

    // Finds the price of the dish entered
    public Dish findPrice(String name){
            Dish theone = null;

            return theone;
    } // findPrice
    
    
    public List<Dish> getDishByCriteria() {

        if (openConnection())
        {
            try{
                List<Dish> items = new ArrayList<>();
                Dish temp;
                String query;
                query ="SELECT * FROM trudish order by dishName asc";
                stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) // iterate through the java resultset
                {
                    temp = new Dish(rs.getString("dishName"),rs.getDouble("price"),rs.getInt("timesOrder"));
                    items.add(temp);
                }
                stmt.close();
                closeConnection();
                return items;
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        closeConnection();
        return null;
    }
}
