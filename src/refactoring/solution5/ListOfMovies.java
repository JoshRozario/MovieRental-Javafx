package refactoring.solution5;

public class ListOfMovies {

	RentalComponent basket;
	
	public ListOfMovies(RentalComponent newBasket){
		basket = newBasket;
	}
	
	public void getBasket(){
		basket.display();
	}
	
}
