
public class Person {
	//Class attributes
	private String designation;
	private String name;
	private String number;
	private String email;
	private String address;
	
	// constructor	
	public Person(String designation, String name, String number, String email, String address) {
			
		this.designation = designation;
		this.name = name;
		this.number = number;
		this.email = email;
		this.address = address;
		}
	
	// Setters
	public void setName(String newName) {
		name = newName;
	}
	
	public void setNumber(String newNumber) {
		number = newNumber;
	}
	
	public void setEmail(String newEmail) {
		email = newEmail;
	}
	
	public void setAddress(String newAddress) {
		address = newAddress;
	}
	
	// method
	public String toString() {
		return 	"\n\n" + designation +
				"\nName:\t\t\t" + name +
				"\nNumber:\t\t\t" + number +
				"\nEmail:\t\t\t" + email +
				"\nAddress:\t\t" + address + "\n";	
		
	}
}
