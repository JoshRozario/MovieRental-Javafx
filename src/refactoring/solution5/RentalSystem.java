package refactoring.solution5;

import java.rmi.dgc.DGC;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class RentalSystem extends Application {

	Stage window;
	Scene welcomeScene, moviesScene;
	
	
	
	public static Scene boughtScene;
	private int days;
	private Movie curMovie;
	RentalComponent Basket = new RentalBasket();
	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		
		window.setTitle("Movie Rental System");
		
		
		
		StringProperty movieClass = new SimpleStringProperty();
		StringProperty price = new SimpleStringProperty();
		StringProperty Tprice = new SimpleStringProperty();
		
		movieClass.setValue(" ");
		price.setValue(" ");
		

		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		//movies
		BasicMovie Default = new BasicMovie("Movie", new Regular());
		BasicMovie Titanic = new BasicMovie("Titanic", new Classic());
		BasicMovie FightClub = new BasicMovie("Fight Club", new Classic());
		BasicMovie Deadpool = new BasicMovie("Deadpool", new Regular());
		BasicMovie Deadpool2 = new BasicMovie("Deadpool 2", new New());
		BasicMovie Shrek3 = new BasicMovie("Shrek the Third", new Regular());
		
		curMovie = Default;
		
		
		
		
		
		//days rented textfield
		TextField daysRented = new TextField("");
		daysRented.setPrefWidth(40);
		daysRented.setPromptText("0");
		GridPane.setConstraints(daysRented, 1, 1);
		
		
		//days rented label
		Label daysLabel = new Label(" Days to rent:");
		GridPane.setConstraints(daysLabel, 0, 1);
		
		
		//movie class label
		Label movieLabel = new Label();
		movieLabel.textProperty().bind(movieClass);
		GridPane.setConstraints(movieLabel,1,0);
		
		//Cart class label
		Label cartLabel = new Label("Cart:");
		GridPane.setConstraints(cartLabel,0,2);
		GridPane.setHalignment(cartLabel, HPos.RIGHT);
		GridPane.setValignment(cartLabel, VPos.TOP);
		
		//price label
		Label priceLabel = new Label();
		priceLabel.textProperty().bind(price);
		GridPane.setConstraints(priceLabel, 1, 0);
		GridPane.setHalignment(priceLabel, HPos.RIGHT);
		
		
		
		
		
		
		
		//price button
		Button priceButton = new Button ("Calculate");
		GridPane.setConstraints(priceButton, 2, 1);
		priceButton.setOnAction(e -> window.close());
		
		priceButton.setOnAction(e -> {
			if (isInt(daysRented) <= 0 || (curMovie.equals(Default))) {
				AlertBox.display("Alert", "Please enter a positive number \nand Select a movie");
			}else {
				days = isInt(daysRented);
				double theprice = curMovie.getCharge(days);
				price.setValue(String.format("%.2f", theprice));
			}
			
		});
			
		
		//choiceBox
		ChoiceBox<BasicMovie> choiceBox = new ChoiceBox<>();
		choiceBox.minWidth(2);
		choiceBox.getItems().add(Default);
		choiceBox.getItems().add(Titanic);
		choiceBox.getItems().add(FightClub);
		choiceBox.getItems().add(Shrek3);
		choiceBox.getItems().add(Deadpool);
		choiceBox.getItems().add(Deadpool2);
		
		
		choiceBox.setValue(Default);
		
		choiceBox.setOnAction(e -> {
			if (choiceBox.getValue().getTitle().equals("Movie")){
				movieClass.setValue("");
			}else {
				movieClass.setValue(choiceBox.getValue().movieType.toString());
				curMovie = choiceBox.getValue();  
				
			}
			price.setValue(" ");
		});	
		
		GridPane.setConstraints(choiceBox,0,0);
		
		
		//Checkbox
		CheckBox schoolH = new CheckBox("School Holiday");
		CheckBox pubH = new CheckBox("Public Holiday");
		GridPane.setConstraints(schoolH, 2, 0);
		GridPane.setConstraints(pubH, 3, 0);
				
		pubH.setOnAction(e -> {
			if (pubH.isSelected()){
				 curMovie = new PubHolidayMovie(curMovie);
			}else {
				 curMovie = new BasicMovie(choiceBox.getValue().getTitle(),choiceBox.getValue().movieType);
			}
		});
		
		schoolH.setOnAction(e -> {
			if (schoolH.isSelected()){
				 curMovie = new SchoolHolidayMovie(curMovie);
			}else {
				 curMovie = new BasicMovie(choiceBox.getValue().getTitle(),choiceBox.getValue().movieType);
			}
		});
				
		
		
		//Table
		TableView<RentalItem> table;
		
		TableColumn<RentalItem, String> titleColumn = new TableColumn<RentalItem, String>("Movie");
		titleColumn.setMinWidth(100);
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("Movie"));
		
		TableColumn<RentalItem, String> priceColumn = new TableColumn<RentalItem, String>("Price");
		priceColumn.setMinWidth(100);
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		table = new TableView<>();
		table.setItems(getRented());
		table.getColumns().addAll(titleColumn, priceColumn);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		GridPane.setConstraints(table, 1, 2);
		
		//Total price Label
		Label TpriceLabel = new Label();
		TpriceLabel.textProperty().bind(Tprice);
		GridPane.setConstraints(TpriceLabel,1,3);
		Tprice.set("Total: ");	
		
		
		//rent button
		Button rentButton = new Button ("Rent");
		GridPane.setConstraints(rentButton, 3, 1);
		rentButton.setOnAction(e -> {
			if (price.getValue().equals(" ")) {
				AlertBox.display("Alert", "Please calculate the price of a movie first");
			}else {
				
				if(!alreadyRented(((RentalBasket) Basket).showBasket(), choiceBox.getValue().getTitle())){
					((RentalBasket) Basket).add(new RentalItem(choiceBox.getValue(), price.getValue()));
					table.setItems(getRented());
					String total = ((RentalBasket) Basket).getPrices();
					//System.out.println(total);
					Tprice.setValue("Total: "+ total);
					price.setValue(" ");
					
				}else{
					AlertBox.display("Alert", "Movie already rented");
				}
				
			}
			
		});
		
			
		
		  
		grid.getChildren().addAll(daysRented,choiceBox,daysLabel,movieLabel,priceLabel,priceButton,rentButton,table,TpriceLabel,cartLabel,pubH,schoolH);
		
		Scene scene = new Scene(grid, 530, 200);
		
		window.setScene(scene);
		
		window.show();
		
	}
	
	
	
	private int isInt(TextField input) {
		try {
			int daysRented = Integer.parseInt(input.getText());
			return daysRented;
		}catch(NumberFormatException e) {
			return -1;
		}
	}
	
	private boolean alreadyRented(ArrayList<RentalComponent> test,String title){
		
		for (RentalComponent d: test){
			if (((RentalItem) d).getMovie().equals(title)){
				return true;
			}
		}
		return false;
	}
	
	public ObservableList<RentalItem> getRented(){
		ObservableList<RentalItem>	rented = FXCollections.observableArrayList();
		for(RentalComponent d : ((RentalBasket) Basket).showBasket()) {
			rented.add((RentalItem) d);
		}
		return rented;
		
	}

}
