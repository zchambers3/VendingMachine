/* Zack Chambers
 * The following work is done by Zack Chambers and no one else can take credit for it.
 */

package Final_Project;

public class Drink extends Product implements Comparable {

	private int ounces;
	
	//no-arg constructor
	public Drink(){
		
	}
	
	public Drink(String productName, double price, int quantity, String dispenseLocation, int productId, String prodDescription, int dispenserID, int ounces) {
		super(productName, price, quantity, dispenseLocation, productId, prodDescription, dispenserID);
		this.ounces = ounces;
	}
	
	//Replicate an existing Drink object
	public Drink(Drink drink){
		super(drink.getName(), drink.getPrice(), drink.getQuantity(), drink.getDispenseLocation(), drink.getProductId(), drink.getDescription(), drink.getDispenserID());
		this.ounces = drink.getOunces();
	}
	
	//Setters
	public void setOunces(int ounces){
		this.ounces = ounces;
	}
	
	//Getters
	public int getOunces(){
		return ounces;
	}
	
	public int compareTo(Drink drink){
		if(this.getName().charAt(0) < drink.getName().charAt(0))
			return -1;
		else if(this.getName().charAt(0) > drink.getName().charAt(0))
			return 1;
		else
			if(this.getPrice() < drink.getPrice())
				return -1;
			else if(this.getPrice() > drink.getPrice())
				return -1;
			else
				return 0;
	}
	
	
	@Override
	public String toString(){
		return super.toString() + ", Ounces: " + ounces;
	}
	
}
