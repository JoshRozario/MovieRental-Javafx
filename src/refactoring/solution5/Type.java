package refactoring.solution5;

abstract interface Type {
	
	double getCharge(int daysRented);
    int getPoints();
    public String toString();
    
}


class New implements Type{

	public int getPoints(){
		return 2;
	}
	
	@Override
	public double getCharge(int daysRented) {
		double price = 5.00;
		if( daysRented > 1) {
			price += (daysRented-1) * 5;	
		}
		return price;
	}
	
	@Override
    public String toString() {
        return "New";
    }
	
}


class Regular implements Type{
	
	public int getPoints(){
		return 1;
	}

	@Override
	public double getCharge(int daysRented) {
		double price = 3.00;
		if (daysRented > 3)
			price += (daysRented - 3) * 1;
		return price;
	}
	
	@Override
    public String toString() {
        return "Regular";
    }
	
}



class Classic implements Type{
	
	public int getPoints(){
		return 3;
	}

	@Override
	public double getCharge(int daysRented) {
		double price = 2.50;
		if (daysRented > 5) {
			price += (daysRented - 5) * 0.50;
		}
		return price;
	}
	
	@Override
    public String toString() {
        return "Classic";
    }
	
	
}