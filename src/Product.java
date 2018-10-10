/* Zack Chambers
 * The following work is done by Zack Chambers and no one else can take credit for it.
 */

package Final_Project;

public abstract class Product extends ErrorManagement {
	
	private String productName; 		// Name of product
	private double price; 			// Price of product
	private int quantity; 			// Quantity available of product
	private int temporaryQuantity; 		// Quantity available of product during transaction
	private String prodDescription;		// Product description
	private int dispenserID;		// ID of the dispenser which the item is located
	private String dispenseLocation; 	// Location in the dispenser
	private int productId; 			// Unique ID for product (used for tracking)
	
	//no-arg constructor
	public Product(){
		
	}
	
	//Constructor, requires name and ID (default to $0.00 price and 1 quantity?)
	public Product(String productName, double price, int quantity, String dispenseLocation, int productId, String prodDescription, int dispID){
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.temporaryQuantity = quantity;
		this.prodDescription = prodDescription;
		this.dispenseLocation = dispenseLocation;
		this.productId = productId;
		this.dispenserID = dispID;
	}
	
	//Setter methods
	public void setProductName(String name) {
		this.productName = name;
	}
	
	public void setProductId(int productId){
		this.productId = productId;
	}
	
	public void setProductDescription(String desc) {
		this.prodDescription = desc;
	}
	
	public void setPrice(double price){
		this.price = price;
	}
	
	public void setQuantity(int quantity){
		this.quantity = quantity;
		
		// Also reset temporary quantity
		this.temporaryQuantity = quantity;
	}
	
	public void setTemporaryQuantity(int tempQuantity){
		this.temporaryQuantity = tempQuantity;
	}
	
	public void setDispenseLocation(String location){
		this.dispenseLocation = location;
	}
	
	public void setDispenserID(int id){
		this.dispenserID = id;
	}
	
	
	//Getter methods
	public String getName() {
		return productName;
	}
	
	public int getProductId(){
		return productId;
	}
	
	public String getDescription() {
		return prodDescription;
	}
	
	public double getPrice(){
		return price;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public int getTemporaryQuantity(){
		return temporaryQuantity;
	}
	
	public String getDispenseLocation(){
		return dispenseLocation;
	}
	
	public int getDispenserID(){
		return dispenserID;
	}

	
	public int compareName(Product prod) {
		if (this.getName().charAt(0) > prod.getName().charAt(0)) {
			return 1;
		} else if (this.getName().charAt(0) == prod.getName().charAt(0) ) {
			if (this.getName().charAt(1) > prod.getName().charAt(1)) {
				return 1;
			} else if (this.getName().charAt(1) == prod.getName().charAt(1) ) {
				if (this.getName().charAt(2) > prod.getName().charAt(2)) {
					return 1;
				} else {
					return 0;
				}
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}
	
	
	public int compareQty(Product prod) {
		if (this.getQuantity() > prod.getQuantity()) {
			return 1;
		} else {
			return 0;
		}
	}
	
	
	@Override
	public String toString(){
		return "Product Name: " + productName + ", Price: " + price + ", Quantity: " + quantity + 
                        ", Dispenser Location: " + dispenseLocation + ", Product ID: " + productId + ", Dispenser ID: " + dispenserID;
	}

}
