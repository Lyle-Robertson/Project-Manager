public class ProjectDetails {

	//Class attributes
	private int projectNumber;
	private int erfNumber;
	private float totalCost;
	private float totalPaid;
	private String projectName;
	private String buildingType;
	private String projectAddress;
	private String dueDate;
	private Person customer;
	private Person architect;
	private Person builder;
	private String complete;
	private String completionDate;
	
	
	// constructor
	public ProjectDetails(int projectNumber, String projectName, String buildingType,
							String projectAddress, int erfNumber, float totalCost, float totalPaid,
							String dueDate, Person customer, Person architect, Person builder,
							String complete, String completionDate) {
		
		this.projectNumber = projectNumber;
		this.projectName = projectName;
		this.buildingType = buildingType;
		this.projectAddress = projectAddress;
		this.erfNumber = erfNumber;
		this.totalCost = totalCost;
		this.totalPaid = totalPaid;
		this.dueDate = dueDate;
		this.customer = customer;
		this.architect = architect;
		this.builder = builder;
		this.complete = complete;
		this.completionDate = completionDate;
		
	}
	
	// setters

	
	public void setTotalPaid(float newTotalPaid) {
		totalPaid = newTotalPaid;
	}
	
	public void setDueDate(String newDueDate) {
		dueDate = newDueDate;
	}
	
	public void setComplete(String completed) {
		complete = completed;
	}
	
	public void setCompletionDate(String dateCompleted) {
		completionDate = dateCompleted;
	}
	
	// getters
	public int getProjectNumber() {
		return projectNumber;
	}
	
	public String getProjectName() {
		return projectName;
	}
	
	public float getTotalCost() {
		return totalCost;
	}
	
	public float getTotalPaid() {
		return totalPaid;
	}
	
	public String getDueDate() {
		return dueDate;
	}
	
	public String getComplete() {
		return complete;
	}
	
	public String getCompletionDate() {
		return completionDate;
	}
	
	public Person customer() {
		return customer;
	}
	
	public Person architect() {
		return architect;
	}
	
	public Person builder() {
		return builder;
	}
	
	// Method
	public String toString() {
		return 	"\nProject Details:\n" +
				"\nProject name:\t\t" + projectName +
				"\nProject number:\t\t" + projectNumber +
				"\nBuilding type:\t\t" + buildingType + 
				"\nProject address:\t" + projectAddress +
				"\nERF number:\t\t" + erfNumber +
				"\nTotal Cost:\t\tR" + totalCost +
				"\nTotal Paid:\t\tR" + totalPaid +
				"\nDue Date:\t\t" + dueDate +
				customer +
				architect +
				builder +
				"\nCompletion Status:\t" + complete +
				"\nCompletion Date:\t" + completionDate + "\n\n"; 
		
	}						
}
