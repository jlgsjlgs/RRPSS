import java.util.*;

class StaffRoster {

	ArrayList<Staff> availStaff;
	
	void addStaff(String name, String gender, int ID) {
		Staff newStaff = new Staff(name, gender, ID);
		availStaff.add(newStaff);
	}
	
	void removeStaff(int ID) {
		availStaff.removeIf(staff -> staff.getEmployeeID() == ID);
	}
}
