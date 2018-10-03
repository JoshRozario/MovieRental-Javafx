package refactoring.solution5;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	
    private String name;
    private List<Rental> rentals;
    
    public Customer (String name) {
        this.name = name;
        this.rentals = new ArrayList<>();
    }
	
	public void addRental(Rental rental) {
		rentals.add(rental);
	}
	
	public String getName (){
		return name;
	}
	
	public String statement() {
		
		String result = "Rental Record for " + getName() + "\n";
		
		result = printDetails(result);
		

		int frequentRenterPoints = applyRenterPoints();			
			
		//add footer lines
		result += "Amount owed is " + String.valueOf(getTotalCharge()) +	"\n";
		// add footer line for frequent-renter points
		result += "You earned " + String.valueOf(frequentRenterPoints) 	+ " frequent renter points";
		return result;
	}

	private String printDetails(String result) {
		for (Rental r: rentals) {
			result += "\t" + r.getMovieTitle() + "\t" + 	String.valueOf(r.getCharge()) + "\n"; //removed dot overuse
		}
		return result;
	}

	private int applyRenterPoints() {
		int frequentRenterPoints = 0;
		for (Rental r: rentals) {
			  frequentRenterPoints += r.getMoviePoints(r.getDaysRented()); //removed complicated if statement and overuse of dots
		}
		return frequentRenterPoints;
	}


	public String htmlStatement() {
		String result = "";
		return result;
	}
	

	// Two options: Sometimes leave the old method to delegate to the old method.  This is useful if it is a public
	// method 
	

	
	private double getTotalCharge() {
		double result = 0;
		for (Rental r: rentals) {
			result += r.getCharge();
		}
		return result;
	}
	
	

}
