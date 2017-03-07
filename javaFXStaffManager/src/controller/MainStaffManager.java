package controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import staffmanager.Personne;

public class MainStaffManager extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private ObservableList<Personne> personData = FXCollections.observableArrayList();

	/**
	 * Constructor
	 */
	public MainStaffManager() {
		// Add some sample data
		personData.add(new Personne("David", "Smith", 25, "Waterpolo player"));
		personData.add(new Personne("Alex", "Polo", 65, "Truck Driver"));
		personData.add(new Personne("Jeremy", "Bob", 45, "Homeless"));
	}

	/**
	 * Returns the data as an observable list of Persons.
	 * 
	 * @return
	 */
	public ObservableList<Personne> getPersonData() {
		return personData;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("A new hope : the Staff Manager");

		initRootLayout();

		showPersonneInterface();
	}

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainStaffManager.class.getResource("/staffmanager/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Shows the person overview inside the root layout.
	 */
	public void showPersonneInterface() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainStaffManager.class.getResource("/staffmanager/PersonneInterface.fxml"));
			AnchorPane personneInterface = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(personneInterface);

			// Give the controller access to the main app.
			PersonOverviewController controller = loader.getController();
			controller.setMainStaffManager(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Opens a dialog to edit details for the specified person. If the user
	 * clicks OK, the changes are saved into the provided person object and true
	 * is returned.
	 *
	 * @param person the person object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showPersonEditDialog(Personne person) {
	    try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainStaffManager.class.getResource("/staffmanager/PersonEditDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();
	        
	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Edit Person");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        dialogStage.show();

	        // Set the person into the controller.
	        PersonEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPerson(person);

	        return controller.isOkClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	/**
	 * Returns the main stage.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}