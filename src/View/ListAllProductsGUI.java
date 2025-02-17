package View;

import java.awt.BorderLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class ListAllProductsGUI extends JFrame {

    private JList<String> allProductList;

    public ListAllProductsGUI() {
        setSize(300, 600);
        setTitle("All Products");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addComponents();
        setVisible(true);
        pack();
    }

    
    private void addComponents() {
        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane();
        allProductList = new JList<>();
        scrollPane.setViewportView(allProductList);
        add(scrollPane, BorderLayout.CENTER);
    }

    
    public void setAllProductListDefaultModel(DefaultListModel<String> defaultModel) {
        allProductList.setModel(defaultModel);
    }
}