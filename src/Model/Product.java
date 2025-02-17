package Model;

import Model.Product;

public class Product {
    private int item_id;
    private String item_name;
    private double quantity;
    
    public Product(int item_id, String item_name, double quantity) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.quantity = quantity;
    }
    
    
    public Product(int item_id, String item_name) {
        this(item_id, item_name, 0.0);
    }

    
    public int getId() {
        return item_id;
    }

    
    public String getName() {
        return item_name;
    }

    
    public double getQuantity() {
        return quantity;
    }

    
    public void setName(String item_name) {
        this.item_name = item_name;
    }

    public boolean dispatch(double stock) {
        stock = Math.abs(stock);
        if (this.quantity >= stock) {
            this.quantity -= stock;
            return true;
        }
        return false;
    }

    
    public void addStock(double stock) {
        this.quantity += Math.abs(stock);
    }
        
    
    public Product clone() {
        return new Product(this.item_id, this.item_name, this.quantity);
    }   
   
    
    @Override
    public String toString() {
        return "[" + item_id + "] " + item_name + " (" + quantity + ")";
    }
}