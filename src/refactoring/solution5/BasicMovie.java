package refactoring.solution5;
/*
 * Moving the getCharge method to the class Movie,
 * Encapsulate Field on the type code to ensure that all uses of the type code go through getting
 * and setting methods. Because most of the code came from other classes, most methods already
 * use the getting method. However, the constructors do access the price code 
 */
public class BasicMovie implements Movie {

	private String title;
	
	public Type movieType;
	
	
	public BasicMovie(String title,Type movieType) {
		this.title = title;
		this.movieType = movieType;
	}
	
	
	public String getTitle (){
		return title;
	}
	
	public double getCharge(int _daysRented) {
		
		return movieType.getCharge(_daysRented);
		
	}
	
	public void setType(Type newMovieType) {
		movieType = newMovieType;
	}


	
	
	public int getFrequentRenterPoints(int _daysRented) {
		return movieType.getPoints();
	}


	@Override
	public String toString() {
		return title;
	}

	
}