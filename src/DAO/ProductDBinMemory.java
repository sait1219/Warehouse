package DAO;

import java.util.ArrayList;
import java.util.List;

import Model.Customer;
import Model.Product;

public class ProductDBinMemory implements ProductDAO {
    // this is executed before CTOR (pre-CTOR)
    private List<Product> productList = new ArrayList<>();
    private List<Customer> customerList = new ArrayList<>();
    
    public ProductDBinMemory() {
        insertProduct(new Product(1, "hinges", 944.0));
        insertProduct(new Product(2, "sliding chanel", 3750.0));
    }

    @Override
    public boolean insertProduct(Product item) {
        Product tmpItem = getProductByID( item.getId() );
        if (tmpItem != null) return false;
        productList.add( item.clone() );
        return true;
    }

    @Override
    public Product getProductByID(int item_id) {
        for (int i=0; i < productList.size(); i++) {
            if ( productList.get(i).getId() == item_id ) {
                return productList.get(i).clone();
            }
        }
        return null;
    }
    
    @Override
    public Customer getCustomerByID(int cust_id) {
        for (int i=0; i < customerList.size(); i++) {
            if ( customerList.get(i).getId() == cust_id ) {
                return customerList.get(i).clone2();
            }
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> copyList = new ArrayList<>( productList.size() );
        //-- this is not the same: copyList.addAll(accountList);
        for (Product product : productList) {
            copyList.add( product.clone() );
        }
        return copyList;
    }
    
    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> copyList = new ArrayList<>( customerList.size() );
        //-- this is not the same: copyList.addAll(accountList);
        for (Customer customer : customerList) {
            copyList.add( customer.clone2() );
        }
        return copyList;
    }
    @Override
    public boolean changeProduct(int item_id, Product item) {
        throw new UnsupportedOperationException("Unimplemented method 'changeAccount'");
    }
    
    @Override
    public boolean updateProduct(int item_id, String item_name,double quantity) {
        throw new UnsupportedOperationException("Unimplemented method 'changeAccount'");
    }
    
    @Override
    public boolean changeDispatch(int cust_id, String item_name,Customer quantity) {
        throw new UnsupportedOperationException("Unimplemented method 'changeAccount'");
    }

    @Override
    public boolean removeProduct(int item_id) {
        throw new UnsupportedOperationException("Unimplemented method 'removeAccount'");
    }
    
    @Override
    public boolean insertCustomer(Customer customer) {
        Customer tmpItem = getCustomerByID( customer.getId() );
        if (tmpItem != null) return false;
        customerList.add( customer.clone1() );
        return true;
    }

}