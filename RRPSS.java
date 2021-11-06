import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.HashMap;
import java.time.format.DateTimeFormatter;
import java.text.NumberFormat;

public class RRPSS {

	//bootstart
	private static final Menu menu = new Menu();
	private static final StaffRoster roster = new StaffRoster();
	private static final SeatingManagement sm = new SeatingManagement();
	private static final ReservationList rl = new ReservationList();
	private static final ArrayList<Order> orders = new ArrayList<>();
	private static final HashMap<OrderItem, Integer> salesTracker = new HashMap<>();


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
		Scanner sc = new Scanner(System.in);
		roster.showStaff();
		System.out.println("Identify yourself");
		int id = getInput(1,roster.availStaff.size());
		Staff staff = roster.availStaff.get(id-1);
		System.out.println("Welcome, "+staff.getName()+" ("+staff.getJobTitle()+")");
		while(true) {
			System.out.println();
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
			System.out.println("12. printitemsinmenu");
			System.out.println("Enter choice");
			switch (getInput(1,12)){
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
					System.out.println("Enter order ID");
					viewOrder(getInput(0,Integer.MAX_VALUE));
					break;
				case 5:
					System.out.println("Enter reservation ID");
					int orderID = getInput(0,Integer.MAX_VALUE);
					Order order = null;
					for(Order o : orders){
						if(o.getOrderID() == orderID){
							order = o;
							break;
						}
					}
					if(order == null){
						System.out.println("Invalid Order ID");
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
					int pax = getInput(1,6);
					if(hasAvailableTable(pax) != -1){
						createReservation(name,pax);
					} else {
						System.out.println("No available table");
					}
					break;
				case 7:
					//edit reservation
					System.out.println("What's ur reservation ID?");
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
					System.out.println(hasAvailableTable(getInput(1,6)) != -1 ? "Table available" : "No available table");
					break;
				case 9:
					System.out.println("Enter reservation ID: ");
					orderID = getInput(0,Integer.MAX_VALUE);
					order = null;
					for(Order o : orders){
						if(o.getOrderID() == orderID){
							order = o;
							break;
						}
					}
					if(order == null){
						System.out.println("Invalid Order ID");
						break;
					}
					printOrderInvoice(order);
					break;
				case 10:
					printSaleRevenue();
					break;
				case 11:
					System.exit(0);
					return;
				case 12:
					menu.getMains();
					menu.getDrinks();
					menu.getDesserts();
					menu.getPromotions();
					break;
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
		int curTableID= hasAvailableTable(pax);

		if(curTableID != -1){

			//get additional info
			System.out.println("Please enter your phone number: ");
			long phoneNum = scan.nextLong();
			System.out.println("Are you a member?");
			String memS = scan.next(); //y/n
			boolean mem = memS.toLowerCase().charAt(0) == 'y' ;

			// System.out.println("Enter date in YYYY-MM-DD format");
			// LocalDate date;
			// LocalTime time;
			// while(true){
			// 	try {
			// 		date = LocalDate.parse(scan.next());
			// 		break;
			// 	}catch (DateTimeParseException e){
			// 		System.out.println("Invalid date");
			// 	}
			// }
			// System.out.println("Enter time in HH:MM:SS format");
			// while(true){
			// 	try {
			// 		time = LocalTime.parse(scan.next());
			// 		break;
			// 	}catch (DateTimeParseException e){
			// 		System.out.println("Invalid time");
			// 	}
			// }

			//create reservation
			LocalDate date = LocalDate.parse("2021-11-07");
			LocalTime time = LocalTime.parse("13:00:00");
			rl.addReservation(name,pax, phoneNum, date, time, mem, curTableID);

			sm.reserveATable(curTableID);
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
		Reservation tempReservation = rl.getReservation(rID);
		rl.removeReservation(rID);
		sm.unassignTable(tempReservation.gettID());
	}

	//check table
	static int hasAvailableTable(int pax) {
		return sm.getAvailTable(pax);
	}


	//order

	static void createOrder(Staff staff) {
		// check if there's reservation, ie has valid rID, yes-> create order, else deny
		

		//ask for rID
		Scanner scan = new Scanner(System.in);
		System.out.println("What's ur rID?");
		long rID = Long.valueOf(scan.nextLine());

		if(hasReservation(rID)){
			Reservation reservation = rl.getReservation(rID);
			int table = sm.getAvailTable(reservation.getPax());
			orders.add(new Order(rID, staff, table, reservation.getMembership()));//is int or long? rID is long but orderID is int
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

			switch (getInput(1,5)){
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
		HashMap<String, ArrayList<MenuItem>> tempHash = menu.getHashMenu();
		ArrayList<Promotional> tempPromo = menu.getPromoArrayList();
		boolean stillRemoving = true;
		int userInput, numitems=0;
		Scanner sc = new Scanner(System.in);

		if (order.getOrderSize() == 0){
			System.out.println("Order is empty!");
			return;
		}

		while (stillRemoving){
			System.out.println("1. Mains");
			System.out.println("2. Drinks");
			System.out.println("3. Desserts");
			System.out.println("4. Promotions");
			System.out.println("5. Exit");
			switch (getInput(1,5)){
				case 1:
					menu.getMains();
					System.out.println("Which item do you want to remove?");
					userInput = Integer.valueOf(sc.nextLine());
					if (!(userInput <= 0 || userInput > menu.getNumMains())){
						order.removeItemFromOrder(tempHash.get("MainCourse").get(userInput-1));
						System.out.println("MainCourse removed from order!");
					} else {
						System.out.println("Error! MainCourse does not exist.");
					}
					break;
				case 2:
					menu.getDrinks();
					System.out.println("Which item do you want to add?");
					userInput = Integer.valueOf(sc.nextLine());
					if (!(userInput <= 0 || userInput > menu.getNumDrinks())){
						order.removeItemFromOrder(tempHash.get("Drink").get(userInput-1));
						System.out.println("Drink removed from order!");
					} else {
						System.out.println("Error! Drink does not exist.");
					}
					break;
				case 3:
					menu.getDesserts();
					System.out.println("Which item do you want to add?");
					userInput = Integer.valueOf(sc.nextLine());
					if (!(userInput <= 0 || userInput > menu.getNumMains())){
						order.removeItemFromOrder(tempHash.get("Dessert").get(userInput-1));
						System.out.println("Dessert removed from order!");
					} else {
						System.out.println("Error! Dessert does not exist.");
					}
					break;
				case 4:
					menu.getPromotions();
					System.out.println("Which item do you want to add?");
					userInput = Integer.valueOf(sc.nextLine());
					if (!(userInput <= 0 || userInput > tempPromo.size())){
						order.removeItemFromOrder(tempPromo.get(userInput-1));
						System.out.println("Promotional item removed from order!");
					} else {
						System.out.println("Error! Promotional item does not exist.");
					}
					break;
				case 5: stillRemoving = false; break;
			}
		}
	}

	
	//check out

	static void printOrderInvoice(Order order) {
		System.out.println("---------------------------------------------------------------");
		System.out.println("RESTAURANT NAME");
		System.out.println("---------------------------------------------------------------");
		System.out.println("Server: "+((Staff)order.getStaff()).getName()+"\t\t"+LocalDate.now());
		System.out.println("Table: "+order.getTableID()+"\t\t\t\t"+LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
		for(OrderItem item : order.getMyOrder()){
			order.printOrder();
			addToDailySales(item);
		}
		if (order.getMembership()){
			System.out.println("10% discount given for membership!");
		}
		System.out.println("\tSubtotal:\t"+ NumberFormat.getCurrencyInstance().format(order.totalPrice()));
		System.out.println("\tService Charge:\t"+ NumberFormat.getCurrencyInstance().format(order.totalPrice()*0.1));
		System.out.println("\tGST:\t\t"+ NumberFormat.getCurrencyInstance().format((order.totalPrice()*1.1)*0.07));
		System.out.println("\tTotal:\t\t"+ NumberFormat.getCurrencyInstance().format((order.totalPrice()*0.1+ order.totalPrice()*1.1*0.07+order.totalPrice())));
		System.out.println("---------------------------------------------------------------");
	}

	//wrapping up

	static void printSaleRevenue() {
		double total=0;
		System.out.println("Sales Revenue Report for " + LocalDate.now());
		System.out.println("---------------------------------------------------------------");

		for (OrderItem tempItem : salesTracker.keySet()){
			System.out.println(tempItem.getOrderName() + "\t\t"+ salesTracker.get(tempItem));
		}

		for (Order tempOrder : orders){
			total += tempOrder.totalPrice();
		}
		System.out.println("---------------------------------------------------------------");
		System.out.println("Total revenue: " + NumberFormat.getCurrencyInstance().format(total));		
	}

	static void addToDailySales(OrderItem curItem){
		if (!(salesTracker.containsKey(curItem))){
			salesTracker.put(curItem, curItem.getOrderQuantity());
		} else {
			int existingNum = salesTracker.get(curItem);
			salesTracker.put(curItem, curItem.getOrderQuantity() + existingNum);
		}
	}
}
