package antcolony;

import geo.Position;

public class FoodSource {	
	
	private int amount;
	private Position position;
		
	public FoodSource(int amount, Position position) {
		
		this.amount = amount;
		this.position = position;
		
	}	
	
	public Position getPosition() {
		
		return this.position;
		
	}
	
	public boolean takeFood() {
		
		boolean l;
		
		if(this.amount > 0) {			
			
			this.amount = this.amount - 1;
			l = true;
			
		}else {
			
			l = false;
			
		}
		
		return l;
	}
	
}
