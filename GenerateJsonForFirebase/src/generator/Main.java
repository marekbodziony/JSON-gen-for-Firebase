package generator;

import java.io.IOException;

import generator.model.Generator;
import generator.model.TouristicItemType;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	
	private Stage stage;
	private VBox layout;
	
	
	@Override
	public void start(Stage stage){
		this.stage = stage;
		stage.setTitle("JSON Generator for Firebase (W4K project)");
		
		try {
			// load view from view.fxml (it also create controller)
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/View.fxml"));
			layout = (VBox)loader.load();
			
			// set scene and stage
			Scene scene = new Scene(layout);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
