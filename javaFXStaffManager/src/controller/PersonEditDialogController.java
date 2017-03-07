package controller;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import staffmanager.Personne;

public class PersonEditDialogController {

	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField ageField;
	@FXML
	private TextField jobField;

	private Stage dialogStage;
	private Personne person;
	private boolean okClicked = false;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
	}

	/**
	 * Sets the stage of this dialog.
	 *
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Sets the person to be edited in the dialog.
	 *
	 * @param person
	 */
	public void setPerson(Personne person) {
		this.person = person;
		
		firstNameField.setText(person.getFirstName());
		lastNameField.setText(person.getLastName());
		ageField.setText(Integer.toString(person.getAge()));
		jobField.setText(person.getJob());
	}

	/**
	 * Returns true if the user clicked OK, false otherwise.
	 *
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() {
		if (isInputValid()) {
			person.setFirstName(firstNameField.getText());
			person.setLastName(lastNameField.getText());
			person.setAge(Integer.parseInt(ageField.getText()));
			person.setJob(jobField.getText());
			
			okClicked = true;
			dialogStage.close();
		}
	}

	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	/**
	 * Validates the user input in the text fields.
	 *
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
		String errorMessage = "";

		if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
			errorMessage += "No valid first name!\n";
		}
		if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
			errorMessage += "No valid last name!\n";
		}
		if (jobField.getText() == null || jobField.getText().length() == 0) {
			errorMessage += "No valid street!\n";
		}

		if (ageField.getText() == null || ageField.getText().length() == 0) {
			errorMessage += "No valid Age!\n";
		} else {
			// try to parse the postal code into an int.
			try {
				Integer.parseInt(ageField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid Age (must be an integer)!\n";
			}
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}

}
