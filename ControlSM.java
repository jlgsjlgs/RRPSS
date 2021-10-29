public class ControlSM{
	public static void main(String[] args){
		SeatingManagement sm = new SeatingManagement();
		int tID;


		for(int i = 0; i < 20; i++){
			tID = sm.getAvailTable(4);
			if (tID >=1 ){
				sm.reserveATable(tID);
				System.out.println(sm.getTable(tID).getStatus());
				System.out.println(sm.getTable(tID).getOrder());
				System.out.println(sm.getTable(tID).getTableID());
				System.out.println(sm.getTable(tID).getNumOfSeats());
				System.out.println();
		}

			
		}
	}
}
