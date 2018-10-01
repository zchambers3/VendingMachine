/* Zack Chambers
 * The following work is done by Zack Chambers and no one else can take credit for it.
 */

package Final_Project;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

import com.opencsv.CSVReader;

import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.VLineTo;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public final class ProcessCustomerQueue {
	
	protected Queue<String> theQueue = new java.util.LinkedList<>();
	protected Dispenser disp = new Dispenser();
	
	public ProcessCustomerQueue(Dispenser disp) {
		this.disp = disp;
		
		try {
			readCustomerQueue();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readCustomerQueue() throws IOException {
		// Attempt to read customer que from file
		try {
			
			CSVReader custQueue = new CSVReader(new FileReader("purchaseQue.csv"));
			
			// Save entries into a list
			List<String[]> custQueueList = custQueue.readAll();
			
			System.out.println("\n-------------- Start Customer Queing --------------");
			
			// Go through the list
			for (int i = 0; i < custQueueList.size(); i++) {
				
				String name = custQueueList.get(i)[0];
				String item = custQueueList.get(i)[1];
				
				// Add to the queue
				System.out.println(in(name + "," + item));
				
			}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Show initial length of queue
		System.out.println(length() + "\n");
		
		// Run the transactions at a specific interval
		timedTransactions();
		
	}
	
	
	public void timedTransactions() {

		if (theQueue.size() > 0) {
			
//Create new Stage to show queue

			Stage primaryStage = new Stage();
			HBox hbox = new HBox();
			hbox.setStyle("-fx-background-color: #ffffff;");
			
			ImageView machineImage = new ImageView();
			machineImage = new ImageView(new Image("https://www.avantius.us/wp-content/uploads/2017/06/ABC45-OUTDOOR-INDOOR-45-Select-Food-and-Beverage.jpg"));
			machineImage.setFitHeight(80);
			machineImage.setFitWidth(60);
			
			hbox.getChildren().add(machineImage);
			
			ImageView customer = new ImageView();
			customer = new ImageView(new Image("http://images.clipartpanda.com/person-standing-sideways-clipart-clip-art-illustration-of-design-mascot-standing-sideways-royalty-free.jpg"));
			customer.setFitHeight(80);
			customer.setFitWidth(40);
			
			// Show people stanind in queue based off of queue.size
			for(int i = 0; i < theQueue.size(); i++ ){
				customer = new ImageView(new Image("http://images.clipartpanda.com/person-standing-sideways-clipart-clip-art-illustration-of-design-mascot-standing-sideways-royalty-free.jpg"));
				customer.setFitHeight(80);
				customer.setFitWidth(40);
				hbox.getChildren().add(customer);
			}
			
		    hbox.setPadding(new Insets(15, 12, 15, 12));
		    hbox.setSpacing(10);
	        StackPane root = new StackPane();
	        root.getChildren().add(hbox);

	        Scene scene = new Scene(root, 600, 150);

	        // Event to run when the queued scene starts
	        primaryStage.addEventHandler(WindowEvent.WINDOW_SHOWN, new EventHandler<WindowEvent>()
	        {
	            @Override
	            public void handle(WindowEvent window)
	            {
	            	System.out.print(out() + "\n");
					System.out.println(length() + "\n");
					Timeline timeline = new Timeline(new KeyFrame(
					        Duration.millis(2500),
					        ae -> timedTransactions()));
										
					String newStr = theQueue.peek().split("[\\])},]")[1];
					
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
			        switch (newStr) {
			            case "Doritos": prodImage = new ImageView(new Image("http://getbetterwellness.com/wp-content/uploads/2013/09/doritos.jpg"));
							          	prodImage.setFitHeight(80);
							        	prodImage.setFitWidth(60);
							        	break;
			            case "Cheetos": prodImage = new ImageView(new Image("https://www.fritolay.com/images/default-source/blue-bag-image/cheetos-crunchy-cheese.png?sfvrsn=5951573a_2"));
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
					PathTransition pt = new PathTransition(Duration.millis(500), path, prodImage);
					pt.setAutoReverse(false);
			        pt.play();
			      		        
					timeline.play();
	            }
	        });
	        
	        primaryStage.setTitle("Queue Transactions");
	        PauseTransition delay = new PauseTransition(Duration.seconds(2.5));
	        delay.setOnFinished( event -> primaryStage.close() );
	        delay.play();
	        primaryStage.setScene(scene);
	        primaryStage.show();
	       
	        	
//			ADDED
			
//			// Process transaction for first person in the queue
//			startTransaction(theQueue.peek());
//			
//			// Stall the process every 3.5 seconds
//			try {
//				System.out.print(out() + "\n");
//				System.out.println(length() + "\n");
//				
//	            Thread.sleep(100);
//	        } catch (InterruptedException e) {
//	            e.printStackTrace();
//	        }
		} else{
			System.out.println(isEmpty());
		}
		
	}
	
	
	/**
	 * doTransaction()
	 * 
	 * Processes the transaction
	 * 
	 * @param toDo
	 */
	public void startTransaction(String toDo) {
		
		String[] toDoArray = toDo.split(",");
		
		String personsName = toDoArray[0];
		String itemToPurchase = toDoArray[1];
		
		boolean itemFound = false;
		boolean itemAvailable = false;
		Product foundItem = null;
		
		// Get products from the dispenser
		ArrayList<Product> mainDispProds = disp.getProducts();
		
		for (int i = 0; i < mainDispProds.size(); i++) {
			
			// Compare product name
			if (mainDispProds.get(i).getName().equals(itemToPurchase)) {
				
				itemFound = true;
				
				// Check availability
				if (mainDispProds.get(i).getQuantity() >= 1) {
					
					itemAvailable = true;
					foundItem = mainDispProds.get(i);
					break;
					
				}
				
			}
			
		}
		
		if (itemFound) {
			if (itemAvailable) {
				
				// Process the transaction
				processTransaction(foundItem);
				
			} else {
				// Get random item from the dispenser
				Product prod = getRandomProduct(mainDispProds);
				
				// Process the purchase
				processTransaction(prod);
				
				System.out.println(personsName + " wanted a " + itemToPurchase + 
						", but there are none available. They purchased a " + prod.getName() + " instead.");
			}
		} else {
			// Get random item from the dispenser
			Product prod = getRandomProduct(mainDispProds);
			
			// Process the purchase
			processTransaction(prod);
			
			System.out.println(personsName + " wanted a " + itemToPurchase + 
					", but was not found in the dispenser. They purchased a " + prod.getName() + " instead.");
		}
		
	}
	
	
	public void processTransaction(Product prod) {
		
		// update quantity
		prod.setQuantity( prod.getQuantity() - 1 );
		
	}
	
	
	public Product getRandomProduct(ArrayList<Product> mainDispProds) {
		// Get random item
		int randNum = ThreadLocalRandom.current().nextInt(0, mainDispProds.size() + 1);
		
		return mainDispProds.get(randNum);
	}
	
	
	// Below are the actions for adding and removing from the queue, while
	// also returning statements to reflect these actions.
	public String first() {
		return "First Item in Queue: " + theQueue.peek();
	}
	
	public String length() {
		return "Length of Queue: " + theQueue.size();
	}
	
	public String in(String itemToAdd) {
		theQueue.offer(itemToAdd);
		
		return "Adding to Queue: " + itemToAdd;
	}
	
	public String out() {
		return "Remove from Queue: " + theQueue.remove();
	}
	
	public String isEmpty() {
		return "Queue is empty";
	}

}

