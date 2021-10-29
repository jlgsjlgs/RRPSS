import java.util.*;

class StaffRoster {

	ArrayList<Staff> availStaff;
	
	void addStaff(String name, String gender, int ID) {
		Staff newStaff = new Staff(name, gender, ID);
		availStaff.add(newStaff);
	}
	
	void removeStaff(int ID) {
		for (int i = 0; i < availStaff.size(); i++) {
			if (availStaff.get(i).getemployeeID() == ID) availStaff.remove(i);
		}
	}
}
