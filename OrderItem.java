class OrderItem{
	private String orderName; //name
	private int quantity;
	private double itemPrice;

	OrderItem(MenuItem item , int q){
		orderName = item.getName();
		quantity = q;
		itemPrice = item.getPrice();
	}
	
	OrderItem(Promotional set , int q){
		orderName = set.getName(); //name of the promotional set eg setA
		quantity = q;
		itemPrice = set.getPrice();
	}
	
	void getOrderName(){
		return orderName;
	}

	void getOrderQuantity(){
		return quantity;
	}
  
	void addMore(int incrBy){
		quantity += incrBy;
	}
	
	double price(){
		return quantity * itemPrice;
	}
	

}
