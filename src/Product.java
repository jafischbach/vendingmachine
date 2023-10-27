

public class Product {

	private String name;
	private double price;
	private int qty;
	
	public Product(String n, double p) {
		name = n;
		price = p;
		qty = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public int getQty() {
		return qty;
	}
	
	public void setPrice(double p) {
		price = p;
	}
	
	public String dispenseItem() {
		if(qty > 0) {
			qty--;
			return "Enjoy your new "+name+"!";
		} else {
			return "Out of stock.";
		}
	}
	
	public void stockMachine(int numItems) {
		qty += numItems;
	}
	
	public String toString() {
		return name + "\n\tUnit price: $"+String.format("%.2f", price)+" Qty: "+qty;
	}
	
}
