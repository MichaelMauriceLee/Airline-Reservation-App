/**
 * <h1>ENSF 593 Assignment 2, Task Exercise 3</h1>
 * <p>Defines Position class and its instance methods.</p>
 * 
 * @author Michael Lee
 * @version 1.0 2019/09/24
 * 
 */

public class Position {
/////////////////////////////////////////////
////Instance Variables

	private int row;
	private char seat;

/////////////////////////////////////////////
////Constructors
	public Position(int row, char seat) {
		setRow(row); // could have said: this.row = row;
		setSeat(seat);
	}

	// This constructor is called a copy constructor!
	// This receives an object of type position, and creates a
	// deep copy for the this object
	public Position(Position p) {
		if (p != null) {
			setRow(p.row); // could have said: this.row = p.row;
			setSeat(p.seat);
		}
	}

	public Position() {
		this.setRow(0);
		this.setSeat('0');
	}

//////////////////////////////////////////////////////
////Getters and Setters
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public char getSeat() {
		return seat;
	}

	public void setSeat(char seat) {
		this.seat = seat;
	}

	public String toString() {
		return "Row: " + row + ", and seat: " + seat;
	}

}
