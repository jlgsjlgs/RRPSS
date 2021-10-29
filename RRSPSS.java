import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

class RRSPSS {

	void addItem() {
		// TODO - implement RRSPSS.addItem
		throw new UnsupportedOperationException();
	}

	void removeItem() {
		// TODO - implement RRSPSS.removeItem
		throw new UnsupportedOperationException();
	}

	void updateItem() {
		// TODO - implement RRSPSS.updateItem
		throw new UnsupportedOperationException();
	}

	void addPromotional() {
		// TODO - implement RRSPSS.addPromotional
		throw new UnsupportedOperationException();
	}

	void removePromotional() {
		// TODO - implement RRSPSS.removePromotional
		throw new UnsupportedOperationException();
	}

	void updatePromotional() {
		// TODO - implement RRSPSS.updatePromotional
		throw new UnsupportedOperationException();
	}

	void createOrder() {
		// TODO - implement RRSPSS.createOrder
		throw new UnsupportedOperationException();
	}

	void viewOrder() {
		// TODO - implement RRSPSS.viewOrder
		throw new UnsupportedOperationException();
	}

	void addItemToOrder() {
		// TODO - implement RRSPSS.addItemToOrder
		throw new UnsupportedOperationException();
	}

	void removeItemFromOrder() {
		// TODO - implement RRSPSS.removeItemFromOrder
		throw new UnsupportedOperationException();
	}

	void createReservation() {
		Scanner scan = new Scanner(System.in);
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
	}

	void checkReservationa() {
		// TODO - implement RRSPSS.checkReservationa
		throw new UnsupportedOperationException();
	}

	void removeReservation() {
		// TODO - implement RRSPSS.removeReservation
		throw new UnsupportedOperationException();
	}

	void checkTableAvailability() {
		// TODO - implement RRSPSS.checkTableAvailability
		throw new UnsupportedOperationException();
	}

	void printOrderInvoice() {
		// TODO - implement RRSPSS.printOrderInvoice
		throw new UnsupportedOperationException();
	}

	void printSaleRevenue() {
		// TODO - implement RRSPSS.printSaleRevenue
		throw new UnsupportedOperationException();
	}

}
