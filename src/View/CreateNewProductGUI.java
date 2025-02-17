package View;

import javax.swing.*;

import DAO.ProductDBbySQL;
import Model.Product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateNewProductGUI extends JDialog {
	
	private ProductDBbySQL productDB =new ProductDBbySQL();
    private JTextField productIdField;
    private JTextField productNameField;
    private JButton submitButton;

    public CreateNewProductGUI(JFrame parent) {
        this.setTitle("Add Product");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        productIdField = new JTextField(15);
        productNameField = new JTextField(15);
        submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productid = productIdField.getText();
                String productName = productNameField.getText();
                int productId = Integer.parseInt(productid);
                createCustomer(productId,productName);
                dispose();
            }
        });

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(new JLabel("Product ID:"));
        add(productIdField);
        add(new JLabel("Product Name:"));
        add(productNameField);
        add(submitButton);
        pack();
        setLocationRelativeTo(parent);
    }
    
    private void createCustomer(int productId,String productName) {
        productDB.insertProduct(new Product(productId, productName));
        JOptionPane.showMessageDialog(this, "Added new product successfully.");
    }
}