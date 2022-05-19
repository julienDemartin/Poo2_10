package be.helha.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ApplicationEbar extends Application {
		
		@Override
		public void start(Stage primaryStage) {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/be/helha/vues/VuePrincipale.fxml"));
				Scene scene = new Scene(root, 614, 310);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("CPH Banque");
				primaryStage.getIcons().add(new Image("/be/helha/application/banque.jpg"));
				primaryStage.setResizable(false);
				primaryStage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public static void lancer(String[] args) {
			launch(args);			
		}
}
