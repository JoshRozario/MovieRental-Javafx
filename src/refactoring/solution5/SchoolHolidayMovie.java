package refactoring.solution5;

public class SchoolHolidayMovie extends MovieDecorator{

	public SchoolHolidayMovie(Movie m) {
		super(m);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getCharge(int _daysRented) {
		return (super.getCharge(_daysRented))*0.75;
	}
    
}
