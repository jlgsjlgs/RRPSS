import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class RRSPSS {

	//bootstart
	StaffRoster roster = new StaffRoster();
	SeatingManagement sm = new SeatingManagement();
	ReservationList rl = new ReservationList();

	//editing menu

	public void addItem() {
		// TODO - implement RRSPSS.addItem
		throw new UnsupportedOperationException();
	}

	public void removeItem() {
		// TODO - implement RRSPSS.removeItem
		throw new UnsupportedOperationException();
	}

	public void updateItem() {
		// TODO - implement RRSPSS.updateItem
		throw new UnsupportedOperationException();
	}

	//promotion
	public void addPromotional() {
		// TODO - implement RRSPSS.addPromotional
		throw new UnsupportedOperationException();
	}

	public void removePromotional() {
		// TODO - implement RRSPSS.removePromotional
		throw new UnsupportedOperationException();
	}

	public void updatePromotional() {
		// TODO - implement RRSPSS.updatePromotional
		throw new UnsupportedOperationException();
	}

	//reservation

	void createReservation(String name, int pax) {

		Scanner scan = new Scanner(System.in);

		//check if table available
		// if yes then actually creates reservation , long phoneNum, Boolean mem
		if(checkTableAvailability(pax)){

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

	public Boolean checkReservation(long rID) {
		if(rl.getReservation(rID) != null)
			return true;
		else return false;
	}

	public void removeReservation(long rID) {
		if(rl.getReservation(rID) != null)
			rl.removeReservation(rID);
	}

	//check table
	public Boolean checkTableAvailability(int pax) {
		if (sm.getAvailTable(pax) <0 )
			return false;
		else return true;
	}


	//order

	public void createOrder() {
		// check if there's reservation, ie has valid rID, yes-> create order, else deny

		//ask for rID
		Scanner scan = new Scanner(System.in);
		System.out.println("What's ur rID?");
		long rID = scan.nextLong();

		if(checkReservation(rID)){
			//Order(rID, staff, sm.); // to be finished
		}
	}

	public void viewOrder() {
		// TODO - implement RRSPSS.viewOrder
		throw new UnsupportedOperationException();
	}

	public void addItemToOrder() {
		// TODO - implement RRSPSS.addItemToOrder
		throw new UnsupportedOperationException();
	}

	public void removeItemFromOrder() {
		// TODO - implement RRSPSS.removeItemFromOrder
		throw new UnsupportedOperationException();
	}

	
	//check out

	public void printOrderInvoice() {
		// TODO - implement RRSPSS.printOrderInvoice
		throw new UnsupportedOperationException();
	}

	//wrapping up

	public void printSaleRevenue() {
		// TODO - implement RRSPSS.printSaleRevenue
		throw new UnsupportedOperationException();
	}

}
