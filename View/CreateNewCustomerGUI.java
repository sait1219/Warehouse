package View;

import javax.swing.*;

import DAO.ProductDBbySQL;
import Model.Customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateNewCustomerGUI extends JDialog {
	private ProductDBbySQL customerDB = new ProductDBbySQL();
    private JTextField customerIdField;
    private JTextField customerNameField;
    private JButton submitButton;

    public CreateNewCustomerGUI(JFrame parent) {
        this.setModal(true);
        this.setTitle("Add Customer");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        customerIdField = new JTextField(15);
        customerNameField = new JTextField(15);
        submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customerid = customerIdField.getText();
                String customerName = customerNameField.getText();
                int customerId = Integer.parseInt(customerid);
                createCustomer(customerId,customerName);
                dispose();
            }
        });

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(new JLabel("Customer ID:"));
        add(customerIdField);
        add(new JLabel("Customer Name:"));
        add(customerNameField);
        add(submitButton);
        pack();
        setLocationRelativeTo(parent);
    }
    
    private void createCustomer(int customerId,String customerName) {
        customerDB.insertCustomer(new Customer(customerId, customerName));
        JOptionPane.showMessageDialog(this, "Added New Customer Successfully.");
    }
}