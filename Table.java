public class Table {

	private Order order;
	private int tableID;
	private int numOfSeats;
	private Status status;

	//package access level constructor
	Table(int tID, int seats){
		tableID = tID;
		numOfSeats = seats;
		status = Status.EMPTY;
		order = null;
	}

	public Order getOrder() {
		return this.order;
	}

	public int getTableID() {
		return this.tableID;
	}

	public int getNumOfSeats() {
		return this.numOfSeats;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setOrder(Order o){
		order = o;
	}

	public void setStatus(Status s){
		status = s;
	}

}
