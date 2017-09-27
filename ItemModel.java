package assignment;

public class ItemModel {
	int id;
	String name;
	String details;
	int quantity;
	double price;
	double eachTotal;

	public double getEachTotal() {
		return eachTotal;
	}
	public void setEachTotal(double eachTotal) {
		this.eachTotal = eachTotal;
	}
	public ItemModel(){
		
	}
	public ItemModel(int id, String name, double price, int quantity, String details) {
		super();
		this.id = id;
		this.name = name;
		this.details = details;
		this.quantity = quantity;
		this.price = price;
	}


	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String toString(){
		return name;
	}
	

}