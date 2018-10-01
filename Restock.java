/* Zack Chambers
 * The following work is done by Zack Chambers and no one else can take credit for it.
 */

package Final_Project;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Restock extends Application {

 Stage menu = new Stage(); // Create new Stage
 boolean MoreMachines = true;
 int machine = 1; // starting machine

 @Override // Start the application
 public void start(Stage primaryStage) {
  menu.setTitle("Manage Multiple Machines");
  int vendingMachines = checkMachineInventory();
  showMenu(vendingMachines); // Show main menu
 }

 // Show Main Menu
 public void showMenu(int vendingMachines) {

  BorderPane pane = new BorderPane(); // Create border pane

  // Create Label for top section
  Label topLabel = new Label("Check Inventory on which Machine");
  topLabel.setTranslateY(50);
  StackPane top = new StackPane(topLabel);
  pane.setTop(top);

  // Create hbox for center section
  HBox centerBox = new HBox(15);
  for (int i = 1; i < vendingMachines; i++) {
   Button Machine = new Button("Machine" + i);
   Machine.setId("Machine" + i); // set button id
   Machine.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler()); // create event handler
   centerBox.getChildren().add(Machine);
  }

  centerBox.setSpacing(55);
  centerBox.setPadding(new Insets(0, 30, 0, 30));
  centerBox.setTranslateY(70);

  pane.setCenter(centerBox);
  menu.setScene(new Scene(pane, 700, 550));
  menu.show();
 }

 public int checkMachineInventory() {
  while (MoreMachines) {
   // create file to check
   File f = new File("inventory_" + (machine + 1) + ".csv");

   // tests if file exists
   MoreMachines = f.exists();

   // prints
   System.out.println("inventory_" + machine);
   machine++;
  }
  return machine;
 }

 // Event handler to handler machine selection
 private class MyEventHandler implements EventHandler < Event > {

  @Override
  public void handle(Event evt) {

   String id = (String)((Node) evt.getSource()).getId();

   switch (id) {
    case "Back":
     System.out.println("inventory_adsdasd");
     showMenu(machine);
     break;
    default:
     // Get number of machine ID that is clicked
     String viewMachine = ((Control) evt.getSource()).getId().substring(7);

     if (!("Back".equals(viewMachine))) {
      BorderPane restockStage = new BorderPane(); // Create border pane

      // Create Label for top section
      Label topLabel = new Label("MACHINE " + viewMachine + " Low Inventory");
      topLabel.setTranslateY(50);
      StackPane top = new StackPane(topLabel);
      restockStage.setTop(top);

      VBox restockPane = new VBox(); // Create border pane

      try {

       CSVReader inventory = new CSVReader(new FileReader("inventory_" + viewMachine + ".csv"), ',', '"', 1);

       // Save entries for each into lists
       List < String[] > inventoryList = inventory.readAll();
       restockPane.getChildren().add(new Label("PURCHASE ORDER"));
       double Total = 0;
       for (int i = 0; i < inventoryList.size(); i++) {
        int quantity = Integer.parseInt(inventoryList.get(i)[4]);
        if (quantity <= 3) {
         Label restock = new Label(inventoryList.get(i)[2] + " | order 10 more | price: " + (10 * Double.parseDouble(inventoryList.get(i)[3])) + "\n");
         Total += (10 * Double.parseDouble(inventoryList.get(i)[3]));
         restockPane.getChildren().add(restock);
        }
       }
       Label restock = new Label("Total price: " + Total);
       restockPane.getChildren().add(restock);


      } catch (IOException e) {
       e.printStackTrace();
      }

      restockPane.setTranslateY(70);
      restockPane.setTranslateX(25);
      restockStage.setCenter(restockPane);
      Button back = new Button("Back");
      back.setId("Back"); // set button id
      back.setTranslateY(-70);
      back.setTranslateX(25);
      back.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler()); // create event handler
      restockStage.setBottom(back);
      menu.setScene(new Scene(restockStage, 700, 550));
      menu.show();
      break;
    }
   }
  }
 }
}

