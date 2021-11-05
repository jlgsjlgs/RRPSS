import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ReservationList {

	StaffRoster staff = new StaffRoster();
	private final ArrayList<Reservation> resList = new ArrayList <>();
	private static int nextrID = 1;

	ArrayList<Reservation> getResList() {
		return resList;
	}

	void addReservation(String name, int pax, long phoneNum, LocalDate resDate, LocalTime resTime, Boolean mem) {
		resList.add(new Reservation(name, pax, phoneNum, resDate, resTime, mem, nextrID));
		nextrID++;
	}

	void removeReservation(long rID) {
		resList.removeIf(res -> (res.getrID() == rID));
	}

	Reservation getReservation(long rID){
		for (Reservation res : resList){
			if (res.getrID() == rID)
				return res;
		}
		return null;
	}
}
