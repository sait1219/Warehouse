package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Customer;
import Model.Product;

public class ProductDBbySQL implements ProductDAO {
    private static final String SQL_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String SQL_SERVER = "127.0.0.1";
    private static final String SQL_SERVER_PORT = "3306";
    private static final String SQL_DATABASE = "Customer";
    private static final String SQL_USERNAME = "Customer";
    private static final String SQL_PASSWORD = "secret123";
    private Connection sqlConnection = null;

    public ProductDBbySQL() {
        //SQL connector is loaded properly
        try {
            Class.forName(SQL_DRIVER);
        }
        catch (ClassNotFoundException e) {
            System.err.println("Oops, SQL connector not installed!");
            System.exit(-1);
        }
        openSQLConnection();
    }

    private void openSQLConnection() {    
        try {
            sqlConnection = DriverManager.getConnection("jdbc:mariadb://" +
                                    SQL_SERVER + ":" + SQL_SERVER_PORT + "/" +
                                    SQL_DATABASE, SQL_USERNAME, SQL_PASSWORD);
        } catch (SQLException e) {
            System.err.println("Got some problem when establishing SQL connection");
            e.printStackTrace();
            System.exit(-2);
        }
    }

    
    @Override
    public boolean insertProduct(Product item) {
        // INSERT INTO products (item_id, item_name, quantity) VALUES (?, ?, ?);
        try {
            PreparedStatement sqlQuery = sqlConnection.prepareStatement(
                "INSERT INTO products (item_id, item_name, quantity) VALUES (?, ?, ?)"
            );
            sqlQuery.setInt(1, item.getId() );
            sqlQuery.setString(2, item.getName() );
            sqlQuery.setDouble(3, item.getQuantity() );
            return sqlQuery.executeUpdate()==1;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    @Override
    public Product getProductByID(int item_id) {
    	// SELECT item_id, item_name, quantity FROM products WHERE item_id=?;
        try {
            PreparedStatement sqlQuery = sqlConnection.prepareStatement(
                "SELECT item_id, item_name, quantity FROM products WHERE item_id=?"
            );
            sqlQuery.setInt(1, item_id);
            ResultSet sqlTable = sqlQuery.executeQuery();
            if ( ! sqlTable.next() ) return null;
            int item_id1 = sqlTable.getInt("item_id");
            String item_name = sqlTable.getString("item_name");
            double quantity = sqlTable.getDouble("quantity");
            
            return new Product(item_id1, item_name, quantity);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    @Override
    public Customer getCustomerByID(int cust_id) {
    	// SELECT cust_id, cust_name,item_name, quantity FROM customers WHERE cust_id=?;
        try {
            PreparedStatement sqlQuery = sqlConnection.prepareStatement(
                "SELECT cust_id, cust_name,item_name, quantity FROM customers WHERE cust_id=?"
            );
            sqlQuery.setInt(1, cust_id);
            ResultSet sqlTable = sqlQuery.executeQuery();
            if ( ! sqlTable.next() ) return null;
            
            int item_id1 = sqlTable.getInt("cust_id");
            String cust_name = sqlTable.getString("cust_name");
            String item_name = sqlTable.getString("item_name");
            double quantity = sqlTable.getDouble("quantity");
            return new Customer(cust_id,cust_name, item_name, quantity);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

  
    @Override
    public List<Product> getAllProducts() {
        // SELECT item_id, item_name, quantity FROM product;
        List<Product> accountList = new ArrayList<>();
        try {
            Statement sqlQuery = sqlConnection.createStatement();
            ResultSet sqlTable = 
                sqlQuery.executeQuery("SELECT item_id, item_name, quantity FROM products");
            while ( sqlTable.next() ) {
                int item_id = sqlTable.getInt("item_id");
                String item_name = sqlTable.getString("item_name");
                double quantity = sqlTable.getDouble("quantity");
                accountList.add(new Product(item_id, item_name, quantity));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return accountList;
    }

    
    @Override
    public boolean changeProduct(int item_id, Product item) {
        removeProduct(item_id);
        return insertProduct(item);
    }
    
    
    @Override
    public boolean changeDispatch(int cust_id,String item_name, Customer quantity) {
    	//UPDATE customers SET item_name = ?, quantity = ? WHERE cust_id = ?;
    	try {
            PreparedStatement sqlQuery = sqlConnection.prepareStatement(
            		"UPDATE customers SET item_name = ?, quantity = ? WHERE cust_id = ?"
            );
            sqlQuery.setString(1, item_name);
            sqlQuery.setDouble(2, quantity.getQuantity());
            sqlQuery.setInt(3, cust_id);
            return sqlQuery.executeUpdate() == 1;
    	}
            catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
    }
    
    
    @Override
    public boolean updateProduct(int item_id,String item_name,double quantity) {
    	//UPDATE products SET item_name = ?, quantity = ? WHERE item_id = ?
    	try {
            PreparedStatement sqlQuery = sqlConnection.prepareStatement(
            		"UPDATE products SET item_name = ?, quantity = ? WHERE item_id = ?"
            		
            );
            sqlQuery.setString(1, item_name);
            sqlQuery.setDouble(2, quantity);
            sqlQuery.setInt(3, item_id);
            return sqlQuery.executeUpdate() == 1;
    	}
            catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
    }

    
    @Override
    public boolean removeProduct(int item_id) {
        // DELETE FROM products WHERE item_id = ?
        try {
            PreparedStatement sqlQuery = sqlConnection.prepareStatement(
                "DELETE FROM products WHERE item_id = ?"
            );
            sqlQuery.setInt(1, item_id);
            return sqlQuery.executeUpdate() == 1;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    @Override
    public boolean insertCustomer(Customer customer) {
        //  "INSERT INTO customers (cust_id, cust_name, item_name, quantity) VALUES (?, ?, ?, ?)"
        try {
            PreparedStatement sqlQuery = sqlConnection.prepareStatement(
                "INSERT INTO customers (cust_id, cust_name, item_name, quantity) VALUES (?, ?, ?, ?)"
            );
            sqlQuery.setInt(1, customer.getId() );
            sqlQuery.setString(2, customer.getName() );
            sqlQuery.setString(3,customer.getName1());
            sqlQuery.setDouble(4,customer.getQuantity());
            return sqlQuery.executeUpdate()==1;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    @Override
    public List<Customer> getAllCustomers() {
        // SELECT cust_id,cust_name, item_name, quantity FROM customers;
        List<Customer> custList = new ArrayList<>();
        try {
            Statement sqlQuery = sqlConnection.createStatement();
            ResultSet sqlTable = 
                sqlQuery.executeQuery("SELECT cust_id,cust_name, item_name, quantity FROM customers");
            while ( sqlTable.next() ) {
                int cust_id = sqlTable.getInt("cust_id");
                String cust_name = sqlTable.getString("cust_name");
                String item_name = sqlTable.getString("item_name");
                double quantity = sqlTable.getDouble("quantity");
                custList.add(new Customer(cust_id, cust_name, item_name, quantity));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return custList;
    }
}