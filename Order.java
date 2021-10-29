import java.util.ArrayList;

class Order {

	private int orderID;
	private Staff staff;
	private ArrayList<MenuItem> myOrder;
	private int tableID;

	Order(int orderID,Staff staff,ArrayList<MenuItem> item,int tableID){
		this.orderID = orderID;
		this.staff = staff;
		this.myOrder = item;
		this.tableID = tableID;
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
		// TODO - implement Order.printOrder
		throw new UnsupportedOperationException();
	}

	void totalPrice() {
		// TODO - implement Order.totalPrice
		throw new UnsupportedOperationException();
	}

	void isMember() {
		// TODO - implement Order.isMember
		throw new UnsupportedOperationException();
	}

}
