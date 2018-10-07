package refactoring.solution5;

public class PubHolidayMovie extends MovieDecorator{

	public PubHolidayMovie(Movie m) {
		super(m);
		// TODO Auto-generated constructor stub
		
		
	}

	@Override
	public double getCharge(int _daysRented) {
		return (super.getCharge(_daysRented))*1.10;
	}

}
