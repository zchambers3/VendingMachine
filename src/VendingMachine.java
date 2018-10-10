/* Zack Chambers
 * The following work is done by Zack Chambers and no one else can take credit for it.
 */

package Final_Project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos; 
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class VendingMachine extends Application {
	BorderPane overallMainPane = new BorderPane();
	GridPane mainCategoryPane = new GridPane();
	GridPane basketPane = new GridPane();
	ListView<String> basketList = new ListView<>();
	Label basketPrice = new Label("Total:");
	Dispenser mainDisp = new Dispenser();
	Dispenser dispenser2 = new Dispenser();
	InventoryManagement invMngmnt = new InventoryManagement();
	Global_InventoryManagement global_invMngmnt = new Global_InventoryManagement();

	String borderedItems = "-fx-border-color: gray;\n"
            + "-fx-border-insets: 5;\n"
            + "-fx-border-width: 1;\n"
            + "-fx-border-style: solid;\n";
	Font fontBold = Font.font("Sans-Serif",FontWeight.BOLD, FontPosture.REGULAR, 16);
	ArrayList<Product> productsInSelectedCategory = new ArrayList();
	
	@Override
	public void start(Stage primaryStage) {
		
		// Create left side component
		VBox leftSide = new VBox(15);
		leftSide.setPadding(new Insets(15,15,15,15));
		leftSide.setPrefWidth(500);
		leftSide.setPrefHeight(600);
		leftSide.setStyle("-fx-background-color: #dddddd");
		
		// Create right side component
		VBox rightSide = new VBox(15);
		rightSide.setPadding(new Insets(15,15,15,15));
		rightSide.setPrefWidth(260);
		rightSide.setPrefHeight(600);
		
		
		// Add initial products to the Dispenser
		// For identification purposes, Product ID's should have a specific starting number:
		// 	Candy:	1		Chips:	2		Drink:	3		Gum:	4
		
		try {
			global_invMngmnt.csvInventoryImport(mainDisp, dispenser2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Create GridPane
		createCategoryPane();
		
		// setup main pane components
		overallMainPane.setLeft(leftSide);
		overallMainPane.setRight(rightSide);
		leftSide.getChildren().add(mainCategoryPane);
		rightSide.getChildren().add(new BuildBasket());
		
		// Create a scene and place it in the stage
		Scene scene = new Scene(overallMainPane);
		primaryStage.setTitle("Vending Machine"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}
	
	
	/**
	 * createCategoryPane()
	 * 
	 */
	public void createCategoryPane() {
		// Clear the grid
		mainCategoryPane.getChildren().clear();
		
		mainCategoryPane.setAlignment(Pos.CENTER);
		mainCategoryPane.setPadding(new Insets(10, 10, 10, 10));
		mainCategoryPane.setHgap(8);
		mainCategoryPane.setVgap(8);
		
		String categories[] = {"Candy", "Chips", "Drink", "Gum"};
		
		// Setup rows and columns to define the layout
		int row = 0;
		int col = 0;
		
		// Add panes to the grid
		for (int i = 0; i < categories.length; i++) {
			mainCategoryPane.add(new buildCategory(categories[i]), col, row);
			
			col++;
			
			if (col == 2) {	// Maximum columns will be 2. Once this is met, reset column and increment to next row
				col = 0;
				row++;
			}
		}
		
		Button boss = new Button("Control Panel");
		boss.setOnAction(value -> {
			Stage stage = new Stage();
			stage.setTitle("Boss Password");
			stage.setResizable(false);
			
			GridPane pane3 = new GridPane();
			pane3.setAlignment(Pos.CENTER);
			pane3.setPadding(new Insets(10, 10, 10, 10));
			pane3.setHgap(8);
			pane3.setVgap(8);
			
			Label enterPword = new Label("Please enter the password:");
			TextField password = new TextField();
			password.setMaxHeight(15);
			password.setMaxWidth(45);
			Button submit = new Button("Submit");
			
			submit.setOnAction(e -> {
				if(checkPassword(password.getText())){
					buildBossInterface();
					stage.hide();
				}else{
					password.clear();
					ErrorManagement.sendAlert(AlertType.ERROR, "Invalid Password", "Invalid Password", "The password entered is invalid. Please try again.");
				}
			});
			
			pane3.add(enterPword, 0, 0);
			pane3.add(password, 0, 1);
			pane3.add(submit, 1, 1);
			
			Scene scene = new Scene(pane3, 300, 80);
			stage.setScene(scene);
			stage.show();

		});
		
		Button restock = new Button("Restock");
		restock.setOnAction(value -> {
			Restock restock1 = new Restock();
			Stage restockStage = new Stage();
			restock1.start(restockStage);
		});
		
		Button queuedTransactions = new Button("Queued Transactions");
		queuedTransactions.setOnAction(value -> {
			// Test Queue
			ProcessCustomerQueue theque = new ProcessCustomerQueue(mainDisp);
		});
		
		mainCategoryPane.add(boss, 0, 3);
		mainCategoryPane.add(restock, 1, 3);
		mainCategoryPane.add(queuedTransactions, 0, 4);
	}
	
	/**
	 * checkPassword()
	 * 
	 * Checks the BOSS password for validity
	 * 
	 * @param string
	 * @return
	 */
	private boolean checkPassword(String string){
		if(string.equals("gcu"))
			return true;
		else
			return false;
	}
	
	/**
	 * buildCategory()
	 */
	class buildCategory extends GridPane {
		buildCategory(String catName) {
			// Setup internal GridPane
			GridPane pane2 = new GridPane();
			pane2.setAlignment(Pos.CENTER);
			pane2.setPadding(new Insets(10, 10, 10, 10));
			pane2.setHgap(8);
			pane2.setVgap(8);
			
			// Add to parent GridPane
			getChildren().add(pane2);
			
			// Create parts
			Button catButton = new Button("Buy " + catName);		
			catButton.setOnAction(value ->  {
				viewCategoryItems(catName);
			});
			
			Image catImage = new Image(new File("images/" + catName.toLowerCase() + ".jpg").toURI().toString());
			
			// Add items to the pane
			pane2.add(new ImageView(catImage), 0, 0);
			pane2.add(catButton, 0, 1);
			pane2.setHalignment(catButton, HPos.CENTER);
		}
	}
	
	/**
	 * viewCategoryItems()
	 * 
	 * @param catName
	 */
	public void viewCategoryItems(String catName) {
		// Clear the grid
		mainCategoryPane.getChildren().clear();
		
		// Get all items in this category
		ArrayList<Product> allProds = mainDisp.getProducts();
		
		// Setup ArrayList to hold products in this category
		ArrayList<Product> prodsInThisCat = new ArrayList();
		
		for (int i = 0; i < allProds.size(); i++) {
			int currentProdId = allProds.get(i).getProductId();
			int lookingForID = 0;
			
			switch (catName) {
				case "Candy":
					lookingForID = 1;
					break;
				case "Chips":
					lookingForID = 2;
					break;
				case "Drink":
					lookingForID = 3;
					break;
				case "Gum":
					lookingForID = 4;
					break;
			}
			
			// Get the first number in the product id
			while (currentProdId >= 10) {
				currentProdId /= 10;
			}
			
			// If the first number matches the ID, 
			// then add it to new ArrayList
			if (currentProdId == lookingForID) {
				prodsInThisCat.add(allProds.get(i));
			}
		}
		
		// Save products to accessible variable
		productsInSelectedCategory = prodsInThisCat;
		
		// Build the pane and list out the items in this category
		buildCategoryItems();
	}
	
	
	/**
	 * buildCategoryItems()
	 * 
	 * @param prodsInThisCat
	 */
	public void buildCategoryItems() {
		// Clear the grid
		mainCategoryPane.getChildren().clear();
		
		mainCategoryPane.setAlignment(Pos.CENTER);
		mainCategoryPane.setPadding(new Insets(10, 10, 10, 10));
		mainCategoryPane.setHgap(8);
		mainCategoryPane.setVgap(8);
		
		Button backBtn = new Button("Back To Categories");		
		backBtn.setOnAction(value ->  {
			createCategoryPane();
		});
				
		// Calculate where the back button should be placed based upon number of rows required
		mainCategoryPane.add(backBtn, 0, (int) ( Math.ceil( (productsInSelectedCategory.size() / 2) + 1) ), 2, 1);
		mainCategoryPane.setHalignment(backBtn, HPos.CENTER);
		
		// Setup rows and columns to define the layout
		int row = 0;
		int col = 0;
		
		for (int i = 0; i < productsInSelectedCategory.size(); i++) {
			mainCategoryPane.add(new buildEachCategoryItem((Product) productsInSelectedCategory.get(i)), col, row);
			
			col++;
			
			if (col == 2) {	// Maximum columns will be 2. Once this is met, reset column and increment to next row
				col = 0;
				row++;
			}
		}
	}
	
	
	/**
	 * buildCategoryItems()
	 */
	class buildEachCategoryItem extends GridPane {
		buildEachCategoryItem(Product prod) {
			// Setup internal GridPane
			GridPane pane2 = new GridPane();
			pane2.setAlignment(Pos.CENTER);
			pane2.setPadding(new Insets(10, 10, 10, 10));
			pane2.setHgap(8);
			pane2.setVgap(8);
			
			// Add to parent GridPane
			getChildren().add(pane2);
			
			// Create parts
			Label name = new Label(prod.getName());
			Label price = new Label(String.valueOf(prod.getPrice()));
			Label desc = new Label(prod.getDescription());
			Label qty = new Label("Qty Available: " + String.valueOf(prod.getTemporaryQuantity()));
			Button addToBasketBtn = new Button("Add To Basket");
			
			addToBasketBtn.setOnAction(value ->  {
				// Attempt to add to the basket
				boolean basketAdd = invMngmnt.addToBasket(prod, 1);
				
				basketPrice.setText("Total: $" + invMngmnt.getBasketTotal());
				
				if (basketAdd) {
					// Update Basket
					basketList.setItems( invMngmnt.getBasketObsList() );
					
				    // Re-do category items panel
				    buildCategoryItems();
				}				
			});
			
			// Enable wrapping of label for description
			desc.setWrapText( true );
			
			// Add items to the pane
			pane2.add(name, 0, 0);
			pane2.add(price, 1, 0);
			pane2.add(desc, 0, 1, 2, 1);
			pane2.add(qty, 0, 2, 2, 1);
			pane2.add(addToBasketBtn, 0, 3, 2, 1);
			
			pane2.setHalignment(price, HPos.RIGHT);
			pane2.setHalignment(addToBasketBtn, HPos.CENTER);
			name.setFont(fontBold);
			pane2.setStyle(borderedItems);
		}
	}
	
	
	class BuildBasket extends GridPane {
		BuildBasket() {
			// Setup internal GridPane
			basketPane.setPadding(new Insets(10, 10, 10, 10));
			basketPane.setHgap(8);
			basketPane.setVgap(8);
			
			// Add to parent GridPane
			getChildren().add(basketPane);
			
			// Create parts
			Label basketTitle = new Label("Basket");
			Button removeItemBtn = new Button("Remove");		
			removeItemBtn.setOnAction(value ->  {
				// Make sure selection was made
				if (basketList.getSelectionModel().getSelectedIndex() == -1) {
					// Do nothing
				} else {
					String[] itemSelectedArray = basketList.getSelectionModel().getSelectedItem().split(" - ");
					int itemIndex = basketList.getSelectionModel().getSelectedIndex();
					
					// Remove from the basket
					invMngmnt.removeFromBasket(mainDisp, Integer.parseInt(itemSelectedArray[1]), itemIndex);
					
					basketPrice.setText("Total: $" + invMngmnt.getBasketTotal());
					
					// Update Basket
					basketList.setItems( invMngmnt.getBasketObsList() );
					
				    // Re-do category items panel
				    buildCategoryItems();
				}
			});
			
			// Create the ListView for the basket
			basketList.setOrientation(Orientation.VERTICAL);
			basketList.setPrefSize(220, 380);

			// Add items to the pane
			basketPane.add(basketTitle, 0, 0);
			basketPane.add(basketList, 0, 1);
			basketPane.add(basketPrice, 0, 2);
			basketPane.add(removeItemBtn, 0, 3);
			basketPane.setHalignment(basketTitle, HPos.CENTER);
			basketPane.setHalignment(removeItemBtn, HPos.CENTER);
		}
	}
	
	public void buildBossInterface() {
		// Clear the grid
		mainCategoryPane.getChildren().clear();
		
		ScrollPane sp = new ScrollPane();
		overallMainPane.setLeft(sp);
		sp.setContent(mainCategoryPane);
		sp.setPrefWidth(500);
		sp.setPrefHeight(600);
		
		mainCategoryPane.setAlignment(Pos.CENTER);
		mainCategoryPane.setPadding(new Insets(10, 10, 10, 10));
		mainCategoryPane.setHgap(8);
		mainCategoryPane.setVgap(8);
		
		Button backBtn = new Button("Back To Categories");		
		backBtn.setOnAction(value ->  {
			createCategoryPane();
		});
				
		// Calculate where the back button should be placed based upon number of rows required
		mainCategoryPane.add(backBtn, 0, (int) ( Math.ceil( (mainDisp.getProducts().size() / 2) + 1) ), 2, 1);
		mainCategoryPane.setHalignment(backBtn, HPos.CENTER);
		
		// Setup rows and columns to define the layout
		int row = 0;
		int col = 0;
		
		for (int i = 0; i < mainDisp.getProducts().size(); i++) {
			mainCategoryPane.add(new buildBossItem((Product) mainDisp.getProducts().get(i)), col, row);
			
			col++;
			
			if (col == 2) {	// Maximum columns will be 2. Once this is met, reset column and increment to next row
				col = 0;
				row++;
			}
		}
	}
	
	
	/**
	 * buildBossItem()
	 */
	class buildBossItem extends GridPane {
		buildBossItem(Product prod) {
			// Setup internal GridPane
			GridPane pane2 = new GridPane();
			pane2.setAlignment(Pos.CENTER);
			pane2.setPadding(new Insets(10, 10, 10, 10));
			pane2.setHgap(8);
			pane2.setVgap(8);
			
			// Add to parent GridPane
			getChildren().add(pane2);
			
			// Create parts
			Label name = new Label(prod.getName());
			Label price = new Label(String.valueOf(prod.getPrice()));
			Label desc = new Label(prod.getDescription());
			Label qty = new Label("Qty Available: " + String.valueOf(prod.getTemporaryQuantity()));
			TextField textField = new TextField();
			textField.setMaxHeight(15);
			textField.setMaxWidth(30);
			Button updateQuantity = new Button("Update Quantity");
			
			updateQuantity.setOnAction(value -> {
				prod.setQuantity(Integer.parseInt(textField.getText()));
				buildBossInterface();
			});
			
			// Enable wrapping of label for description
			desc.setWrapText( true );
			
			// Add items to the pane
			pane2.add(name, 0, 0);
			pane2.add(price, 1, 0);
			pane2.add(desc, 0, 1, 2, 1);
			pane2.add(qty, 0, 2, 2, 1);
			pane2.add(textField, 0, 3, 2, 1);
			pane2.add(updateQuantity, 1, 3, 2, 1);
			
			pane2.setHalignment(price, HPos.RIGHT);
			name.setFont(fontBold);
			pane2.setStyle(borderedItems);
		}
	}
	
	
	
	
//	public static void main(String[] args) {
//		Dispenser testDisp = new Dispenser();
//		
//		//add 5 products to the Dispenser
//		testDisp.addProduct(new Chips("Doritos", 1.99, 1, "A2", 1234, 135, false));
//		testDisp.addProduct(new Chips("Cheetos", 2.49, 5, "B3", 1155, 155, false));
//		testDisp.addProduct(new Candy("Reeses", 2.09, 3, "C1", 2435, 240, 1));
//		testDisp.addProduct(new Gum("Trident", 0.89, 5, "D4", 9999, 5, "Medium", true));
//		testDisp.addProduct(new Drink("Coke", 1.49, 4, "A1", 1111, 12));
//		testDisp.addProduct(new Drink("Tea", 1.25, 3, "A3", 1133, 12));
//	
//		// Create new Drinks to Compare
//		Drink tea = new Drink("Tea", 1.25, 3, "A3", 1133, 12);
//		Drink coke = new Drink("Coke", 1.49, 4, "A1", 1111, 12);
//		coke.compareTo(tea);
//				
//	    //display Products in a String
//		System.out.println(testDisp.getProductsToString() + "\n" +
//				"Total Product Count: " + testDisp.getTotalProductCount());
//		
//		// Result of Drinks Compare
//		int result = coke.compareTo(tea);
//		if (result < 0){
//			System.out.print("Coke is less than tea");
//		} else if (result == 0){
//			System.out.print("These are the same");
//		}else {
//			System.out.print("Coke is greater than tea");
//		}
//		
//	}
	
	public static void main(String[] args) {
        Application.launch(args);

   }
	
}

