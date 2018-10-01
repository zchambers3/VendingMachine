/* Zack Chambers
 * The following work is done by Zack Chambers and no one else can take credit for it.
 */

package Final_Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;

public class Global_InventoryManagement extends Product implements Comparable {
	
	ArrayList<Product> allProducts = new ArrayList<Product>();
	ArrayList<Product> nameSearchResults = new ArrayList<Product>();
	
	Global_InventoryManagement() {
		
	}
	
	/**
	 * csvInventoryImport()
	 * 
	 * NOTE:  
	 * 		Currently assuming that there are only have 2 vending machines to track for simplicity.
	 * 
	 * Imports inventory from CSV file
     * @param mainDisp
     * @param dispenser2
	 * @throws IOException 
	 */
	public void csvInventoryImport(Dispenser mainDisp, Dispenser dispenser2) throws IOException {
		
		// Attempt to read inventory from file
		try {
			
			CSVReader inventory1 = new CSVReader(new FileReader("inventory_1.csv"), ',', '"', 1);
			CSVReader inventory2 = new CSVReader(new FileReader("inventory_2.csv"), ',', '"', 1);
			
			// Save entries for each into lists
			List<String[]> inventory1List = inventory1.readAll();
			List<String[]> inventory2List = inventory2.readAll();
			
			for (int i = 0; i < inventory1List.size(); i++) {
				// Create Product objects and add them to first vending machine
				switch (inventory1List.get(i)[0]) {
					case "Candy":
						addCandy(mainDisp, inventory1List.get(i));
						break;
					case "Chips":
						addChips(mainDisp, inventory1List.get(i));
						break;
					case "Drink":
						addDrink(mainDisp, inventory1List.get(i));
						break;
					case "Gum":
						addGum(mainDisp, inventory1List.get(i));
						break;
					default:
						// Type does not exist
						break;
				}
				
//				System.out.print(inventory1List.get(i)[0] + " : "); // Product Type
//				System.out.print(inventory1List.get(i)[1] + " : "); // ID
//				System.out.print(inventory1List.get(i)[2] + " : "); // Name
//				System.out.print(inventory1List.get(i)[3] + " : "); // Price
//				System.out.print(inventory1List.get(i)[4] + " : "); // Qty
//				System.out.print(inventory1List.get(i)[5] + " : "); // Location
//				System.out.print(inventory1List.get(i)[6] + " : "); // Desc
//				System.out.print(inventory1List.get(i)[7] + " : "); // Calories
//				System.out.print(inventory1List.get(i)[8] + " : "); // Serving Size
//				System.out.print(inventory1List.get(i)[9] + " : "); // Ounces
//				System.out.print(inventory1List.get(i)[10] + " : "); // Baked?
//				System.out.print(inventory1List.get(i)[11] + " : "); // Pack Size
//				System.out.print(inventory1List.get(i)[12] + " : "); // Sugarfree?
//				System.out.println(inventory1List.get(i)[13] + " : "); // Machine Number / Dispenser ID
			}
			
			for (int i = 0; i < inventory2List.size(); i++) {
				// Create Product objects and add them to first vending machine
				switch (inventory2List.get(i)[0]) {
					case "Candy":
						addCandy(dispenser2, inventory2List.get(i));
						break;
					case "Chips":
						addChips(dispenser2, inventory2List.get(i));
						break;
					case "Drink":
						addDrink(dispenser2, inventory2List.get(i));
						break;
					case "Gum":
						addGum(dispenser2, inventory2List.get(i));
						break;
					default:
						// Type does not exist
						break;
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Combine the product objects
		allProducts.addAll(mainDisp.getProducts());
		allProducts.addAll(dispenser2.getProducts());
		
		// Recursive sort by name and qty
		recursiveSortName(allProducts, 0, allProducts.size());
		recursiveSortQty(allProducts, 0, allProducts.size());
		
		// Recursive search for product by specific name
		String prodSearchName = "Reeses";
		
		recursiveSearchByName(prodSearchName, allProducts, 0, allProducts.size());
		System.out.println("-------------- Name Search Results --------------");
		
		if (nameSearchResults.size() > 0) {
			System.out.println("Searched for " + prodSearchName + "...");
			
			for (Product prod : nameSearchResults) {
				System.out.println("Dispenser ID: " + prod.getDispenserID() + ", Qty: " + prod.getQuantity());
			}
		} else {
			System.out.println("Product name " + prodSearchName + " was not found");
		}

		System.out.println("-------------- Sorted Products --------------");
		for (Product prod : allProducts) {
			System.out.println(prod.getName() + " : " + prod.getQuantity());
		}
		
	}
	
	/**
	 * addCandy()
	 * 
	 * @param dispenser
	 * @param prod
	 */
	public void addCandy(Dispenser dispenser, String[] prod) {
		dispenser.addProduct(new Candy(
			prod[2], 
			Double.parseDouble(prod[3]), 
			Integer.parseInt(prod[4]), 
			prod[5], 
			Integer.parseInt(prod[1]), 
			prod[6],
			Integer.parseInt(prod[13]),
			Integer.parseInt(prod[7]), 
			Double.parseDouble(prod[8])));
	}
	
	/**
	 * addChips()
	 * 
	 * @param dispenser
	 * @param prod
	 */
	public void addChips(Dispenser dispenser, String[] prod) {
		dispenser.addProduct(new Chips(
			prod[2], 
			Double.parseDouble(prod[3]),
			Integer.parseInt(prod[4]),
			prod[5], 
			Integer.parseInt(prod[1]),
			prod[6],
			Integer.parseInt(prod[13]),
			Integer.parseInt(prod[7]),
			Boolean.parseBoolean(prod[10])));
	}
	
	/**
	 * addDrink()
	 * 
	 * @param dispenser
	 * @param prod
	 */
	public void addDrink(Dispenser dispenser, String[] prod) {
		dispenser.addProduct(new Drink(
			prod[2], 
			Double.parseDouble(prod[3]),
			Integer.parseInt(prod[4]),
			prod[5], 
			Integer.parseInt(prod[1]),
			prod[6],
			Integer.parseInt(prod[13]),
			Integer.parseInt(prod[9])));
	}
	
	/**
	 * addGum()
	 * 
	 * @param dispenser
	 * @param prod
	 */
	public void addGum(Dispenser dispenser, String[] prod) {
		dispenser.addProduct(new Gum(
			prod[2], 
			Double.parseDouble(prod[3]),
			Integer.parseInt(prod[4]),
			prod[5], 
			Integer.parseInt(prod[1]),
			prod[6],
			Integer.parseInt(prod[13]),
			Integer.parseInt(prod[7]),
			prod[11],
			Boolean.parseBoolean(prod[12])));
	}
	
	
	/**
	 * recursiveSortName()
	 * 
	 * Sort by name recursively
	 * 
	 * @param allProducts
	 * @param beginningIndex
	 * @param n
	 */
	public void recursiveSortName(ArrayList<Product> allProducts, int beginningIndex, int n) {
		if (beginningIndex >= n)
			return;
				
		// Compare names
		for (int i = 0; i < allProducts.size(); i++) {
			// Make sure we don't compare the product to itself
			if (i != beginningIndex) {
				Product tempProd = allProducts.get(i);
				
				int result = allProducts.get(beginningIndex).compareName( tempProd );
				
				if (result == 1) {
					allProducts.set(i, allProducts.get(beginningIndex));
					allProducts.set(beginningIndex, tempProd);
				}
			}
		}

		recursiveSortName(allProducts, beginningIndex + 1, n);
	}
	
	
	/**
	 * recursiveSortQty()
	 * 
	 * Sort by Qty recursively. MUST BE DONE AFTER recursiveSortName()
	 * 
	 * @param allProducts
	 * @param beginningIndex
	 * @param n
	 */
	public void recursiveSortQty(ArrayList<Product> allProducts, int beginningIndex, int n) {
		if (beginningIndex >= n)
			return;
		
		// Compare qty
		for (int i = 0; i < allProducts.size(); i++) {
			// Make sure we don't compare the product to itself
			if (i != beginningIndex) {
				Product tempProd = allProducts.get(i);
				
				if (allProducts.get(beginningIndex).getProductId() == tempProd.getProductId() ) {
										
					int result = allProducts.get(beginningIndex).compareQty( tempProd );
					
					if (result == 1) {
						// Make sure index is higher, otherwise don't swap
						if (beginningIndex < i) {
							allProducts.set(i, allProducts.get(beginningIndex));
							allProducts.set(beginningIndex, tempProd);
						}
					}
				}
			}
		}
		
		recursiveSortQty(allProducts, beginningIndex + 1, n);
	}
	
	
	/**
	 * recursiveSearchByName()
	 * 
	 * Recursively searches products ArrayList for a specific name
	 * 
	 * @param name
	 * @param allProducts
	 * @param beginningIndex
	 * @param n
	 */
	public void recursiveSearchByName(String name, ArrayList<Product> allProducts, int beginningIndex, int n) {
		if (beginningIndex >= n)
			return;
		
		if (allProducts.get(beginningIndex).getName().equals( name )) {
			nameSearchResults.add(allProducts.get(beginningIndex));
		}
		
		// Write each recursive flow to the call stack file
		try {
			writeToCallStackFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		recursiveSearchByName(name, allProducts, beginningIndex + 1, n);
	}
	
	
	/**
	 * writeToCallStackFile()
	 * 
	 * Writes the current process of the call stack to file
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeToCallStackFile() throws FileNotFoundException {
		File file = new File("callStack.txt");
		
		PrintWriter writer = new PrintWriter(file);
		
		if (file.exists()) {
			writer.print("");
		}
		
		for (StackTraceElement line : Thread.currentThread().getStackTrace()) {
			writer.println(line.toString());
		}
		
		writer.close();
		
	}
}

