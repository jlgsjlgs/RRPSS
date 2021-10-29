import java.util.Scanner;

public class Staff {

	private String name;
	private String gender;
	private int employeeID;
	private StaffRole jobTitle;
        
        Staff(String name, String gender, int ID){
            this.name = name;
            this.gender = gender;
            this.employeeID = ID;
        }

        
	void setJobTitle() {
            Scanner sc = new Scanner(System.in);
            
            System.out.println("Job titles:");
            System.out.println("1. Server");
            System.out.println("2. Manager");
            System.out.println("3. Cashier");
            int userInput = Integer.valueOf(sc.nextLine());
            
            switch(userInput){
                case 1:
                    this.jobTitle = StaffRole.SERVER;
                    break;
                case 2:
                    this.jobTitle = StaffRole.MANAGER;
                    break;
                case 3:
                    this.jobTitle = StaffRole.CASHIER;
                    break;
            }
        }

	String getName() {
		return this.name;
	}

	String getGender() {
		return this.gender;
	}

	int getEmployeeID() {
		return this.employeeID;
	}

	StaffRole getJobTitle() {
		return this.jobTitle;
	}

}
