package View;

import javax.swing.*;

import DAO.ProductDBbySQL;
import Model.Customer;
import Model.Product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DispatchStockGUI extends JDialog {
	private ProductDBbySQL productDB = new ProductDBbySQL();
    private JTextField productIdField;
    private JTextField productNameField;
    private JTextField quantityField;
    private JTextField customerIdField;
    private JButton submitButton;

    public DispatchStockGUI(JFrame parent) {
        this.setModal(true);
        this.setTitle("Dispatch Product");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        productIdField = new JTextField(15);
        productNameField = new JTextField(15);
        quantityField = new JTextField(15);
        customerIdField = new JTextField(15);
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            
        	
        	@Override
            public void actionPerformed(ActionEvent e) {
                String productId = productIdField.getText();
                String productName = productNameField.getText();
                int productid = Integer.parseInt(productIdField.getText());
                double quantity = Double.parseDouble(quantityField.getText());
                String customerId = customerIdField.getText();
                int customerid = Integer.parseInt(customerIdField.getText());
                // Call a method to dispatch the product to the customer
                dispatchProduct(productid, productName, quantity, customerid);
                dispose();
            }
        });

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(new JLabel("Product ID:"));
        add(productIdField);
        add(new JLabel("Product Name:"));
        add(productNameField);
        add(new JLabel("Quantity:"));
        add(quantityField);
        add(new JLabel("Customer ID:"));
        add(customerIdField);
        add(submitButton);
        pack();
        setLocationRelativeTo(parent);
    }

    private void dispatchProduct(int productId, String productName, double quantity, int customerId) {
        // Implement the logic to dispatch the product to the customer
        Product product = productDB.getProductByID(productId);
        if (product != null) 
        {
        	    Customer customer = productDB.getCustomerByID(customerId);
        	    product.dispatch(quantity);
        	    customer.addStock(quantity);
                // Update product quantity
                productDB.changeProduct(productId,product);
                // Add entry to customer table
                productDB.changeDispatch(customerId, productName,customer);
                JOptionPane.showMessageDialog(this, "Product Dispatched Successfully.");
            }
        
        else 
        {
                JOptionPane.showMessageDialog(this, "Insufficient Quantity Available.");
            }
        } 
		
		
	private Product Product(double quantity) {			
			return null;
		}
		
	private Customer Customer(double quantity) {			
			return null;
		}
		
    }
