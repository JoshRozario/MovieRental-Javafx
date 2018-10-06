package refactoring.solution5;

public class RentalItem implements RentalComponent {
	Movie movie;
	
	String price;
	
	
	public RentalItem(Movie newMovie,String newPrice){
		movie = newMovie;
		price = newPrice;

	}
	@Override
	public String getMovie(){
		return movie.getTitle();
	}
	
	public double returnPrice(){
		return Double.parseDouble(price);
	}


	
	@Override
	public String getPrice() {
		return "$" + price;
	}



	
	
	
	
	
	

}
