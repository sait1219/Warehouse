package View;

import javax.swing.*;

import DAO.ProductDBbySQL;
import Model.Product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeProductGUI extends JDialog {
	private ProductDBbySQL productDB = new ProductDBbySQL();
    private JTextField productIdField;
    private JTextField newProductNameField;
    private JTextField newquantityField;
    private JButton submitButton;

    public ChangeProductGUI(JFrame parent) {
        this.setModal(true);
        this.setTitle("Update Product");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        productIdField = new JTextField(15);
        newProductNameField = new JTextField(15);
        newquantityField = new JTextField(15);
        submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productId = productIdField.getText();
                String newProductName = newProductNameField.getText();
                String newQuantity = newquantityField.getText();
                int productid = Integer.parseInt(productId);
                double quantity = Double.parseDouble(newQuantity);
                // Call a method to update the product in the database
                updateProduct(productid,newProductName,quantity);
                dispose();
            }
            
        });
        
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(new JLabel("Product ID:"));
        add(productIdField);
        add(new JLabel("New Product Name:"));
        add(newProductNameField);
        add(new JLabel("New Quantity:"));
        add(newquantityField);
        add(submitButton);
        pack();
        setLocationRelativeTo(parent);
    }
    
    private void updateProduct(int productId,String productName,double quantity) {
        Product product = productDB.getProductByID(productId);   
        if (product != null) {
        productDB.updateProduct(productId, productName,quantity);
        JOptionPane.showMessageDialog(this, "Updated Product Details Successfully.");
        }
        else 
        {
            JOptionPane.showMessageDialog(this, "Product Not Available.");
        }
  }
}