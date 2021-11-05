import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.HashMap;

public class RRPSS {

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
			System.out.println("1. Edit Menu Items");
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
					System.out.println("Enter orderID");
					viewOrder(getInput(0,Integer.MAX_VALUE));
					break;
				case 5:
					int orderID = getInput(0,Integer.MAX_VALUE);
					Order order = null;
					for(Order o : orders){
						if(o.getOrderID() == orderID){
							order = o;
							break;
						}
					}
					if(order == null){
						System.out.println("Invalid OrderID");
						break;
					}
					System.out.println("Editing order");
					System.out.println("1. Add item(s)");
					System.out.println("2. Remove item(s)");
					System.out.println("Enter choice");
					switch (getInput(1,2)){
						case 1: addItemToOrder(order);break;
						case 2: removeItemFromOrder(order);break;
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
							removeReservation(rID);
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
					orderID = getInput(0,Integer.MAX_VALUE);
					order = null;
					for(Order o : orders){
						if(o.getOrderID() == orderID){
							order = o;
							break;
						}
					}
					if(order == null){
						System.out.println("Invalid OrderID");
						break;
					}
					printOrderInvoice(order);
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
			Reservation reservation = rl.getReservation(rID);
			int table = sm.getAvailTable(reservation.getPax());
			orders.add(new Order(rID, staff, table));//is int or long? rID is long but orderID is int
			sm.assignTable(table);
			reservation.settID(table);
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

	static void addItemToOrder(Order order) {
		HashMap<String, ArrayList<MenuItem>> tempHash = menu.getHashMenu();
		ArrayList<Promotional> tempPromo = menu.getPromoArrayList();
		boolean stillAdding = true;
		int userInput, numitems=0;
		Scanner sc = new Scanner(System.in);

		while (stillAdding){
			System.out.println("1. Mains");
			System.out.println("2. Drinks");
			System.out.println("3. Desserts");
			System.out.println("4. Promotions");
			System.out.println("5. Exit");

			switch (getInput(1,4)){
				case 1:
					menu.getMains();
					System.out.println("Which item do you want to add?");
					userInput = Integer.valueOf(sc.nextLine());
					if (!(userInput <= 0 || userInput > menu.getNumMains())){
						System.out.println("Enter quantity");
						numitems = Integer.valueOf(sc.nextLine());
						order.addItemToOrder(tempHash.get("MainCourse").get(userInput-1), numitems);
						System.out.println("MainCourse added to order!");
					} else {
						System.out.println("Error! MainCourse does not exist.");
					}
					break;
				case 2:
					menu.getDrinks();
					System.out.println("Which item do you want to add?");
					userInput = Integer.valueOf(sc.nextLine());
					if (!(userInput <= 0 || userInput > menu.getNumDrinks())){
						System.out.println("Enter quantity");
						numitems = Integer.valueOf(sc.nextLine());
						order.addItemToOrder(tempHash.get("Drink").get(userInput-1), numitems);
						System.out.println("Drink added to order!");
					} else {
						System.out.println("Error! Drink does not exist.");
					}
					break;
				case 3:
					menu.getDesserts();
					System.out.println("Which item do you want to add?");
					userInput = Integer.valueOf(sc.nextLine());
					if (!(userInput <= 0 || userInput > menu.getNumDessert())){
						System.out.println("Enter quantity");
						numitems = Integer.valueOf(sc.nextLine());
						order.addItemToOrder(tempHash.get("Dessert").get(userInput-1), numitems);
						System.out.println("Dessert added to order!");
					} else {
						System.out.println("Error! Dessert does not exist.");
					}
					break;
				case 4:
					menu.getPromotions();
					System.out.println("Which item do you want to add?");
					userInput = Integer.valueOf(sc.nextLine());
					if (!(userInput <= 0 || userInput > tempPromo.size())){
						System.out.println("Enter quantity");
						numitems = Integer.valueOf(sc.nextLine());
						order.addItemToOrder(tempPromo.get(userInput-1), numitems);
						System.out.println("Item added to order!");
					} else {
						System.out.println("Error! Item does not exist.");
					}
					break;
				case 5: stillAdding = false; break;
			}
		}
	}

	static void removeItemFromOrder(Order order) {
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

	static void printOrderInvoice(Order order) {
		System.out.println("RESTAURANT NAME");
		System.out.println("-----------------");
		System.out.println("Server: "+order.getStaff()+"\t\t"+LocalDate.now().toString());
		System.out.println("Table: "+order.getTableID()+"\t\t"+LocalTime.now().toString());
		System.out.println("-----------------");
		for(OrderItem item : order.getMyOrder()){
			System.out.println(item.getOrderQuantity()+"\t"+item.getOrderName()+"\t\t"+item.price());
		}
		System.out.println("-----------------");
		System.out.println("\t\tSubtotal:\t"+order.totalPrice());
		System.out.println("\t\tGST:\t"+order.totalPrice()*0.07);
		System.out.println("\t\tTotal:\t"+order.totalPrice()*1.07);
		System.out.println("-----------------");
	}

	//wrapping up

	static void printSaleRevenue() {
		// TODO - implement RRSPSS.printSaleRevenue
		throw new UnsupportedOperationException();
	}

}
