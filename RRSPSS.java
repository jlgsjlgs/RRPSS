import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RRSPSS {

	//bootstart
	private static final Menu menu = new Menu();
	private static final StaffRoster roster = new StaffRoster();
	private static final SeatingManagement sm = new SeatingManagement();
	private static final ReservationList rl = new ReservationList();
	private static final ArrayList<Order> orders = new ArrayList<>();
	 

	/**
	 * Helper function to get int input that is valid and within range from user
	 * @param min Minimum value
	 * @param max Maximum value
	 * @return input
	 */
	private static int getInput(Integer min,Integer max){
		Scanner scanner = new Scanner(System.in);
		int choice = 0;
		do {
			try {
				choice = scanner.nextInt();
			}catch (InputMismatchException e){
				System.out.println("Invalid number, try again");
			}
			if(choice < min || choice > max){
				System.out.println("Must be within the range of "+min+" and "+max+", try again");
			}else
				break;
		}while (true);
		return choice;
	}

	public static void main(String[] args) {
		roster.showStaff();
		System.out.println("Identify yourself");
		int id = getInput(0,roster.availStaff.size()-1);
		Staff staff = roster.availStaff.get(id);
		System.out.println("Welcome, "+staff.getName()+" ("+staff.getJobTitle()+")");
		while(true) {
			System.out.println("1. Edit menu");
			System.out.println("2. Edit promotions");
			System.out.println("3. Create order");
			System.out.println("4. View order");
			System.out.println("5. Edit order");
			System.out.println("6. Create reservation");
			System.out.println("7. Check/Remove reservation");
			System.out.println("8. Check table availability");
			System.out.println("9. Print order invoice");
			System.out.println("10. Print sales revenue report");
			System.out.println("11. Exit");
			System.out.println("Enter choice");
			switch (getInput(1,11)){
				case 1:
					System.out.println("Editing menu");
					System.out.println("1. Add");
					System.out.println("2. Update");
					System.out.println("3. Remove");
					System.out.println("Enter choice");
					switch (getInput(1,3)){
						case 1: menu.addMenuItem();break;
						case 2: menu.updateMenuItem();break;
						case 3: menu.deleteMenuItem();break;
					}
					break;
				case 2:
					System.out.println("Editing promotions");
					System.out.println("1. Add");
					System.out.println("2. Update");
					System.out.println("3. Remove");
					System.out.println("Enter choice");
					switch (getInput(1,3)){
						case 1: menu.addPromotionalItem();break;
						case 2: menu.updatePromotionalItem();break;
						case 3: menu.deletePromotionalItem();break;
					}
					break;
				case 3:
					createOrder(staff);
					break;
				case 4:
					viewOrder();
					break;
				case 5:
					System.out.println("Editing order");
					System.out.println("1. Add item(s)");
					System.out.println("2. Remove item(s)");
					System.out.println("Enter choice");
					switch (getInput(1,2)){
						case 1: addItemToOrder();break;
						case 2: removeItemFromOrder();break;
					}
					break;
				case 6:
					System.out.println("Enter customer name");
					Scanner scanner = new Scanner(System.in);
					String name = scanner.next();
					System.out.println("Enter pax");
					int pax = getInput(1,10);
					if(!hasAvailableTable(pax)){
						System.out.println("No available table");
						return;
					}
					createReservation(name,pax);
					break;
				case 7:
					//edit reservation
					System.out.println("What's ur rID?");
					Scanner scan = new Scanner(System.in);
					long rID = scan.nextLong();

					if(hasReservation(rID)){
						Reservation reservation = rl.getReservation(rID);
						System.out.println("Reservation of "+reservation.getPax()+" pax by "+reservation.getCustName());
						System.out.println(reservation.getReservationDate()+" "+reservation.getReservationTime());
						System.out.println("1. Remove");
						System.out.println("2. Back");
						if(getInput(1,2) == 1){
							rl.removeReservation(rID);
							System.out.println("Reservation removed");
						}
					}else
						System.out.println("No such reservation");
					break;
				case 8:
					System.out.println("Enter No. of pax");
					System.out.println(hasAvailableTable(getInput(1,10)) ? "Table available" : "No available table");
					break;
				case 9:
					printOrderInvoice();
					break;
				case 10:
					printSaleRevenue();
					break;
				case 11:
					return;
				default:
					System.out.println("Invalid choice");
					break;
			}
		}
	}
	//reservation

	static void createReservation(String name, int pax) {

		Scanner scan = new Scanner(System.in);

		//check if table available
		// if yes then actually creates reservation , long phoneNum, Boolean mem
		if(hasAvailableTable(pax)){

			//get additional info
			System.out.println("can i have ur number");
				long phoneNum = scan.nextLong();
			System.out.println("bro u member?");
				String memS = scan.next(); //y/n
				boolean mem = memS.toLowerCase().charAt(0) == 'y' ;

			System.out.println("Enter date in YYYY-MM-DD format");
			LocalDate date;
			LocalTime time;
			while(true){
				try {
					date = LocalDate.parse(scan.next());
					break;
				}catch (DateTimeParseException e){
					System.out.println("Invalid date");
				}
			}
			while(true){
				try {
					time = LocalTime.parse(scan.next());
					break;
				}catch (DateTimeParseException e){
					System.out.println("Invalid date");
				}
			}

			//create reservation
			rl.addReservation(name,pax, phoneNum, date, time, mem);
		}

		// else say sorry, no reservation obj created
		else{
			System.out.println("No tables with your current pax available atm, try reserving again tomorrow or put ut child at home");
		}
		
		
	}

	static Boolean hasReservation(long rID) {
		return rl.getReservation(rID) != null;
	}

	static void removeReservation(long rID) {
		rl.removeReservation(rID);
	}

	//check table
	static Boolean hasAvailableTable(int pax) {
		return sm.getAvailTable(pax) >= 0;
	}


	//order

	static void createOrder(Staff staff) {
		// check if there's reservation, ie has valid rID, yes-> create order, else deny
		

		//ask for rID
		Scanner scan = new Scanner(System.in);
		System.out.println("What's ur rID?");
		long rID = scan.nextLong();

		if(hasReservation(rID)){
			orders.add(new Order((int)rID, staff, sm.getAvailTable(rl.getReservation(rID).getPax())));//is int or long? rID is long but orderID is int
			sm.assignTable(sm.getAvailTable(rl.getReservation(rID).gettID());
		}
		
	}

	static void viewOrder(int orderID) {
		for(Order oItem : orders){
			if(oItem.getOrderID() == orderID){ //found order item
				oItem.printOrder();
				return;
			}
		}
		System.out.println("Invalid OrderID");
	}

	static void addItemToOrder() {
		System.out.println("Enter orderID");
		int orderID = getInput(0,Integer.MAX_VALUE);
		Order order = orders.get(orderID);
		if(order == null){
			System.out.println("Invalid orderID");
			return;
		}
		System.out.println("1. Mains");
		System.out.println("2. Drinks");
		System.out.println("3. Desserts");
		System.out.println("4. Promotions");
		switch (getInput(1,4)){
			case 1:menu.getMains();break;
			case 2:menu.getDrinks();break;
			case 3:menu.getDesserts();break;
			case 4:menu.getPromotions();break;
		}
		//TODO items inaccessible, cannot continue
	}

	static void removeItemFromOrder() {
		System.out.println("Enter orderID");
		int orderID = getInput(0,Integer.MAX_VALUE);
		Order order = orders.get(orderID);
		if(order == null){
			System.out.println("Invalid orderID");
			return;
		}
		System.out.println("1. Mains");
		System.out.println("2. Drinks");
		System.out.println("3. Desserts");
		System.out.println("4. Promotions");
		switch (getInput(1,4)){
			case 1:menu.getMains();break;
			case 2:menu.getDrinks();break;
			case 3:menu.getDesserts();break;
			case 4:menu.getPromotions();break;
		}
		//TODO items inaccessible, cannot continue
	}

	
	//check out

	static void printOrderInvoice() {
		// TODO - implement RRSPSS.printOrderInvoice
		throw new UnsupportedOperationException();
	}

	//wrapping up

	static void printSaleRevenue() {
		// TODO - implement RRSPSS.printSaleRevenue
		throw new UnsupportedOperationException();
	}

}
