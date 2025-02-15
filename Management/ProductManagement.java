package Management;

import java.util.Scanner;

import DAO.ProductDAO;
import DAO.ProductDBbySQL;
import Model.Customer;
import Model.Product;

public class ProductManagement {
    public static void main(String[] args) 
    {
        ProductDAO productDB = new ProductDBbySQL();
        ProductDAO customerDB = new ProductDBbySQL();       
        Scanner input = new Scanner(System.in);
    mainloop:
        while (true) {
            // print main menu
            System.out.println("Main:");
            System.out.println("===================");
            System.out.println("1 - Create new Product");
            System.out.println("2 - List of all Products");
            System.out.println("3 - Change Product");
            System.out.println("4 - Delete Product");
            System.out.println("5 - Add Stock");
            System.out.println("6 - Dispatch Stock");
            System.out.println("7 - Create new Customers");
            System.out.println("8 - List of all Customers");
            System.out.println("9 - Quit Program");
            System.out.print("Your choice: ");
            char choice  = input.nextLine().charAt(0);

            int item_id;
            int cust_id;
            switch (choice) {
                case '1':
                    System.out.print("Product Id: ");
                    item_id = input.nextInt();
                    input.nextLine();
                    System.out.print("Product Name: ");
                    String name = input.nextLine();
                    if (productDB.insertProduct(new Product(item_id, name))) {
                        System.out.println("Added New Product Successfully");
                    }break;

                    
                case '2':
                    for (Product item : productDB.getAllProducts()) {
                        System.out.println(item);
                    }break;                   
               
                    
                case '3':
                	System.out.print("Product Id: ");
                	item_id = input.nextInt();
                    input.nextLine();
                    Product item1 = productDB.getProductByID(item_id);
                    if ( item1 != null ) {
                    	System.out.print("Do you really want to change this product (Y/N)? ");
                        char answer = input.next().charAt(0);
                        input.nextLine();
                        if (answer == 'Y') {
                        	System.out.print("Product Name: ");
                            String Account1 = input.nextLine();
                          
                            System.out.print("Product Quantity: ");
                            double quantity = input.nextDouble();
                            input.nextLine();
                            if (productDB.updateProduct(item_id,Account1,quantity)) {
                                System.out.println("Updated Product Details!!!");
                                 }
                         }
                    }break;        	
                      
                    
                case '4':
                    System.out.print("Product Id: ");
                    item_id = input.nextInt();
                    input.nextLine();
                    if ( productDB.getProductByID(item_id) != null ) {
                        System.out.print("Do you really want to delete this product (Y/N)? ");
                        char answer = input.next().charAt(0);
                        input.nextLine();
                        if (answer == 'Y') {
                            if (productDB.removeProduct(item_id)) {
                                System.out.println("Product Deleted!");
                            }
                        }
                    }break;

                    
                case '5':
                    System.out.print("Product id: ");
                    item_id = input.nextInt();
                    input.nextLine();
                    Product item = productDB.getProductByID(item_id);
                    if ( item != null ) {
                        System.out.println("Quantity to add to stock: ");
                        double stock = input.nextDouble();
                        input.nextLine();
                        item.addStock(stock);
                        productDB.changeProduct(item_id, item);
                    }break;
               
                
                case '6':
                    System.out.print("Product id: ");
                    item_id = input.nextInt();
                    input.nextLine();
                    Product item2 = productDB.getProductByID(item_id);
                    if ( item2 != null ) {
                        System.out.println("Quantity to be dispatched: ");
                        double stock = input.nextDouble();
                        input.nextLine();
                        
                        System.out.println("Product name to be dispatched: ");
                        String product = input.nextLine();
                        System.out.println("Customer id to be dispatched: ");
                        cust_id = input.nextInt();
                        Customer item3 = customerDB.getCustomerByID(cust_id);
                        input.nextLine();
                        item2.dispatch(stock);
                        item3.addStock(stock);
                        productDB.changeProduct(item_id, item2);
                        productDB.changeDispatch(cust_id, product, item3);
                    }break;
                
                
                case '7':
                    System.out.print("Customer id: ");
                    cust_id = input.nextInt();
                    input.nextLine();
                    System.out.print("Customer name: ");
                    String cust_name = input.nextLine();
                    if (productDB.insertCustomer(new Customer(cust_id, cust_name))) {
                        System.out.println("Added new Customer successfully");
                    }
                    break;
                    
                    
                case '8':
                    for (Customer cust : customerDB.getAllCustomers()) {
                        System.out.println(cust);
                    }break;
                
                    
                case '9':
                    break mainloop; // while
                    
                    
                default:
                    System.out.println("Sorry, not yet implemented");
                    break;
            }
        }


    }
}