import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

class ReservationList {
	static final long deadline = 10;
	
	private ArrayList<Reservation> resList;
	private static int NEXT_RID;
	
	ReservationList(){
		resList = new ArrayList <Reservation>();
		NEXT_RID = 1;
	}

	ArrayList<Reservation> getResList() {
		return resList;
	}

	void addReservation(String name, int pax, long phoneNum, LocalDate resDate, LocalTime resTime, Boolean mem, int tID) {
		Reservation tempReservation = new Reservation(name, pax, phoneNum, resDate, resTime, mem, NEXT_RID, tID);
		resList.add(tempReservation);

		System.out.println("Please note, your reservation ID is : " + NEXT_RID);
		NEXT_RID++;	
	}

	void removeReservation(long rID) {
		if (resList.removeIf(res -> (res.getrID() == rID))){
			//have to delete res obj??
			System.out.println("Your reservation " + rID+ "is successfully removed");
		}
		else 
			System.out.println("Error in removing reservation. No such reservation");
	}

	Reservation getReservation(long rID){
		for (Reservation res : resList){
			if (res.getrID() == rID)
				return res;
		}
		return null;
	}

	void ReservationChecker(SeatingManagement sm){
		ArrayList <Long> toRemove = new ArrayList<Long>();
		int i=0;
		for (Reservation tempReservation : resList){
			if (tempReservation.getReservationDate().isBefore(LocalDate.now())){
				toRemove.add(tempReservation.getrID());
			} 
			if ((LocalTime.now().isAfter(tempReservation.getReservationTime().plusSeconds(deadline))) && (tempReservation.getReservationDate().equals(LocalDate.now()))){
				toRemove.add(tempReservation.getrID());
			}
		}
		while (toRemove.size() != 0){
			resList.remove(getReservation(toRemove.get(0)));
			toRemove.remove(0);
		}
	}
}
