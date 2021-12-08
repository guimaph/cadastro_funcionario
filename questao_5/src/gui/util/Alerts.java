package gui.util;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Alerts {

	public static boolean showAlert(String title, String header, String content, AlertType type) {
		
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		//alert.show();
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if(!result.isPresent() || result.get() != ButtonType.OK) {
			return false;
		} else {
			return true;
		}
	}
}