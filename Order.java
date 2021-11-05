import java.util.ArrayList;

class Order {

	private int orderID;
	private Staff staff;
	private ArrayList<OrderItem> myOrder;
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

	ArrayList<OrderItem> getMyOrder() {
		return myOrder;
	}

	int getTableID() {
		return tableID;
	}

	
	void addItemToOrder(Item item, int quantity) {
		
		for(Item oItem : myOrder){
			if(oItem.getOrderName() == item.getName()){ //if item already exists in myOrder
				oItem.addMore(quantity);
				return;
			}
		}
		myOrder.add(new OrderItem(item, quantity)); //add new item in myOrder
	}
	

	boolean removeItemFromOrder(Item item) {
		
		for(OrderItem oItem : myOrder){
			if(oItem.getOrderName() == item.getName()){ //found order item
				oItem = null; //delete item obj
				myOrder.remove(oItem); //delete item from myOrder arraylist
				return true;
			}
		}
		
		//else if item not found in myOrder
		System.out.println("This item cannot be removed since it doesn't exist in your order.");
		return false;
	}

	void printOrder() {
		System.out.println("---------------------------------------------------------------");
		for(OrderItem oItem : myOrder)
			System.out.println(oItem.getOrderQuantity() +" "+ oItem.getOrderName() +"\t\t" +oItem.price()); 
		System.out.println("---------------------------------------------------------------");
	}

	double totalPrice() {
		double totalPrice = 0;
		for(OrderItem oItem : myOrder)
			totalPrice += oItem.price();
		return totalPrice;
	}
	
}
