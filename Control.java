public class Control{
	public static void main(String[] args){
		SeatingManagement sm = new SeatingManagement();
		int tID;

		for(int i = 0; i < 20; i++){
			tID = sm.getAvailTable(2);
			sm.reserveATable(tID);
			sm.assignTable(tID);
			System.out.println(sm.getTable(tID).getStatus());
			System.out.println(sm.getTable(tID).getOrder());
			System.out.println(sm.getTable(tID).getTableID());
			System.out.println(sm.getTable(tID).getNumOfSeats());
			System.out.println();
		}
		

	}
}