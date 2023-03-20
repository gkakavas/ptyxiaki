package application;
	
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;


public class Main extends Application {
	@FXML
	private static Stage myStage;
	@Override
	public void start(Stage primaryStage) {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setTitle("Πτυχιακή");
				primaryStage.setScene(scene);
				primaryStage.show();
				myStage = primaryStage;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	
	public static Stage getStage() {
		return myStage;
	}
	
	public static void main(String[] args)  {
		launch(args);
		}
}
