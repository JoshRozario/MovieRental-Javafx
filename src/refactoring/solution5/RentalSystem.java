package refactoring.solution5;

import java.rmi.dgc.DGC;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
		
		movieClass.setValue(" ");
		price.setValue(" ");
		

		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		//movies
		Movie Default = new Movie("Movie", new Regular());
		Movie Titanic = new Movie("Titanic", new Classic());
		Movie FightClub = new Movie("Fight Club", new Classic());
		Movie Deadpool = new Movie("Deadpool", new Regular());
		Movie Deadpool2 = new Movie("Deadpool 2", new New());
		Movie Shrek3 = new Movie("Shrek the Third", new Regular());
		
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
		
		
		//price label
		Label priceLabel = new Label();
		priceLabel.textProperty().bind(price);
		GridPane.setConstraints(priceLabel, 2, 0);
		

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
				price.setValue("$"+ String.format("%.2f", theprice));
			}
			
		});
			
		
		//choiceBox
		ChoiceBox<Movie> choiceBox = new ChoiceBox<>();
		choiceBox.minWidth(2);
		choiceBox.getItems().add(Default);
		choiceBox.getItems().add(Titanic);
		choiceBox.getItems().add(FightClub);
		choiceBox.getItems().add(Shrek3);
		choiceBox.getItems().add(Deadpool);
		choiceBox.getItems().add(Deadpool2);
		
		
		choiceBox.setValue(Default);
		
		choiceBox.setOnAction(e -> {
			if (choiceBox.getValue().getTitle().equals("Default")){
				movieClass.setValue("");
			}else {
				movieClass.setValue(choiceBox.getValue().movieType.toString());
				curMovie = choiceBox.getValue();  
			}
			price.setValue(" ");
		});	
		
		//rent button
		Button rentButton = new Button ("Rent");
		GridPane.setConstraints(rentButton, 3, 1);
		rentButton.setOnAction(e -> {
			if (price.getValue().equals(" ")) {
				AlertBox.display("Alert", "Please calculate the price of a movie first");
			}else {
				
				if(!alreadyRented(Basket.showBasket(), choiceBox.getValue().getTitle())){
					Basket.add(new RentalItem(choiceBox.getValue(),price.getValue()));
					ArrayList<RentalComponent> test = Basket.showBasket();
					for(RentalComponent d : test){
						System.out.println(d.display());
						
					}
					price.setValue(" ");
				}else{
					AlertBox.display("Alert", "Movie already rented");
				}
				
			}
			
		});
		
		//Table
				Basket.add(new RentalItem(Shrek3,"2.50"));
		
				TableView<RentalComponent> table;
				
				TableColumn<RentalComponent, String> titleColumn = new TableColumn<RentalComponent, String>("Movie");
				titleColumn.setMinWidth(100);
				titleColumn.setCellValueFactory(new PropertyValueFactory<>("Movie"));
				
				TableColumn<RentalComponent, String> priceColumn = new TableColumn<RentalComponent, String>("Price");
				priceColumn.setMinWidth(100);
				priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
				
				table = new TableView<>();
				table.setItems(getRented());
				table.getColumns().addAll(titleColumn, priceColumn);
				
				GridPane.setConstraints(table, 1, 2);
		
		GridPane.setConstraints(choiceBox,0,0);
		
		  
		grid.getChildren().addAll(daysRented,choiceBox,daysLabel,movieLabel,priceLabel,priceButton,rentButton,table);
		
		Scene scene = new Scene(grid, 400, 200);
		
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
			if (d.getMovieName().equals(title)){
				return true;
			}
		}
		return false;
	}
	
	public ObservableList<RentalComponent> getRented(){
		ObservableList<RentalComponent>	rented = FXCollections.observableArrayList(Basket);
		return rented;
		
	}

}
