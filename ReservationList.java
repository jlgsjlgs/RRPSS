public class ReservationList {

	private static ArrayList <Reservation> resList = new ArrayList <Reservation>();
	private static nextrID = 1;

	static Reservation getResList() {
		return resList;
	}

	static void addReservation(String name, int pax, long phoneNum, LocalDate resDate, LocalTime resTime, Boolean mem) {
		resList.add(new Reservation(String name, int pax, long phoneNum, LocalDate resDate, LocalTime resTime, Boolean mem, nextrID));
		nextrID++;
	}

	static void removeReservation(long rID) {
		resList.removeIf(res -> (res.getrID() == rID));
	}

	Reservation getReservation(long rID){
		for (Resevation res : resList){
			if (res.getrID() == rID)
				return res;
		}
		return null;
	}
}
