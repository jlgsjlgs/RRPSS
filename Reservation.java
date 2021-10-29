import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {

	private String custName;
	private LocalDate date;
	private LocalTime time;
	private int pax;
	private long contact;
	private Boolean membership;

	public LocalDate getDate() {
		return date;
	}

	public LocalTime getTime() {
		return time;
	}

	public int getPax() {
		return this.pax;
	}

	public String getCustName() {
		return this.custName;
	}

	public long getContact() {
		return this.contact;
	}

	public Boolean getMembership() {
		return this.membership;
	}

	public Boolean checkAvailableTable(SeatingManagement sm,int seats) {
		int table = sm.getAvailTable(seats);
		if(table == -1)
			return false;
		sm.reserveATable(table);
		return true;
	}

}