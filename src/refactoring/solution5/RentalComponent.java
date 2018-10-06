package refactoring.solution5;

import java.util.ArrayList;

public abstract class RentalComponent {

	public void add(RentalComponent newRentalComponent) {
		
		throw new UnsupportedOperationException();
		
	}
	
   public void remove(RentalComponent newRentalComponent) {
		
		throw new UnsupportedOperationException();
		
	}
   
   public void getPrice() {
	   
	   throw new UnsupportedOperationException();
	   
   }

   public String display(){
	   throw new UnsupportedOperationException();
   }
   
   public ArrayList<RentalComponent> showBasket(){
	   
	   throw new UnsupportedOperationException();
	   
   }
   
   public String getMovieName(){
	   throw new UnsupportedOperationException();
   }
   
}
