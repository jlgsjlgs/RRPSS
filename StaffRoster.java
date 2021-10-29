import java.util.*;
import java.util.Scanner;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.InputStream;

class StaffRoster {
	ArrayList<Staff> availStaff;
	
	StaffRoster(){
		this.availStaff = new ArrayList<>();
	}
	
void bootup(){
	InputStream is = StaffRoster.class.getResourceAsStream("TestStaffRoster.txt");
    Scanner filescanner = new Scanner(System.in);
    //System.out.println("File to read: ");
    String filename = "TestStaffRoster.txt";
//    try (Scanner filescan = new Scanner(Paths.get(filename))){
    try (Scanner filescan = new Scanner(is)) {
        ArrayList<String> fileLines = new ArrayList<>();
        
        while (filescan.hasNextLine()){
            String line = filescan.nextLine();
            if (line.equals("END")){
                break;
            }
            
            if (line.isEmpty() || !filescan.hasNextLine()){
                String staffName = fileLines.get(0);
                String staffGender = fileLines.get(1);
                String jobTitle = fileLines.get(2);
                int staffID = Integer.valueOf(fileLines.get(3)); 
                this.availStaff.add(new Staff(staffName, staffGender, jobTitle, staffID));
                fileLines.clear();
            } else {
                fileLines.add(line);
            }
        }
    } catch (Exception e){
        System.out.println("Error: " + e.getMessage());
    }
}

void showStaff(){
	System.out.println("*******************************************");
	for (int i = 0; i < availStaff.size(); i++) {
		Staff tempStaff = availStaff.get(i);
		System.out.println("Name: " + tempStaff.getName());
		System.out.println("Gender: " + tempStaff.getGender());
		System.out.println("Role: " + tempStaff.getJobTitle());
		System.out.println("Employee ID: " + tempStaff.getEmployeeID());
		System.out.println("*******************************************");
	}
}
}
