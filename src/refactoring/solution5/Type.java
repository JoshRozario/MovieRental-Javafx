package refactoring.solution5;

public interface Type {
	
	double getCharge(int _daysRented);
    int getPoints();
}

class Regular implements Type{
	
	public int getPoints(){
		return 1;
	}

	@Override
	public double getCharge(int _daysRented) {
		double thisAmount = 0;
		thisAmount += 2;
		if (_daysRented > 2)
			thisAmount += (_daysRented - 2) * 1.5;
		return thisAmount;
	}
	
}

class NewRelease implements Type{

	public int getPoints(){
		return 2;
	}
	
	@Override
	public double getCharge(int _daysRented) {
		double thisAmount = 0;
		thisAmount += _daysRented * 3;
		return thisAmount;
	}
	
}

class Childrens implements Type{

	public int getPoints(){
		return 1;
	}
	
	@Override
	public double getCharge(int _daysRented) {
		double thisAmount = 1.5;
		if (_daysRented > 3)
			thisAmount += (_daysRented - 3) * 1.5;
		return thisAmount;
	}
	
}

class Classic implements Type{
	
	public int getPoints(){
		return 3;
	}

	@Override
	public double getCharge(int _daysRented) {
		double thisAmount = 0;
		if (_daysRented > 5) {
			thisAmount = _daysRented - 5;
		}
		return thisAmount;
	}
	
	
}