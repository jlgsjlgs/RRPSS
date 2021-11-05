class OrderItem{
	private String orderName; //name
	private int quantity;
	private double itemPrice;

	OrderItem(MenuItem item , int q){
		orderName = item.getName();
		quantity = q;
		itemPrice = item.getPrice();
	}
	
	OrderItem(Promotional item , int q){
		orderName = item.getName();
		quantity = q;
		itemPrice = item.getPrice();
	}
	
	String getOrderName(){
		return orderName;
	}

	int getOrderQuantity(){
		return quantity;
	}
  
	void addMore(int incrBy){
		quantity += incrBy;
	}
	
	double price(){
		return quantity * itemPrice;
	}
	

}
