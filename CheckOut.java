import java.time.LocalDate;
import java.time.LocalTime;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;

class CheckOut {

	static void doCheckOut(Order myOrder, ReservationList rl, SeatingManagement sm) {
		System.out.println("---------------------------------------------------------------");
		System.out.println("RESTAURANT NAME");
		System.out.println("---------------------------------------------------------------");
		System.out.println("Server: "+((Staff)myOrder.getStaff()).getName()+"\t\t"+LocalDate.now());
		System.out.println("Table: "+myOrder.getTableID()+"\t\t\t\t"+LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
		for(OrderItem item : myOrder.getMyOrder()){
			myOrder.printOrder();
			RRPSS.addToDailySales(item);
		}
		if (myOrder.getMembership()){
			System.out.println("10% discount given for membership!");
		}
		System.out.println("\tSubtotal:\t"+ NumberFormat.getCurrencyInstance().format(myOrder.totalPrice()));
		System.out.println("\tService Charge:\t"+ NumberFormat.getCurrencyInstance().format(myOrder.totalPrice()*0.1));
		System.out.println("\tGST:\t\t"+ NumberFormat.getCurrencyInstance().format((myOrder.totalPrice()*1.1)*0.07));
		System.out.println("\tTotal:\t\t"+ NumberFormat.getCurrencyInstance().format((myOrder.totalPrice()*0.1+ myOrder.totalPrice()*1.1*0.07+myOrder.totalPrice())));
		System.out.println("---------------------------------------------------------------");

		rl.removeReservation(myOrder.getOrderID());
		sm.unassignTable(myOrder.getTableID());
	}
}
