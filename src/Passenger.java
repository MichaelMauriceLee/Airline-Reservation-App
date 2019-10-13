/**
 * <h1>ENSF 593 Assignment 2, Task Exercise 3</h1>
 * <p>Defines Passenger class and its instance methods.</p>
 * 
 * @author Michael Lee
 * @version 1.0 2019/09/24
 * 
 */

public class Passenger {

/////////////////////////////////////////////
////Instance Variables
	private String name;
	private Position assignedSeat; // object of type position

/////////////////////////////////////////////
////Constructors
	public Passenger(String name, int row, char seat) {
		this.setName(name);
		setAssignedSeat(new Position(row, seat));
	}

	public Passenger(String name, Position assignedSeat) {
		this.setName(name);
		// this.assignedSeat = assignedSeat;
		// the above will result in two passengers to have
		// the same seat! not acceptable!
		// my original code
		// this.assignedSeat = new Position (assignedSeat);
		// What the IDE produced that does the same thing using the
		// setter function
		this.setAssignedSeat(new Position(assignedSeat));
	}

	public Passenger() {
		this.setName("");
	}

//////////////////////////////////////////////
/////Instance methods
	@Override
	public String toString() {
		return "\nPassenger name: " + name + "\nAssignedSeat:\n" + assignedSeat;
	}

//////////////////////////////////////////////////////
////Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Position getAssignedSeat() { // actually problematic, security wise, need to send a copy
		return assignedSeat;
	}

	public void setAssignedSeat(Position assignedSeat) {// actually problematic, security wise (?)
		this.assignedSeat = assignedSeat;
	}
}
