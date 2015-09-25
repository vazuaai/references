package antcolony.simulation;

import geo.Position;
import antcolony.Ant;
import antcolony.FoodSource;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Simulation {

	public static Random random = new Random();		
	public double[][] pheromoneMatrix;				
	private int matrixSize;							
	public int foodDelivered;						
	private ArrayList<Ant> ants;					
	private Position antHill;						
	private Position antPos;
	public FoodSource foodSource;					

	public Simulation(int matrixSize, int antNumber, Position antHill, FoodSource foodSource) {
		
		this.matrixSize = matrixSize;
		this.antHill = antHill;
		this.antPos = antHill;
		this.foodSource = foodSource;
		
		pheromoneMatrix = new double[this.matrixSize][this.matrixSize];
		
		ants = new ArrayList<Ant>();
		
		for(int i = 0; i < antNumber; i++) {		
		
			ants.add(new Ant(this, this.antPos));
			
		}
		
	}
	
	public Position getColonyPosition() {
	
		return this.antHill; 
		
	}
	
	public FoodSource getFoodSource() {
	
		return this.foodSource;
		
	}
	
	public int getSize() {
	
		return this.matrixSize;
		
	}
	
	public List<Ant> getAnts() {
	
		return this.ants;
		
	}
	
	public void putPheromoneOn(Position p, double pheromonAmount) {	
		
		pheromoneMatrix[p.x][p.y] = pheromoneMatrix[p.x][p.y] + pheromonAmount;
		
	}
	
	public double pheromoneOn(Position p) {
		
		double ph = pheromoneMatrix[p.x][p.y];
		
		return ph;
		
	}
	
	//EldĂ¶nti, hogy egy adott pozĂ­ciĂł a szimulĂˇciĂł keretein belĂĽl van-e.
	public boolean inside(Position p) {		
	
		boolean l;		
		
		if(0 <= p.x && p.x < matrixSize && 0 <= p.y && p.y < matrixSize) {
			
			l = true;
			
		}else {
			
			l = false;
			
		}
		
		return l;
	}
	
	//Egy pozĂ­ciĂłt kap Ă©s visszatĂ©r a pĂˇlyĂˇn levĹ‘ Ă©telforrĂˇsra valĂł referenciĂˇval, 
	//ha az az adott mezĹ‘n talĂˇlhatĂł. EgyĂ©bkĂ©nt null-t ad vissza.
	public FoodSource foodPresent(Position p) {
	
		if(p.samePosition(foodSource.getPosition())) {
			
			return foodSource;
			
		}else return null;	
	}
	
	//FĂĽggvĂ©ny minden hangya simulateStep fĂĽggvĂ©nyĂ©t vĂ©grehajtja Ă©s 
	//emellett 0.9-el megszorozza az Ă¶sszes mezĹ‘n talĂˇlhatĂł feromonmennyisĂ©get.
	public void simulateStep() {
	
		for(int i = 0; i < ants.size(); i++) {
		
			ants.get(i).simulateStep();
			
		}
		
		for(int row = 0; row < matrixSize; row++) {
		
			for(int col = 0; col < matrixSize; col++) {
			
				pheromoneMatrix[row][col] = pheromoneMatrix[row][col] * 0.9;
				
			}
		}
	}
	
}











