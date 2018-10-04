package refactoring.solution5;

public class RentalItem extends RentalComponent {
	Movie movie;
	
	double price;
	
	
	public RentalItem(Movie newMovie){
		movie = newMovie;
		
	}
	
	public String getMovieName(){
		return movie.getTitle();
	}

	public Double calculatePrice(int daysRented){
		price = movie.getCharge(daysRented);
		//add discount
		
		return price;
		
	}
}
