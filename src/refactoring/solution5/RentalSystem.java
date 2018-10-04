package refactoring.solution5;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
		
		StackPane rootPane = new StackPane();
		
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		GridPane grid2 = new GridPane();
		grid2.setPadding(new Insets(10, 10, 10, 10));
		grid2.setVgap(8);
		grid2.setHgap(10);
		
		rootPane.getChildren().addAll(grid,grid2);
		
		//movies
		Movie Default = new Movie("Movie", new Regular());
		Movie Titanic = new Movie("Titanic", new Classic());
		Movie FightClub = new Movie("Fight Club", new Classic());
		
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
		
		choiceBox.setValue(Default);
		
		choiceBox.setOnAction(e -> {
			if (choiceBox.getValue().getTitle().equals("Default")){
				movieClass.setValue("");
			}else {
				movieClass.setValue(choiceBox.getValue().movieType.toString());
				curMovie = choiceBox.getValue();  
			}
		});	
		
		GridPane.setConstraints(choiceBox,0,0);
		
		  
		grid.getChildren().addAll(daysRented,choiceBox,daysLabel,movieLabel,priceLabel,priceButton);
		
		Scene scene = new Scene(rootPane, 270, 110);
		
		window.setScene(scene);
		
		
		window.show();
		
	}
	private int isInt(TextField input) {
		try {
			int daysRented = Integer.parseInt(input.getText());
			return daysRented;
		}catch(NumberFormatException e) {
			System.out.println("passcode must only contain numbers");
			return -1;
		}
	}
	

}
