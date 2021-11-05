import java.time.LocalDate;
import java.time.LocalTime;

class Reservation {

	private LocalDate reservedDate;
	private LocalTime reservedTime;
	private String custName;
	private int pax;
	private long contact;
	private Boolean membership;
	private long rID;
	private int tID;

	Reservation(String name, int pax, long phoneNum, LocalDate resDate, LocalTime resTime, Boolean mem, long rId){
		custName = name;
		this.pax = pax;
		contact = phoneNum;
		reservedDate = Date.setDate(resDate);
		reservedTime = Date.setTime(resTime);
		membership = mem;
		this.rID = rID;
	}

	LocalDate getReservationDate() {
		return reservedDate;
	}

	LocalTime getReservationTime() {
		return reservedTime;
	}

	int getPax() {
		return this.pax;
	}

	String getCustName() {
		return this.custName;
	}

	Boolean getMembership() {
		return this.membership;
	}

	long getrID(){
		return rID;
	}

	int allocatedReserveTable(SeatingManagement sm) { //if returns -1, no table resrved, else, reservation is successful
		int tID = sm.getAvailTable(getPax());
		if(tID != -1)
			sm.reserveATable(tID);
		else
			System.out.println("No tables with your current pax available atm, try reserving again tomorrow or put ut child at home");
			//also in main, delete this reservation
		return tID;
	}
}
