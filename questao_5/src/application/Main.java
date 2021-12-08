package application;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/gui/View.fxml"));
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setTitle("Cadastro de Funcionários");
			stage.show();
		} catch(IOException e) {
			e.printStackTrace();
			
		}
		
		//ViewController getTableData = new ViewController();
		//getTableData.getData();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}