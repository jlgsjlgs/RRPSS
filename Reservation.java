import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {

	private String custName;
	private LocalDate date;
	private LocalTime time;
	private int pax;
	private long contact;
	private Boolean membership;

	LocalDate getDate() {
		return date;
	}

	LocalTime getTime() {
		return time;
	}

	int getPax() {
		return this.pax;
	}

	String getCustName() {
		return this.custName;
	}

	long getContact() {
		return this.contact;
	}

	Boolean getMembership() {
		return this.membership;
	}

	Boolean checkAvailableTable(SeatingManagement sm,int seats) {
		int table = sm.getAvailTable(seats);
		if(table == -1)
			return false;
		sm.reserveATable(table);
		return true;
	}

}
