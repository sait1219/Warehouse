package Control;

import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.DefaultListModel;

import DAO.ProductDAO;
import DAO.ProductDBbyGCSQL;
import DAO.ProductDBbyGCS;
import DAO.ProductDBinMemory;

import javax.swing.*;

import Model.Customer;
import Model.Product;
import View.AddStockGUI;
import View.ChangeProductGUI;
import View.CreateNewCustomerGUI;
import View.CreateNewProductGUI;
import View.DeleteProductGUI;
import View.DispatchStockGUI;
import View.ListAllCustomersGUI;
import View.ListAllProductsGUI;
import View.MainView;
import View.MainViewInterface;

public class MainController {
    private ProductDAO productDB;
    private MainViewInterface mainView;
 

    public MainController(ProductDAO productDB, MainViewInterface mainView) {
        this.productDB = productDB;
        this.mainView = mainView;

        mainView.setAddStockButtonListener(this::addStockButtonOnClick);
        mainView.setChangeProductButtonListener(this::ChangeProductButtonOnClick);
        mainView.setCreatenewProductButtonListener(this::CreatenewProductButtonOnClick);
        mainView.setcreatenewcustomerButtonListener(this::CreateNewCustomerButtonOnClick);
        mainView.setListAllProductsButtonListener(this::listAllProductsButtonOnClick);
        mainView.setListAllCustomersButtonListener(this::listAllCustomersButtonOnClick);
        mainView.setDeleteProductButtonListener(this::DeleteProductButtonOnClick);
        mainView.setDispatchStockButtonListener(this::DispatchStockButtonOnClick);
    }

    private void listAllProductsButtonOnClick(ActionEvent e) {
    	ListAllProductsGUI listAllProducts = new ListAllProductsGUI();
        DefaultListModel<String> defaultListModel = new DefaultListModel<>();
        for (Product product : productDB.getAllProducts()) 
        {
            defaultListModel.addElement( product.toString() );
        }
        
        listAllProducts.setAllProductListDefaultModel(defaultListModel);
        mainView.setEnabled(false);

        listAllProducts.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) { }

            @Override
            public void windowClosing(WindowEvent e) { }

            @Override
            public void windowClosed(WindowEvent e) {
                mainView.setEnabled(true);
                mainView.requestFocus();
            }

            @Override
            public void windowIconified(WindowEvent e) { }

            @Override
            public void windowDeiconified(WindowEvent e) { }

            @Override
            public void windowActivated(WindowEvent e) { }

            @Override
            public void windowDeactivated(WindowEvent e) { }
        });
    }

    private void listAllCustomersButtonOnClick(ActionEvent e) {
    	ListAllCustomersGUI listAllCustomers = new ListAllCustomersGUI();
        DefaultListModel<String> defaultListModel = new DefaultListModel<>();
        for (Customer customer : productDB.getAllCustomers())
        {
            defaultListModel.addElement( customer.toString() );
        }
        
        listAllCustomers.setAllCustomersListDefaultModel(defaultListModel);
        mainView.setEnabled(false);

        listAllCustomers.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) { }

            @Override
            public void windowClosing(WindowEvent e) { }

            @Override
            public void windowClosed(WindowEvent e) {
                mainView.setEnabled(true);
                mainView.requestFocus();
            }

            @Override
            public void windowIconified(WindowEvent e) { }

            @Override
            public void windowDeiconified(WindowEvent e) { }

            @Override
            public void windowActivated(WindowEvent e) { }

            @Override
            public void windowDeactivated(WindowEvent e) { }
        });
    }

    public void compareProducts() {
    long startTime = System.currentTimeMillis();

    List<Product> productsFromGCS = productDB.getAllProducts();
    List<Product> productsFromSQL = customerDB.getAllProducts();

    boolean isAccurate = productsFromGCS.equals(productsFromSQL);

    long endTime = System.currentTimeMillis();
    System.out.println("Operation took " + (endTime - startTime) + " milliseconds");
    System.out.println("Accuracy: " + isAccurate);
}

    
    
    private void CreatenewProductButtonOnClick(ActionEvent e) {
    	  JDialog productDialog = new CreateNewProductGUI((MainView) mainView);
          productDialog.setVisible(true);
    }

    private void ChangeProductButtonOnClick(ActionEvent e) {
  	  JDialog productDialog = new ChangeProductGUI((MainView) mainView);
        productDialog.setVisible(true);
    }   
    
    private void DeleteProductButtonOnClick(ActionEvent e) {
    	  JDialog productDialog = new DeleteProductGUI((MainView) mainView);
          productDialog.setVisible(true);
    }   
    
    
    private void addStockButtonOnClick(ActionEvent e) {
  	  JDialog productDialog = new AddStockGUI((MainView) mainView);
        productDialog.setVisible(true);
    } 
    
    private void CreateNewCustomerButtonOnClick(ActionEvent e) {
    	  JDialog productDialog = new CreateNewCustomerGUI((MainView) mainView);
          productDialog.setVisible(true);
    } 
    
    private void DispatchStockButtonOnClick(ActionEvent e) {
  	  JDialog productDialog = new DispatchStockGUI((MainView) mainView);
        productDialog.setVisible(true);
  } 
   
    public static void main(String[] args) {
        new MainController(
           // new ProductDBinMemory(),
            ProductDBbyGCSQL,
             ProductDBbyGCS,
            new MainView()
        );
    }
}