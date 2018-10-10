/* Zack Chambers
 * The following work is done by Zack Chambers and no one else can take credit for it.
 */

package Final_Project;

import java.util.ArrayList;

public class Dispenser {

		private ArrayList<Product> products = new ArrayList();
		private int maxProducts = 80;
		private int maxProductsPerRow = 5;
		
		private String[] objType = new String[20];          // Type of product in vending machine
		private float balance;                              // Amount of money for transaction
		private float moneyInMachine;                       // Amount of money in vending machine
		private String status;                              // Status of vending machine
		private String[] transaction;                       // Record of current transaction
                //private ArrayList<transaction> transactions; // Records of all of the transactions on vending machine 

		//no-arg constructor
		Dispenser(){
			
		}
		
		Dispenser(ArrayList<Product> products){
			this.products = products;
		}
		
		/**
		 * addProduct()
		 * 
		 * Adds a product object to the dispenser
		 * 
		 * @param product
		 */
		public void addProduct(Product product){
			// Check if dispenser is full
			if (dispenserFull()) {
				System.out.println("Dispenser is full. Maximum number of products is " + maxProducts);
			} else {
				// Check that the row is not full
				if (product.getQuantity() > maxProductsPerRow) {
					System.out.println("Cannot have more than " + maxProductsPerRow + " items in each row");
				} else {
					// Add it to the ArrayList
					products.add(product);
				}
			}
		}
		
		/**
		 * removeProduct()
		 * 
		 * Remove Product from product ArrayList
		 * 
		 * @param id
		 */
		public void removeProduct(int id) {
			for (int i = 0; i < products.size(); i++) {
				if (products.get(i).getProductId() == id) {
					products.remove(i);
				}
			}
			
		// TODO: Need to create inventory counts, dispenser views, etc.
		}
		
		/**
		 * dispenserFull()
		 * 
		 * Checks the maximum product count in the dispenser.
		 * 
		 * @return boolean
		 */
		public boolean dispenserFull() {
			boolean tempResult = false;
			
			if (products.size() >= maxProducts)
				tempResult = true;
			
			return tempResult;
		}

		// Setters
		public void setMaxProducts(int max) {
			maxProducts = max;
		}
		
		public void setMaxProductsPerRow(int max) {
			maxProductsPerRow = max;
		}
		
		// Set Product to location
                // public setProduct(){
                //
                //}
                //
		// Dispense Product from Dispenser
                // public dispenseProduct(){
                //
                //}
		
		// Getters
		public ArrayList<Product> getProducts() {
			return products;
		}
		
		public int getMaxProducts() {
			return maxProducts;
		}
		
		public int getMaxProductsPerRow() {
			return maxProductsPerRow;
		}
		
		public int getTotalProductCount() {
			int tempCount = 0;
			
			for (Product prods : products) {
				tempCount += prods.getQuantity();
			}
			
			return tempCount;
		}
		
		public String getProductsToString(){
			String tempProds = "";
			
			for(int i = 0; i < products.size(); i++){
				tempProds += products.get(i).toString() + "\n";
			}
			
			return tempProds;
		}
	
}

