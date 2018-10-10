/* Zack Chambers
 * The following work is done by Zack Chambers and no one else can take credit for it.
 */

package Final_Project;

import java.util.ArrayList;

import javafx.animation.PathTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.VLineTo;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class InventoryManagement extends Product {
	
	protected ObservableList<String> basketObservableList;
	protected ArrayList<String> basketListItems = new ArrayList<String>();
	
	InventoryManagement() {
		
	}
	
	/**
	 * sellItem()
	 * 
	 * Reduce the number of items for a specific product based on a sale
	 * 
	 * @param prod
	 * @param qty
	 */
	public boolean addToBasket(Product prod, int qty) {
		String errorMsg = "";
		
		// Make sure enough products are available
		if (prod.getTemporaryQuantity() <= 0) {
			if (prod.getTemporaryQuantity() == 0)
				errorMsg = "Sorry, there are no " + prod.getName() + " left. Please try another item.";
			else
				errorMsg = "Sorry, only " + prod.getQuantity() + " left of " + prod.getName() + ". Please try another item.";
				
			sendAlert(AlertType.ERROR, "Item Unavailable", "Item Unavailable", errorMsg);
			
			return false;
		} else {
			prod.setTemporaryQuantity(prod.getTemporaryQuantity() - qty);
			
			// Add to the basket
			basketListItems.add( prod.getName() + " - " + prod.getProductId() + " - $" + prod.getPrice() );
			updateBasketObsList();
			
			// Create animation for adding the item to the basket
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Add to Basket");
			stage.setResizable(false);
			
			Group root = new Group();
			
			// Get image for basket
			ImageView basket = new ImageView(new Image("http://simpleicon.com/wp-content/uploads/basket.png"));
			basket.relocate(10, 300);
			basket.setFitHeight(90);
			basket.setFitWidth(100);
			
			// Create image for product
			ImageView prodImage = new ImageView();
			
			// Set image size and url based of prod.getname()
	        switch (prod.getName()) {
	            case "Doritos": prodImage = new ImageView(new Image("http://getbetterwellness.com/wp-content/uploads/2013/09/doritos.jpg"));
					          	prodImage.setFitHeight(80);
					        	prodImage.setFitWidth(60);
					        	break;
	            case "Cheetos": prodImage = new ImageView(new Image("http://www.scoopnheap.com/wp-content/uploads/2013/02/Cheetos.png"));
					          	prodImage.setFitHeight(80);
					        	prodImage.setFitWidth(60);
					        	break;
	            case "Corn Chips": prodImage = new ImageView(new Image("https://images.jet.com/md5/e8749580772b358818dd6544f8828c43"));
					          	prodImage.setFitHeight(80);
					        	prodImage.setFitWidth(80);
					        	break;
	            case "Flutes": prodImage = new ImageView(new Image("http://img1.21food.com/img/cj/2014/10/9/1412788858789310.jpg"));
					          	prodImage.setFitHeight(80);
					        	prodImage.setFitWidth(80);
					        	break;
	            case "Reeses":  prodImage = new ImageView(new Image("https://upload.wikimedia.org/wikipedia/en/9/97/Reese's-PB-Cups-Wrapper-Small.png"));
					            prodImage.setRotate(90);
					          	prodImage.setFitHeight(40);
					        	prodImage.setFitWidth(80);
					        	break;
	            case "Trident": prodImage = new ImageView(new Image("http://badwordslab.com/wp-content/uploads/2014/08/Trident-460x295.png"));
					            prodImage.setFitHeight(40);
					        	prodImage.setFitWidth(80);
					        	break;
	            case "Hubba Bubba": prodImage = new ImageView(new Image("http://www.sanza.co.uk/pics/8259.jpg"));
					            prodImage.setFitHeight(40);
					        	prodImage.setFitWidth(80);
					        	break;
	            case "Big League Chew": prodImage = new ImageView(new Image("https://www.studentnewsdaily.com/wp-content/uploads/2015/05/Big-League-Chew.png"));
					            prodImage.setFitHeight(40);
					        	prodImage.setFitWidth(80);
					        	break;
	            case "Coke":  prodImage = new ImageView(new Image("http://www.magictricks.com/assets/images/trickspix/airbornecokecan2.jpg"));
					          prodImage.setFitHeight(60);
					          prodImage.setFitWidth(60);
					          break;
	            case "Pepsi":  prodImage = new ImageView(new Image("http://i.bnet.com/blogs/new-pepsi-can-logo-bnet-april-2011.jpg"));
					          prodImage.setFitHeight(80);
					          prodImage.setFitWidth(60);
					          break;
	            case "Sprite":  prodImage = new ImageView(new Image("http://www.caffeineinformer.com/wp-content/caffeine/sprite.jpg"));
					          prodImage.setFitHeight(80);
					          prodImage.setFitWidth(40);
					          break;				          
	            case "Root Beer":  prodImage = new ImageView(new Image("http://www.caffeineinformer.com/wp-content/caffeine/aw-root-beer.jpg"));
					          prodImage.setFitHeight(80);
					          prodImage.setFitWidth(40);
					          break;
	            case "Green Tea":  prodImage = new ImageView(new Image("https://s-media-cache-ak0.pinimg.com/236x/76/55/d9/7655d918c9d08c66beecc8e47146da2a.jpg"));
				             prodImage.setFitHeight(90);
					         prodImage.setFitWidth(40);
					         break;
	            case "Snickers":  prodImage = new ImageView(new Image("https://upload.wikimedia.org/wikipedia/en/thumb/3/3c/Snickers_wrapped.png/240px-Snickers_wrapped.png"));
	            				prodImage.setFitHeight(40);
					        	prodImage.setFitWidth(80);
					        	break;
	            case "Kit-Kat":  prodImage = new ImageView(new Image("https://upload.wikimedia.org/wikipedia/en/5/5d/Kit-Kat-Wrapper-Small.jpg"));
								prodImage.setFitHeight(40);
					        	prodImage.setFitWidth(80);
					        	break;
	            case "Caramello":  prodImage = new ImageView(new Image("http://cdn.foodbeast.com/content/uploads/2016/06/caramello-chocolate-bar.jpg"));
								prodImage.setFitHeight(40);
					        	prodImage.setFitWidth(80);
		         break;
	        }
		    
            prodImage.relocate(0, 0);
			root.getChildren().addAll(prodImage, basket);
			
			// Set animation path
			Path path = new Path();
			path.getElements().addAll(new MoveTo(60, 50), new VLineTo(340));
			path.setFill(null);
			path.setStroke(Color.TRANSPARENT);
			path.setStrokeWidth(2);
			root.getChildren().add(path);
			
			// Set and show new scene
			Scene scene = new Scene(root, 100, 400);
			stage.setScene(scene);
			stage.show();
			
			//Animate product going into basket
			PathTransition pt = new PathTransition(Duration.millis(1000), path, prodImage);
            pt.play();
			pt.setAutoReverse(false);
			pt.play();
			
			/////
			
			return true;
		}
	}
	
	
	/**
	 * removeFromBasket()
	 * 
	 * Performs tasks of removing items from the users basket
	 * 
	 * @param mainDisp
	 * @param prodId
	 * @param itemInList
	 */
	public void removeFromBasket(Dispenser mainDisp, int prodId, int itemInList) {
		// Remove from Observable List
		basketObservableList.remove(itemInList);
		
		// Remove from ArrayList
		for (int j = 0; j < basketListItems.size(); j++) {
			String[] itemToRemove = basketListItems.get(j).split(" - ");
			
			if (Integer.parseInt(itemToRemove[1]) == prodId) {
				basketListItems.remove(j);
			}
		}
		
		// Remove from product temporary count
		for (int i = 0; i < mainDisp.getProducts().size(); i++) {
			Product theProd = mainDisp.getProducts().get(i);
			
			// If selected prodId matches, update the quantities
			if (theProd.getProductId() == prodId) {
				theProd.setTemporaryQuantity( theProd.getTemporaryQuantity() + 1 );
			}
		}
	}
	
	
	/**
	 * getBasketTotal()
	 * 
	 * Returns Double value to show the total price of all items in basket
	 * 
	 * @return
	 */
	public double getBasketTotal() {
		double tempTotal = 0.00;
		
		for (int k = 0; k < basketListItems.size(); k++) {
			String[] itemToRemove = basketListItems.get(k).split(" - ");
			
			tempTotal += Float.parseFloat(itemToRemove[2].replace("$", ""));
		}
		
		return Math.round(tempTotal * 100D) / 100D;
	}
	
	
	/**
	 * updateBasketObsList()
	 * 
	 * Updates the Observable list of items in the basket
	 */
	private void updateBasketObsList() {		
		String[] tempListItems = new String[basketListItems.size()];
		
		for (int i = 0; i < basketListItems.size(); i++) {
			tempListItems[i] = basketListItems.get(i);
		}
		
		basketObservableList = FXCollections.observableArrayList(tempListItems);
	}
	
	
	// Getters
	public ObservableList<String> getBasketObsList() {
		return basketObservableList;
	}
}

