package View;

import javax.swing.*;

import DAO.ProductDBbySQL;
import Model.Product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStockGUI extends JDialog {
    private ProductDBbySQL productDB = new ProductDBbySQL();   
    private JTextField productIdField;
    private JTextField newquantityField;
    private JButton submitButton;

    public AddStockGUI(JFrame parent) {
        this.setModal(true);
        this.setTitle("Add Product");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Initialize the text fields and button
        productIdField = new JTextField(15);
        newquantityField = new JTextField(15);
        submitButton = new JButton("Submit");

        // Action listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productId = productIdField.getText();
                String newquantity = newquantityField.getText();
                
                // Parse productId and newquantity into appropriate types
                int productid = Integer.parseInt(productId);
                double quantity = Double.parseDouble(newquantity);
                addStock( productid, quantity);
                dispose();
            }
        });

        // Layout setup
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(new JLabel("Product ID:"));
        add(productIdField);
        add(new JLabel("Add Stock:"));
        add(newquantityField);
        add(submitButton);

        // Final layout adjustments
        pack();
        setLocationRelativeTo(parent); // Center the dialog relative to the parent frame
    }
    
    
   private void addStock(int productId,double quantity){
	   Product product = productDB.getProductByID(productId);
    	   
           if (product != null) {
               // Add stock to the product
               product.addStock(quantity);
               
               // Update product in the database
               productDB.changeProduct(productId, product);
               JOptionPane.showMessageDialog(AddStockGUI.this, "Stock Added Successfully!");
           } else {
               JOptionPane.showMessageDialog(AddStockGUI.this, "Product Quantity Insufficient!");
           }
       }
    	
    }
