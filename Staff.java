import java.util.Scanner;

class Staff {

	private String name;
	private String gender;
	private int employeeID;
	
    Staff(String name, String gender, int ID){
            this.name = name;
            this.gender = gender;
            this.employeeID = ID;
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

}
