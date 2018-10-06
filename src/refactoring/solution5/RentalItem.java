package refactoring.solution5;

public class RentalItem extends RentalComponent {
	Movie movie;
	
	String price;
	
	
	public RentalItem(Movie newMovie,String newPrice){
		movie = newMovie;
		price = newPrice;

	}
	
	public String getMovieName(){
		return movie.getTitle();
	}

	@Override
	public String display() {
		return movie.getTitle() +" "+  price;
	}
	
	
	

}
