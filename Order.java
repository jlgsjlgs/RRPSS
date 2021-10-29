import java.util.ArrayList;

class Order {

	private int orderID;
	private Staff staff;
	private ArrayList<MenuItem> myOrder;
	private int tableID;

	Order(int orderID, Staff staff, int tableID){
		this.orderID = orderID;
		this.staff = staff;
		this.tableID = tableID;
		myOrder = null;
	}

	int getOrderID() {
		return this.orderID;
	}

	Staff getStaff() {
		return this.staff;
	}

	ArrayList<MenuItem> getMyOrder() {
		return myOrder;
	}

	int getTableID() {
		return tableID;
	}

	void addItemToOrder(MenuItem item) {
		myOrder.add(item);
	}

	void removeItemFromOrder(MenuItem item) {
		myOrder.remove(item);
	}

	void printOrder() {
		for(MenuItem orderItem : myOrder)
			System.out.println(orderItem);
	}

	void totalPrice() {
		// to do
	}

	void isMember() {
		// TODO - implement Order.isMember
		throw new UnsupportedOperationException();
	}

}
