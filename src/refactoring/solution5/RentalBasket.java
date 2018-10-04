package refactoring.solution5;

import java.util.ArrayList;

public class RentalBasket extends RentalComponent{
	
	ArrayList<RentalComponent> RentalItems = new ArrayList<RentalComponent>();
	
	public RentalBasket() {
		
	}

	public void add(RentalComponent newRentalitem) {
		RentalItems.add(newRentalitem);
	}
	
	public void remove(RentalComponent newRentalitem) {
		RentalItems.remove(newRentalitem);
	}
	
	
	
}
	