package controller;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import staffmanager.Personne;
import controller.MainStaffManager;

public class PersonOverviewController {

	@FXML
	private TableView<Personne> personTable;
	@FXML
	private TableColumn<Personne, String> firstNameColumn;
	@FXML
	private TableColumn<Personne, String> lastNameColumn;
	@FXML
	private TableColumn<Personne, Integer> ageColumn;
	@FXML
	private TableColumn<Personne, String> jobColumn;

	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label ageLabel;
	@FXML
	private Label jobLabel;

	// Reference to the main application.
	private MainStaffManager mainStaffManager;

	/**
	 * The constructor. The constructor is called before the initialize()
	 * method.
	 */
	public PersonOverviewController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the person table

		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
		ageColumn.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asObject());
		jobColumn.setCellValueFactory(cellData -> cellData.getValue().jobProperty());
		
	    // Clear person details.
	    showPersonDetails(null);

	    // Listen for selection changes and show the person details when changed.
	    personTable.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> showPersonDetails(newValue));
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 * 
	 */
	public void setMainStaffManager(MainStaffManager mainStaffManager) {
		this.mainStaffManager = mainStaffManager;

		// Add observable list data to the table
		personTable.setItems(mainStaffManager.getPersonData());
	}
	
	/**
	 * Fills all text fields to show details about the person.
	 * If the specified person is null, all text fields are cleared.
	 *
	 * @param person the person or null
	 */
	private void showPersonDetails(Personne person) {
	    if (person != null) {
	        // Fill the labels with info from the person object.
	        firstNameLabel.setText(person.getFirstName());
	        lastNameLabel.setText(person.getLastName());
	        jobLabel.setText(person.getJob());
	        ageLabel.setText(Integer.toString(person.getAge()));
	    } else {
	        // Person is null, remove all the text.
	        firstNameLabel.setText("");
	        lastNameLabel.setText("");
	        ageLabel.setText("");
	        jobLabel.setText("");
	    }
	}
	
	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeletePerson() {
		int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			personTable.getItems().remove(selectedIndex);
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainStaffManager.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person Selected");
			alert.setContentText("Please select a person in the table.");

			alert.showAndWait();
		}
	}
	
	/**
	 * Called when the user clicks the new button. Opens a dialog to edit
	 * details for a new person.
	 */
	@FXML
	private void handleNewPerson() {
	    Personne tempPerson = new Personne();
	    mainStaffManager.showPersonEditDialog(tempPerson);
	    mainStaffManager.getPersonData().add(tempPerson);
	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected person.
	 */
	@FXML
	private void handleEditPerson() {
	    Personne selectedPerson = personTable.getSelectionModel().getSelectedItem();
	    if (selectedPerson != null) {
	        boolean okClicked = mainStaffManager.showPersonEditDialog(selectedPerson);
	        if (okClicked) {
	            showPersonDetails(selectedPerson);
	        }

	    } else {
	        // Nothing selected.
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(mainStaffManager.getPrimaryStage());
	        alert.setTitle("No Selection");
	        alert.setHeaderText("No Person Selected");
	        alert.setContentText("Please select a person in the table.");

	        alert.showAndWait();
	    }
	}

}
