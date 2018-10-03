package refactoring.solution5;

public class Rental {
	private Movie _movie;
	private int _daysRented;
	
	public Rental(Movie movie, int daysRented) {
		_movie = movie;
		_daysRented = daysRented;
	}
	
	public int getDaysRented() {
		return _daysRented;
	}
	
	public Movie getMovie() {
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