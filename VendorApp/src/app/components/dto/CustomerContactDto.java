package app.components.dto;

public class CustomerContactDto {

	private String lastName;

	private String firstName;
	private String contactNo;

	private String emailAddress;

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

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

	@Override
	public String toString() {
		return "CustomerContactDto{" +
				"lastName='" + lastName + '\'' +
				", firstName='" + firstName + '\'' +
				", contactNo='" + contactNo + '\'' +
				", emailAddress='" + emailAddress + '\'' +
				'}';
	}
}
