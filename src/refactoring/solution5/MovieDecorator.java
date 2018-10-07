package refactoring.solution5;

public class MovieDecorator implements Movie{

	protected Movie movie;
	
	
	public MovieDecorator(Movie m) {
		this.movie = m;
	}
	
	@Override
	public double getCharge(int _daysRented) {
		return this.movie.getCharge(_daysRented);
	}
	
	

}
