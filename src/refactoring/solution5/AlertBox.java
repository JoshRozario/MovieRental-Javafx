package refactoring.solution5;



import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
	
	
	public static void display(String title, String message) {
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		window.setMinHeight(100);
		
		Label label = new Label(message);
		
		Button Ok = new Button("OK");
		
		
		Ok.setOnAction(e -> {
			window.close();
			
		});
		
		
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, Ok);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
		
		
	}
	

}
