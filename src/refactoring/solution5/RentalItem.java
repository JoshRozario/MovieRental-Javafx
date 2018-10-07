package refactoring.solution5;

public class RentalItem implements RentalComponent {
	BasicMovie movie;
	
	String price;
	
	
	public RentalItem(BasicMovie newMovie,String newPrice){
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
