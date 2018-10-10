/* Zack Chambers
 * The following work is done by Zack Chambers and no one else can take credit for it.
 */

package Final_Project;

public class Candy extends Snack {
	
	private double servingSize; //Servings per candy
	
	//no-arg constructor
	public Candy() {
		
	}
	
	public Candy(String productName, double price, int quantity, String dispenseLocation, int productId, String prodDescription, int dispenserID, int calories, double servingSize) {
		super(productName, price, quantity, dispenseLocation, productId, prodDescription, dispenserID, calories);
		this.servingSize = servingSize;
	}
	
	//Replicate an existing Candy object
	public Candy(Candy candy) {
		super(candy.getName(), candy.getPrice(), candy.getQuantity(), candy.getDispenseLocation(), candy.getProductId(), candy.getDescription(), candy.getDispenserID(), candy.getCalories());
		this.servingSize = candy.getServingSize();		
	}
	
	//Setters
	public void setServingSize(double servingSize){
		this.servingSize = servingSize;
	}
	//Getters
	public double getServingSize(){
		return servingSize;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", Serving Size: " + servingSize;
	}
	

}

