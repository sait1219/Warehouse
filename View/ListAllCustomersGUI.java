package View;

import java.awt.BorderLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class ListAllCustomersGUI extends JFrame {

    private JList<String> allCustomersList;

    public ListAllCustomersGUI() {
        setSize(300, 600);
        setTitle("All Customers");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addComponents();
        setVisible(true);
        pack();
    }

    
    private void addComponents() {
        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane();
        allCustomersList = new JList<>();
        scrollPane.setViewportView(allCustomersList);
        add(scrollPane, BorderLayout.CENTER);
    }

    
    public void setAllCustomersListDefaultModel(DefaultListModel<String> defaultModel) {
        allCustomersList.setModel(defaultModel);
    }
}