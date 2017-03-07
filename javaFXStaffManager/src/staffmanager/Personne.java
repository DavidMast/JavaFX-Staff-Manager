package staffmanager;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Personne {

	private final StringProperty firstName;
	private final StringProperty lastName;
	private final StringProperty job;
	private final IntegerProperty age;

	/**
	 * Default constructor.
	 */
	public Personne () {
		//this(null, null, 0, null);
		this.firstName = new SimpleStringProperty(null);
		this.lastName = new SimpleStringProperty(null);
		this.age = new SimpleIntegerProperty(0);
		this.job = new SimpleStringProperty(null);
	}

	/**
	 * Constructor with some initial data.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param age
	 * @param job
	 */
	public Personne(String firstName, String lastName, int age, String job) {
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.age = new SimpleIntegerProperty(age);
		this.job = new SimpleStringProperty(job);
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	public StringProperty firstNameProperty() {
		return firstName;
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public StringProperty lastNameProperty() {
		return lastName;
	}

	public int getAge() {
		return age.get();
	}

	public void setAge(int age) {
		this.age.set(age);
	}

	public IntegerProperty ageProperty() {
		return age;
	}

	public String getJob() {
		return job.get();
	}

	public void setJob(String job) {
		this.job.set(job);
	}

	public StringProperty jobProperty() {
		return job;
	}

}
