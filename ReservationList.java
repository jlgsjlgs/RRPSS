import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

class ReservationList {
	
	private ArrayList<Reservation> resList;
	private static int NEXT_RID;
	
	ReservationList(){
		resList = new ArrayList <Reservation>();
		NEXT_RID = 1;
	}

	ArrayList<Reservation> getResList() {
		return resList;
	}

	void addReservation(String name, int pax, long phoneNum, LocalDate resDate, LocalTime resTime, Boolean mem) {
		resList.add(new Reservation(name, pax, phoneNum, resDate, resTime, mem, NEXT_RID));
		NEXT_RID++;
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
