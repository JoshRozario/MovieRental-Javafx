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

	
	public String getPrices() {
		double tPrice = 0;
		for (RentalComponent d: RentalItems) {
			RentalItem x = (RentalItem) d;
			tPrice += x.returnPrice();
			
		}
		String test = String.format("%.2f", tPrice);
		if(RentalItems.size()>4) {
			tPrice = tPrice*0.9	;		
			test = test + ", 10% discount added!";
		}
		
		return "$" + test;
	}

	@Override
	public String getMovie() {
		// TODO Auto-generated method stub
		return "pingas";
	}

	@Override
	public String getPrice() {
		// TODO Auto-generated method stub
		return "WRONG CLASS";
	}


	
	
	
	
	
}
	