package Model;

public class Customer {
	private int cust_id;
    private String cust_name;
    private String item_name;
    private double quantity;
    
    public Customer(int cust_id,String cust_name,String item_name, double quantity){
    	this.cust_id = cust_id;
        this.cust_name = cust_name;
        this.item_name = item_name;
        this.quantity = quantity;
    }

    public Customer(int cust_id,String cust_name) {
    	this.cust_id = cust_id;
        this.cust_name = cust_name;
        this.item_name = "***";
        this.quantity = 0.0;
    }

    public int getId() {
        return cust_id;
    }

    public String getName() {
        return cust_name;
    }
    
    public double getQuantity() {
        return quantity;
    }
    
    public String getName1() {
        return item_name;
    }
    
    public void addStock(double stock) {
        this.quantity += Math.abs(stock);
    }
    
    public Customer clone1() {
        return new Customer(this.cust_id, this.cust_name);
    }
    
    public Customer clone2() {
        return new Customer(this.cust_id, this.cust_name, this.item_name, this.quantity);
    }
    @Override
    public String toString() {
        return "[" + cust_id + "] " + cust_name + " " + item_name + " (" + quantity + ")";
    }

}