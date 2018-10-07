package refactoring.solution5;

public class Rental {
	private BasicMovie _movie;
	private int _daysRented;
	
	public Rental(BasicMovie movie, int daysRented) {
		_movie = movie;
		_daysRented = daysRented;
	}
	
	public int getDaysRented() {
		return _daysRented;
	}
	
	public BasicMovie getMovie() {
		return _movie;
	}

	double getCharge() {
		return _movie.getCharge(_daysRented);
	}
	
	public int getMoviePoints(int _daysRented) {
		return _movie.getFrequentRenterPoints(_daysRented);
	}
	public String getMovieTitle() {
		return _movie.getTitle();
	}

}
