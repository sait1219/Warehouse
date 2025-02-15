package View;

import javax.swing.*;

import DAO.ProductDBbySQL;
import Model.Product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteProductGUI extends JDialog {
	private ProductDBbySQL productDB = new ProductDBbySQL();
    private JTextField productIdField;
    private JButton submitButton;

    public DeleteProductGUI(JFrame parent) {
        this.setModal(true);
        this.setTitle("Delete Product");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        productIdField = new JTextField(15);
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            
        	
        	@Override
            public void actionPerformed(ActionEvent e) {
                String productid = productIdField.getText();
                int productId = Integer.parseInt(productid);
                deleteProduct(productId);
                dispose();
            }
        });

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(new JLabel("Product ID:"));
        add(productIdField);
        add(submitButton);
        pack();
        setLocationRelativeTo(parent);
    }
    
    
    private void deleteProduct(int productId) {
    	Product product = productDB.getProductByID(productId);
    			// Call a method to delete the product from the database
                if(product != null) {
                	productDB.removeProduct(productId);
                	JOptionPane.showMessageDialog(this, "Product Deleted Successfully.");
                }
                else 
                {
                	JOptionPane.showMessageDialog(this, "Product Not Found.");
                }
                
    }
}