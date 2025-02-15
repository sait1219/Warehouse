package View;

import java.awt.event.ActionListener;

public interface MainViewInterface {

    void setCreatenewProductButtonListener(ActionListener listener);
    
    void setOkayButtonListener(ActionListener listener);
    
    void setListAllProductsButtonListener(ActionListener listener);
    
    void setListAllCustomersButtonListener(ActionListener listener);
    
	void setChangeProductButtonListener(ActionListener listner);
	
	void setDeleteProductButtonListener(ActionListener listner);
	
	void setAddStockButtonListener(ActionListener listner);

	void setcreatenewcustomerButtonListener(ActionListener listener);

	void setDispatchStockButtonListener(ActionListener listener);
	
	void setEnabled(boolean Enabled);

	void requestFocus();
	
	void showMessage(String message);

}