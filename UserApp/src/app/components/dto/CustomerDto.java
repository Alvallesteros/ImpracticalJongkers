package app.components.dto;

public class CustomerDto {

	private String lastName;
	private String firstName;
	private String email;
	private String phoneNumber;
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public String toString() {
		return "CustomerDto [lastName=" + lastName + ", firstName=" + firstName + ", email=" + email + ", phoneNumber="
				+ phoneNumber + "]";
	}
}
