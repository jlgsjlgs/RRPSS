public class Reservation {

	private String custName;
	private LocalDate date;
	private LocalTime time;
	private Int pax;
	private long contact;
	private Boolean membership;

	public Date getDate() {
		// TODO - implement Reservation.getDate
		throw new UnsupportedOperationException();
	}

	public Time getTime() {
		// TODO - implement Reservation.getTime
		throw new UnsupportedOperationException();
	}

	public Int getPax() {
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

	public Boolean checkAvailableTable() {
		// TODO - implement Reservation.checkAvailableTable
		throw new UnsupportedOperationException();
	}

}