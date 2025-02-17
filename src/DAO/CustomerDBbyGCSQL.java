package DAO;

import Model.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Customer;


public class CustomerDBbyGCSQL implements ProductDAO {
    private final String jdbcUrl = "private final String jdbcUrl = "jdbc:mysql://google/customerdb?cloudSqlInstance=my-project:us-central1:customer-instance&socketFactory=com.google.cloud.sql.mysql.SocketFactory&user=root&password=acs@2025";

    
    @Override
public boolean insertCustomer(Customer customer) {
    String query = "INSERT INTO customers (id, name) VALUES (?, ?)";
    try (Connection conn = DriverManager.getConnection(jdbcUrl);
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, customer.getId());
        stmt.setString(2, customer.getName());
        stmt.executeUpdate();
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


    @Override
public List<Customer> getAllCustomers() {
    List<Customer> customerList = new ArrayList<>();
    String query = "SELECT id, name FROM customers";

    try (Connection conn = DriverManager.getConnection(jdbcUrl);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            customerList.add(new Customer(id, name));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return customerList;
}


    @Override
public Customer getCustomerByID(int id) {
    String query = "SELECT id, name FROM customers WHERE id = ?";
    
    try (Connection conn = DriverManager.getConnection(jdbcUrl);
         PreparedStatement stmt = conn.prepareStatement(query)) {
        
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            return new Customer(rs.getInt("id"), rs.getString("name"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return null; // Return null if the customer is not found
}


    @Override
    public boolean updateCustomer(int id, String name) {
        // Implement the logic to update a customer in Google Cloud SQL
    }

    @Override
    public boolean removeCustomer(int id) {
        // Implement the logic to remove a customer from Google Cloud SQL
    }

    @Override
    public boolean changeCustomer(int id, Customer customer) {
        // Implement the logic to change a customer in Google Cloud SQL
    }
}
