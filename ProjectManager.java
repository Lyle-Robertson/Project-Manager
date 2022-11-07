import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;

public class ProjectManager {
	
	// global variables
	static List<String> importFileData = new ArrayList<>();
	static Person contractor;
	static Person customer = new Person("Customer", " ", " ", " ", " ");
	static Person architect = new Person("Architect", " ", " ", " ", " ");
	static String fullname = "";
	static final String ERRORMESSAGE = "Check input and try again"; 
	static List<Object> projectsObject = new ArrayList<>();
	static float totalPaid = 0;
	static int erfNumber = 0;
	static float totalCost = 0;
	static String projectName = "";
	static String objName = "";
	static String nameOrNumberSearch = "";
	static int i = 0;
	static String dueDate = "";
	
	//methods
	
	//calling main method
	static void mainCaller() {
		main(null);
	}

	//writing complete object to a .txt file
	public static void appendStrToFile() {
		
		String writeToFile = (projectsObject.get(i)) +
						"-------------------------------------";
		
				
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("CompletedProjects.txt", true));
			// Writing on output stream
			out.write(writeToFile);
			out.close();
			mainCaller();
		}	
		catch (IOException e) {
			System.out.println("");
		}
	}
	
	//editing mainMenu
	static void editMenuu() {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Choose one of the options below:");
		System.out.println("""
							1.\tChange amount that has been paid
							2.\tEdit Contractor details
							3.\tChange Due Date
							4.\tFinalise (mark as complete)
							0.\tBack
							:
							""");
			
		int option2 = input.nextInt();
			
		if (option2 == 1) {
			editAmountPaid();
		}
		else if (option2 == 2) {
			editContractorDetails();
		}	
		else if (option2 == 3) {
			editDueDate();
		}
		else if (option2 == 4) {
			finalizeProject();
		}
		else if (option2 == 0) {
			mainCaller();
		}
		input.close();
	}
	
	//creating new project object
	static void collectProjectData() {
		
		//requesting input data from user
		
		Scanner input = new Scanner(System.in);
		
		//object and project_name	
		System.out.println("\nPlease enter the following information:\n");
		System.out.println("\nEnter the name of the project:");
		projectName = input.nextLine();
		objName = projectName;
		
		//clients name
		System.out.println("\nFirst and Last name of the Client:");
		fullname = input.nextLine();
		String[] splitName = fullname.split(" ");
			
		//building type
		System.out.println("\nThe type of building being designed:");
		String buildingType = input.nextLine();
		
		//creating project_name if project name is left empty
		if (projectName.isEmpty()) {		
			
			projectName = splitName[1] + " " + buildingType;
			objName = projectName;
		}
			
		//project address
		System.out.println("\nThe project address:");
		String projectAddress = input.nextLine();

		//ERF number 
		while (true) {
			try {
				System.out.println("\nThe ERF number:\t");
				String erf = input.nextLine();
				erfNumber = Integer.parseInt(erf);
				break;
			}catch (NumberFormatException e) {
				System.out.println("\nValue Entered is not number, Try again");
			 }
		}
		
		//due date
		while(true) {
			
			System.out.println("\nProject Due date (dd/mm/yyyy):");
			dueDate = input.nextLine();
		
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dateFormat.setLenient(false);
		
		    try {
		      dateFormat.parse(dueDate.trim());
		      break;
		    } 
		    catch (ParseException pe) {
		      System.out.println(ERRORMESSAGE);
		    }
		}
		
		//project cost
		while (true) {
			try {
				System.out.println("\nTotal Cost of the the project:");
				String cost = input.nextLine();
				totalCost = Float.parseFloat(cost);
				break;
			}	
			catch (NumberFormatException e) {
				System.out.println("Value Entered is not number, Try again");
			}
		}
		
		//amount paid
		while (true) {
			try {
				System.out.println("\nAmount paid to date:");
				String paid = input.nextLine();
				totalPaid = Float.parseFloat(paid);
				break;
			}	
			catch (NumberFormatException e) {
			System.out.println("Value Entered is not number, Try again");
			}
		}

		//instructions to user for creating instances in person class
		System.out.println("""
				The following data on the Customer, Architect and Builder will now be requested:
				1.\tfull name
				2.\ttelephone number
				3.\temail address
				4.\tphysical address.
				
				!!Separate each item by using a ','!!
				
				Customer:
				(telephone number, email address, physical address)
				
				""");
		
		// Instances for customer architect and builder in person class
		
		while(true) {
			String customerDetails = input.nextLine();
			String[] splitCustomerDetails = customerDetails.split(",");
			
			if (splitCustomerDetails.length == 3) {
				//Customer
				customer.setName(fullname);
				customer.setNumber(splitCustomerDetails[0]);
				customer.setEmail(splitCustomerDetails[1]);
				customer.setAddress(splitCustomerDetails[2]);
				break;
			}
			else {
				System.out.println("Check your input and try again");
			}	
		}
			
		while(true) {
			System.out.println("\nArchitect:\n(full name, telephone numer, email address, physical address)\n");
			String architectDetails = input.nextLine();
			String[] splitArchitectDetails = architectDetails.split(",");
				if (splitArchitectDetails.length == 4) {
					//architect
					architect.setName(splitArchitectDetails[0]);
					architect.setNumber(splitArchitectDetails[1]);
					architect.setEmail(splitArchitectDetails[2]);
					architect.setAddress(splitArchitectDetails[3]);
					break;
				}
				else {
					System.out.println("Check your input and try again");
				}
		}
		while(true) {
			System.out.println("\nBuilder:\n(full name, telephone numer, email address, physical address)\n");
			String builderDetails = input.nextLine();
			String[] splitBuilderDetails = builderDetails.split(",");
			
				if (splitBuilderDetails.length == 4) {
					Person builder = new Person("Builder", splitBuilderDetails[0], splitBuilderDetails[1], 
							splitBuilderDetails[2], splitBuilderDetails[3]);
						
						//projects number using size of array containing projects
					int projectNumber = 1 + projectsObject.size();
						
					//instance in project_details
					ProjectDetails objName = new ProjectDetails(projectNumber, projectName, buildingType,
																	projectAddress, erfNumber, totalCost, totalPaid, 
																	dueDate,  customer, architect, builder, 
																	"incomplete", " ");
						
						projectsObject.add(objName);
						System.out.println(objName);		
						System.out.println("The data has been successfully stored\n\n");
						break;
				}
				else {
					System.out.println("CHeck your input and try again");
				}
		}
		mainMenu();
		input.close();
}
				
	//editing amount paid
	static void editAmountPaid() {
		
		Scanner input = new Scanner(System.in);
		System.out.println((projectsObject.get(i)));
				
		//determining outstanding amount
		float amountOutstanding = ((ProjectDetails) projectsObject.get(i)).getTotalCost() -
								((ProjectDetails) projectsObject.get(i)).getTotalPaid();
			
		//displaying information
		System.out.println("\nTotal project cost is:\tR "
						+ ((ProjectDetails) projectsObject.get(i)).getTotalCost()
						+ "\nAmount paid:\t\tR "
						+ ((ProjectDetails) projectsObject.get(i)).getTotalPaid()
						+ "\nAmount outstanding:\tR " + amountOutstanding);
				
		System.out.println("\n\nEnter latest amount paid:");
		float latestAmount = input.nextFloat();
				
		//new amount paid
		float newPaidTotal = latestAmount + ((ProjectDetails) projectsObject.get(i)).getTotalPaid();
				
		//setting new amount paid
		((ProjectDetails) projectsObject.get(i)).setTotalPaid(newPaidTotal);
				
		float newAmountOutstanding = ((ProjectDetails) projectsObject.get(i)).getTotalCost() -
									((ProjectDetails) projectsObject.get(i)).getTotalPaid();
				
		System.out.println("Amount has been updated!\n");
		System.out.println("\nTotal project cost is:\tR "
						+ ((ProjectDetails) projectsObject.get(i)).getTotalCost()
						+ "\nAmount paid:\t\tR "
						+ ((ProjectDetails) projectsObject.get(i)).getTotalPaid()
						+ "\nAmount outstanding:\tR " + newAmountOutstanding
						+ "\n\n");
		editMenuu();
		input.close();
	}
		
	//editing contractor details
	static void editContractorDetails() {
		
		Scanner input = new Scanner(System.in);
				
		System.out.println("\nWhich contractor's details would you like to edit??");
		System.out.println("""
							a -\tarchitect
							b -\tbuilder
							0 -\tpevious menu
							
							""");	
		
		char choice = input.next().charAt(0);
		
		if (choice == 'a') {
			contractor = ((ProjectDetails) projectsObject.get(i)).architect();
		}
		else if (choice == 'b') {
			contractor = ((ProjectDetails) projectsObject.get(i)).builder();
		}
		else if (choice == '0') {
			editMenuu();
			input.close();
		}
		
		while (true) {	
			System.out.println(contractor);
			System.out.println("""
								What would you like to edit?:
								1.\tName
								2.\tNumber
								3.\tEmail
								4.\tAddress
								5.\tPrevious menu
								 
								 """);
				
				int edit = input.nextInt();
				
				if (edit == 1) {	
					
					System.out.println("Enter new name:");
					input.nextLine();
					String newName = input.nextLine();
						
					contractor.setName(newName);
						
					System.out.println("Name has successfully changed");
					System.out.println(contractor);
				}
				else if (edit == 2) {
						
					System.out.println("Enter new number:");
					input.nextLine();
					String newNumber = input.nextLine();
						
					contractor.setNumber(newNumber);
						
					System.out.println("Number successfully changed");
					System.out.println(contractor);
						
				}
				else if (edit == 3) {
						
					System.out.println("Enter new email:");
					input.nextLine();
					String newEmail = input.nextLine();
						
					contractor.setEmail(newEmail);
						
					System.out.println("Email successfully changed");
					System.out.println(contractor);
							
				}
				else if (edit == 4) {
						
					System.out.println("Enter new address:");
					input.nextLine();
					String newAddress = input.nextLine();
						
					contractor.setAddress(newAddress);
						
					System.out.println("Address successfully changed");
					System.out.println(contractor);
						
				}
				else if (edit == 5) {
					editContractorDetails();
					break;
				}
				else {
					System.out.println(ERRORMESSAGE);
				}
			}
		}
		
	//editing due date
	static void editDueDate() {
	
		Scanner input = new Scanner(System.in);
				
		String dueDate = ((ProjectDetails) projectsObject.get(i)).getDueDate();
		String projName = ((ProjectDetails) projectsObject.get(i)).getProjectName();
				
		System.out.println("\nProject name (dd/mm/yyyy):\t" + projName 
						+ "\nDue date:\t" + dueDate );
				
		System.out.println("\n\nEnter new due date (dd/mm/yyyy):");
		String newDate = input.nextLine();
				
		((ProjectDetails) projectsObject.get(i)).setDueDate(newDate);
				
		System.out.println("\n\nDue date successfully changed\n\n");
				
		System.out.println("Project name:\t" + projName 
						+ "\nDue date:\t" + ((ProjectDetails) projectsObject.get(i)).getDueDate()
						+ "\n\n");
		editMenuu();
		input.close();
	}
		
	//Finalizing project
	static void finalizeProject() {
		
		Scanner input = new Scanner(System.in);
		
		while(true) {
			
			System.out.println("\n\nEnter date of completion (dd/mm/yyyy)");
			String completionDate = input.nextLine();
		
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dateFormat.setLenient(false);
		
		    try {
		      dateFormat.parse(completionDate.trim());
		      ((ProjectDetails) projectsObject.get(i)).setCompletionDate(completionDate);
		      ((ProjectDetails) projectsObject.get(i)).setComplete("Complete");
						
				System.out.println("\n\nCompletion Status Changed");
						
				// invoice displayed only if amount has not been fully paid
				float amountOutstanding = ((ProjectDetails) projectsObject.get(i)).getTotalCost() -
										((ProjectDetails) projectsObject.get(i)).getTotalPaid();
						
				if (amountOutstanding == 0) {
					System.out.println("Project Cost has been paid in full.");
					appendStrToFile();	
				}
				else {
					System.out.println(((ProjectDetails) projectsObject.get(i)).customer());
					System.out.println("Outstanding amount:\tR " + amountOutstanding + "\n\n");
					appendStrToFile();
				}
		      break;
		    } 
		    catch (ParseException e) {
		      System.out.println(ERRORMESSAGE);
		    }
		}input.close();		
	}
	
	//importing data from .txt file
	static void importFromTxtFile() {
		
		//checking if import file exists
		File importFromFile = new File (System.getProperty("user.dir") + "\\ImportTasks.txt");
		try {				
			if (importFromFile.exists()) {
				//read the file
				FileReader importFileReader = new FileReader(importFromFile);
				try (BufferedReader importFile = new BufferedReader(importFileReader)) {
					//Storing input date as string
					String importFileLines;
					int lineCounter = -1;
					while ((importFileLines = importFile.readLine()) != null) {
						lineCounter ++;
						importFileData.add(importFileLines);
					}
					
					int projectLineIndex = 0;
					int numOfImportProjects = (int) Math.floor(lineCounter / 25);
					int loopCounter = 0;
					
					//creating projecDetails and Person objects from text file
					while(loopCounter < numOfImportProjects) {
						
						//making sure date format in correct
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						dateFormat.setLenient(false);    
						dateFormat.parse((importFileData.get(projectLineIndex + 7).split(":"))[1].replaceAll("\\s","").trim());
						   
						//imported customer instance of person
						Person importCustomer = new Person(
								(importFileData.get(projectLineIndex + 8).replace(":","")),
								(importFileData.get(projectLineIndex + 9).split(":"))[1].replaceAll("\\s",""), 
								(importFileData.get(projectLineIndex + 10).split(":"))[1].replaceAll("\\s",""),
								(importFileData.get(projectLineIndex + 11).split(":"))[1].replaceAll("\\s",""), 
								(importFileData.get(projectLineIndex + 12).split(":"))[1].replaceAll("\\s",""));
					
						//imported architect instance of person
						Person importArchitect = new Person(
								(importFileData.get(projectLineIndex + 13).replace(":","")),
								(importFileData.get(projectLineIndex + 14).split(":"))[1].replaceAll("\\s",""), 
								(importFileData.get(projectLineIndex + 15).split(":"))[1].replaceAll("\\s",""),
								(importFileData.get(projectLineIndex + 16).split(":"))[1].replaceAll("\\s",""), 
								(importFileData.get(projectLineIndex + 17).split(":"))[1].replaceAll("\\s",""));
						
						//imported builder instance of person
						Person importBuilder = new Person(
								(importFileData.get(projectLineIndex + 18).replace(":","")),
								(importFileData.get(projectLineIndex + 19).split(":"))[1].replaceAll("\\s",""), 
								(importFileData.get(projectLineIndex + 20).split(":"))[1].replaceAll("\\s",""),
								(importFileData.get(projectLineIndex + 21).split(":"))[1].replaceAll("\\s",""), 
								(importFileData.get(projectLineIndex + 22).split(":"))[1].replaceAll("\\s",""));
					
						//imported project instance of person
						ProjectDetails importProject = new ProjectDetails(
								Integer.parseInt((importFileData.get(projectLineIndex + 1).split(":"))[1].replaceAll("\\s","")), 
								(importFileData.get(projectLineIndex + 0).split(":"))[1].replaceAll("\\s",""), 
								(importFileData.get(projectLineIndex + 2).split(":"))[1].replaceAll("\\s",""), 
								((importFileData.get(projectLineIndex + 3).split(":"))[1].replaceAll("\\s","")).replace(",", " "),
								Integer.parseInt((importFileData.get(projectLineIndex + 4).split(":"))[1].replaceAll("\\s","")), 
								Float.parseFloat(((importFileData.get(projectLineIndex + 5).split(":"))[1].replaceAll("\\s","")).replace("R", "")), 
								Float.parseFloat(((importFileData.get(projectLineIndex + 6).split(":"))[1].replaceAll("\\s","")).replace("R", "")), 
								(importFileData.get(projectLineIndex + 7).split(":"))[1].replaceAll("\\s",""),
								importCustomer, 
								importArchitect, 
								importBuilder, 
								(importFileData.get(projectLineIndex + 23).split(":"))[1].replaceAll("\\s",""), 
								(importFileData.get(projectLineIndex + 24).split(":"))[1].replaceAll("\\s",""));
					
						projectsObject.add(importProject);
						projectLineIndex = projectLineIndex + 26;
						loopCounter ++;
					}
					mainMenu();
				}
			}
			else {
				System.out.println("File could not be found. make sure file is named 'ImportTasks.txt'");
			}
		}
		catch(Exception e) {
			System.out.println("Imported data does not match requirements. Check data and try again\n\nRestarting program\n\n");
			mainCaller();
		}
	}
	
	//view all projecets
	static void viewProjects() {
	
		for (i = 0; i < projectsObject.size(); i++) {
				System.out.println(projectsObject.get(i));
		}
		mainMenu();
	}

	//view incomplete projects
	static void viewIncompleteProjects() {
		
		for (i = 0; i < projectsObject.size(); i++) {
			if (((ProjectDetails) projectsObject.get(i)).getComplete().equals("incomplete")) {
				System.out.println(projectsObject.get(i));
			}
		}
		mainMenu();
	}
	
	//view overdue projects
	static void viewOverDueProjects() {
		
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
		String currentDate = dateFormat.format(date);
		
		for (i = 0; i < projectsObject.size(); i++) {
			if (((ProjectDetails) projectsObject.get(i)).getDueDate().compareTo(currentDate) > 0 ||
				((ProjectDetails) projectsObject.get(i)).getComplete().equals("incomplete") ||
				((ProjectDetails) projectsObject.get(i)).getCompletionDate().equals("")){
						System.out.println(projectsObject.get(i));
			}
		}
		mainMenu();
	}
	
	//storing all projects to text file
	static void projectDetailsToTxt() {
		
		try {
			for (i = 0; i < projectsObject.size(); i++) {
				String writeToFile = (projectsObject.get(i)) +
						"-------------------------------------";
		
				BufferedWriter out = new BufferedWriter(new FileWriter("ProjectList.txt", true));
				// Writing on output stream
				out.write(writeToFile);
				out.close();
			}	
			System.exit(0);
		}
		catch (IOException e) {
			System.out.println("");
		}
	}
	
	//main menu
	static void mainMenu() {
		
		try (Scanner input = new Scanner(System.in)) {
			System.out.println("""
					Choose and option from the list bellow:
					1.\tCreate a new project.
					2.\tEdit a project data.
					3.\tView incomplete projects
					4.\tView overdue projects
					5.\tView projects
					0.\tExit
				
					:
					"""
					);
		
			String option1 = input.nextLine();
		
			if (option1.equals("0")) {
				System.out.println("Goodbye!!");
				projectDetailsToTxt();
			}
			else if (option1.equals("1")) {
				collectProjectData();
			}
			else if (option1.equals("2")) {
				// searching through list of objects matching project name or number to user input	
				if (projectsObject.isEmpty()) {
					System.out.println("\n\nNo projects have been registered. First add projects\n\n");
					mainMenu();
				}
				else {
					System.out.println("Enter project name or number.\n:");	
				nameOrNumberSearch = input.nextLine();
			}	
				for (i = 0; i < projectsObject.size(); i++) {
					if (((ProjectDetails) projectsObject.get(i)).getProjectName().equals(nameOrNumberSearch)){
						System.out.println(projectsObject.get(i));
						editMenuu();
					}else {
						try {
							int check = Integer.parseInt(nameOrNumberSearch);
							if(((ProjectDetails) projectsObject.get(i)).getProjectNumber() == check) {
								System.out.println(projectsObject.get(i));
								editMenuu();
							}
						}catch (NumberFormatException e) {
							System.out.println("\nProject not found. check input and try again");
							mainMenu();
						}
					}
				}
			}
			else if (option1.equals("3")) {
				viewIncompleteProjects();
			}
			else if (option1.equals("4")) {
				viewOverDueProjects();
			}
			else if (option1.equals("5")) {
				viewProjects();
			}
			else {
				System.out.println("\nCheck input and try again\n");
				mainMenu();
			}
		}
	}
	
	//main method
	public static void main (String[] args) {

		try (Scanner input = new Scanner(System.in)) {
			
			System.out.println("Scan for external data to import into list of projects??");
			System.out.println("(yes/no)\n");
			String importData = input.nextLine();
			
			if (importData.equals("yes")) {
				importFromTxtFile();
				
			}else if (importData.equals("no")) {
				mainMenu();
			}
			else {
				System.out.println(ERRORMESSAGE + "\n\n");
				mainCaller();
			}
		}
	}
}

