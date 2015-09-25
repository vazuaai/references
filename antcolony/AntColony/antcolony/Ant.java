package antcolony;

import geo.Position;
import antcolony.simulation.Simulation;
import java.util.LinkedList;

public class Ant {
	
	private Position position; 					
	private boolean foodPresent;				
	public double excitement;					
	private int stepNumber;						
	private Simulation simulationIn;			
	private static int TIME_TO_GO_HOME = 15;	
		
		
	public Ant(Simulation simulation, Position antPos) {
	
		this.simulationIn = simulation;
		this.position = new Position(antPos.x, antPos.y);
		this.excitement = 1.0;
		
	}
	
	public Position getPosition() {
	
		return this.position;
		
	}
	
	public boolean isCarryingFood() {
	
		return this.foodPresent;
		
	}
	
	public void moveTowardsColony() {
		
		if(foodPresent == true) {
		
			simulationIn.putPheromoneOn(position, excitement);	
		}
		
		excitement = excitement * 0.9;
		
		this.position.towards(simulationIn.getColonyPosition());					
		
		
	
	}
	
	public void wander() {
		
		LinkedList<Position> ll = position.neighbours(simulationIn);
		
		double rnd = simulationIn.random.nextDouble(); 

		
		double max = simulationIn.pheromoneOn(ll.get(0)) + rnd;
		Position maxPos = ll.get(0);
		
		for(int i = 1; i < ll.size(); i++){
			
			double rnd1 = simulationIn.pheromoneOn(ll.get(i)) + simulationIn.random.nextDouble();
		
	
			
			if(rnd1 > max){

				max = rnd1;
				maxPos = ll.get(i);
			}
		}
		
		for(int i = 0; i < ll.size(); i++) {		
			if(ll.get(i) == maxPos){		
				position.towards(ll.get(i));

			}
		}
		
	}
	
	public void simulateStep() {
				
		if(this.foodPresent == true && this.position.samePosition(simulationIn.getColonyPosition())) {
			
			this.foodPresent = false;
			simulationIn.foodDelivered = simulationIn.foodDelivered + 1;
			this.excitement = 0.0;
			this.stepNumber = 0;
			
		}else if(this.position.samePosition(simulationIn.getColonyPosition())) {
			
			this.excitement = 0.0;
			this.stepNumber = 0;
			
		}
		
		if(this.foodPresent == true || this.stepNumber == this.TIME_TO_GO_HOME) {
			
			this.moveTowardsColony();
			
		}else if(simulationIn.foodPresent(this.position) != null && simulationIn.foodSource.takeFood() == true){
			
			this.excitement = 1.0;
			foodPresent = true;
			
		}else wander();
		
		
	}
	
}
