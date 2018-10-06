package refactoring.solution5;

import java.util.ArrayList;

public class RentalBasket implements RentalComponent{
	
	ArrayList<RentalComponent> RentalItems = new ArrayList<RentalComponent>();
	
	public RentalBasket() {
		
	}

	public void add(RentalComponent newRentalitem) {
		RentalItems.add(newRentalitem);
	}
	
	public void remove(RentalComponent newRentalitem) {
		RentalItems.remove(newRentalitem);
	}
	
	
	public ArrayList<RentalComponent> showBasket() {
		return RentalItems;
		
	}

	@Override
	public String getPrice() {
		double tPrice = 0;
		for (RentalComponent d: RentalItems) {
			RentalItem x = (RentalItem) d;
			tPrice += x.returnPrice();
			
		}
		return Double.toString(tPrice);
	}

	@Override
	public String getMovie() {
		// TODO Auto-generated method stub
		return "pingas";
	}


	
	
	
	
	
}
	