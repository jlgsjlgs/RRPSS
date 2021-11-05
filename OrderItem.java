class OrderItem{
	private String orderName; //name
	private int quantity;

	OrderItem(MenuItem item , int q){
		orderName = item.getName();
		quantity = q;
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

}
