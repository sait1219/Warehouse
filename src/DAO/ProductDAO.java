package DAO;

import java.util.List;

import Model.Customer;
import Model.Product;

public interface ProductDAO {
	
	// create aka insert
    public boolean insertProduct(Product item);
    
    public boolean insertCustomer(Customer customer);

    // read
    public Product getProductByID(int item_id);
    
    public Customer getCustomerByID(int item_id);
    
    
    //List
    public List<Product> getAllProducts();
    
    public List<Customer> getAllCustomers();

    
    // update
    public boolean changeProduct(int item_id,Product item);
    
    public boolean updateProduct(int item_id,String item_name,double quantity);
    
	public boolean changeDispatch(int cust_id,String item_name,Customer quantity);
    
	
    // delete
    public boolean removeProduct(int item_id);
    
    
	
	

}
