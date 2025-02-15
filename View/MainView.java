package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class MainView extends JFrame implements MainViewInterface
{
    private JButton createproductButton = new JButton("Create Product");
    private JButton ListproductButton = new JButton("List all Products");
    private JButton addstockButton = new JButton("add stock");
    private JButton changeproductButton = new JButton("Update product details");
    private JButton DeleteproductButton = new JButton("Delete Product");
    private JButton dispatchstockButton = new JButton("Disptch");
    private JButton createnewcustomerButton = new JButton("Create Customer");
    private JButton listcustomersButton = new JButton("List all customers");
    private JButton okButton = new JButton("Okay");

    public MainView() {
        setTitle("Main");
        setSize(400, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new FlowLayout());
        JPanel outerPanel = new JPanel();
        outerPanel.setBorder(new EmptyBorder(5,5,5,5));
        JPanel centerPanel = new JPanel( new GridLayout(3, 2) );
        outerPanel.add(centerPanel);
        JPanel bottomPanel = new JPanel( new GridLayout(3, 2) );
        addCenterComponent(centerPanel);
        addButtons(bottomPanel);
        add(topPanel, BorderLayout.NORTH);
        add(outerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }

    private void addButtons(JPanel bottomPanel)
    {
        bottomPanel.setBorder(new EmptyBorder(10,10,10,10));
        bottomPanel.add(createproductButton);
        bottomPanel.add(ListproductButton);
        bottomPanel.add(addstockButton);
        bottomPanel.add(changeproductButton);
        bottomPanel.add(DeleteproductButton);
        bottomPanel.add(dispatchstockButton);
        bottomPanel.add(createnewcustomerButton);
        bottomPanel.add(listcustomersButton);      
    }
    

    private void addCenterComponent(JPanel centerPanel) 
    {
        JLabel label = new JLabel("HARDWARE WAREHOUSE MANAGEMENT SYSTEM ");
        label.setFont(new Font("Arial", Font.BOLD, 16));  // Set font to bold
        label.setHorizontalAlignment(SwingConstants.CENTER); 
        centerPanel.add(label);
        JLabel subheading = new JLabel("Please Make a selection from the following Options");
        subheading.setHorizontalAlignment(SwingConstants.CENTER); 
        centerPanel.add(subheading);
    }
    

    @Override
    public void setListAllProductsButtonListener(ActionListener listener) {
    	ListproductButton.addActionListener(listener);
    }
    
    
    @Override
    public void setListAllCustomersButtonListener(ActionListener listener) {
    	listcustomersButton.addActionListener(listener);
    }

    
	@Override
	public void showMessage(String message) {	}
	

	@Override
	public void setCreatenewProductButtonListener(ActionListener listener) {
		createproductButton.addActionListener(listener);		
	}

	
	@Override
	public void setChangeProductButtonListener(ActionListener listener) {
		changeproductButton.addActionListener(listener);
	}
	
	
	@Override
	public void setOkayButtonListener(ActionListener listener) {	}

	
	@Override
	public void setDeleteProductButtonListener(ActionListener listener) {
		DeleteproductButton.addActionListener(listener);		
	}

	
	@Override
	public void setAddStockButtonListener(ActionListener listener) {
		addstockButton.addActionListener(listener);		
	}
	
	
	@Override
	public void setcreatenewcustomerButtonListener(ActionListener listener) {
		createnewcustomerButton.addActionListener(listener);		
	}
	
	
	@Override
	public void setDispatchStockButtonListener(ActionListener listener) {
		dispatchstockButton.addActionListener(listener);		
	}
	
}