/**
 * <h1>ENSF 593 Assignment 2, Task Exercise 3</h1>
 * <p>Defines Airplane class and its instance methods.</p>
 * 
 * @author Michael Lee
 * @version 1.0 2019/09/24
 * 
 */

public class Airplane {

/////////////////////////////////////////////
////Instance Variables
	private Passenger[] passArray;// list of passengers on the plane
	private char[][] seatMap;
	private int numOfPassengers;
	private final int numOfRows;

/////////////////////////////////////////////
////Constructors
	public Airplane(int row) {
		setNumOfPassengers(0);
		numOfRows = row;
		passArray = new Passenger[1];// array of objects of size 1
		passArray[0] = new Passenger();// initialize to default constructor
		seatMap = new char[row][5]; // assuming 5 seats per row
		populateSeatMap();
	}

//////////////////////////////////////////////
/////Instance methods
	// this method takes a passenger name, seat and row information
	// This method must check to see if the position is available and the passenger is not on the flight already. If the position
	// is available and the passenger hasn't been added yet, then the passenger is added to the passArray, and
	// an X is recorded in the seat map in that position and the number of
	// passengers increase.
	// If the position is not available, the method returns false
	public boolean addPassenger(String passName, int row, char seat) {

		
		if (searchPassenger(passName) == true) {
			System.out.println(passName + " is already on the flight.  One passenger can only reserve one seat.");
			return false;
		}
		if (markSeatMap(row, seat) == false)
			return false;
		Passenger p = new Passenger(passName, row, seat);
		if (numOfPassengers == 0) {
			passArray[0] = p;
		} else {
			appendPassenger(p);
		}

		incrementNumOfPassengers();

		return true;
	}

	// this method takes in a passenger name and checks to see if the name is an
	// actual passenger
	// if the name is an actual passenger, seatMap, passArray, and number of
	// passengers
	// will be updated in order to remove the passenger and returns true if
	// successful
	// otherwise returns false
	public boolean removePassenger(String passName) {
		if (searchPassenger(passName) == true) {
			int index = searchPassengerIndex(passName);
			unmarkSeatMap(passArray[index].getAssignedSeat().getRow(), passArray[index].getAssignedSeat().getSeat());
			removeAPassenger(passArray[index], index);
			decrementNumOfPassengers();
			return true;
		}
		return false;
	}

	// searches for the passenger in the array and returns true if its found,
	// otherwise returns false
	public boolean searchForPassenger(String passName) {
		return searchPassenger(passName);
	}

	// This is a wrapper method, that helps us abide to the guideline which state:
	// public methods must NOT call other public methods.
	public boolean checkSeatAvailability(int row, char seat) {
		return checkAvailability(row, seat);
	}

	// Method that prints the seatMap to the console
	public void showSeatMap() {
		System.out.println("Seat Map: ");
		for (int i = 0; i < seatMap.length; i++) {
			for (int j = 0; j < seatMap[i].length; j++) {
				System.out.print(seatMap[i][j] + " ");
			}
			System.out.println();
		}
	}

	@Override
	public String toString() {
		String st = "";
		for (int i = 0; i < passArray.length; i++) {
			// passArray[i] is a reference (i.e. pointer) to a Passenger Object.
			// The line: st += passArray[i]; makes a call to the toString method of
			// class Passenger. And, the toString() in class Passenger, makes
			// a call to the toString in class Position
			st += passArray[i];
			st += "\n\n";
		}
		return st;
	}

///////////////////////////////////////////////////////
///Helper methods
	private void appendPassenger(Passenger p) {
		// step 1: create new memory
		Passenger[] temp = new Passenger[passArray.length + 1];
		// step 2: copy
		for (int i = 0; i < passArray.length; i++)
			temp[i] = passArray[i];
		// step 3: append new object
		temp[temp.length - 1] = p;
		// step 4: reassign passArray
		passArray = temp;
	}

	private void removeAPassenger(Passenger p, int index) {
		if (passArray.length == 1) {
			Passenger[] temp = new Passenger[1];
			temp[0] = new Passenger ();
			passArray = temp;
		}
		else {
			// step 1: create new memory
			Passenger[] temp = new Passenger[passArray.length - 1];
			// step 2: copy up to old passenger's index
			for (int i = 0; i < index; i++)
				temp[i] = passArray[i];
			// step 3: copy rest of array past the old passenger
			for (int i = index; i < temp.length; i++)
				temp[i] = passArray[i + 1];
			// step 4: reassign passArray
		passArray = temp;
		}
	}

	private void populateSeatMap() { // used to initialize the seatmap
		for (int i = 0; i < seatMap.length; i++) {
			for (int j = 0; j < seatMap[i].length; j++) {
				seatMap[i][j] = 'O';
			}
		}

	}

	// searches for the passenger in the array and returns true if its found,
	// otherwise returns false
	private boolean searchPassenger(String passName) {
		int index = searchPassengerIndex(passName);
		if (index >= 0) {
			return true;
		}
		return false;
	}

	// searches for the passenger in the array and returns >=0 if true, otherwise
	// returns false
	private int searchPassengerIndex(String passName) {
		int index = -1;
		for (int i = 0; i < passArray.length; i++) {
			if (passArray[i].getName().contentEquals(passName)) {
				index = i;
			}

		}
		return index;

	}

	// checks to ensure that the requested seat is valid and available
	private boolean checkAvailability(int row, char seat) {
		if ((row >= numOfRows) || (row < 0)) {
			return false;
		}
		if ((seat > 'E') || (seat < 'A')) {
			return false;
		}
		if (seatMap[row][seat - 'A'] == 'O') {
			return true;
		}
		return false;
	}

	private void incrementNumOfPassengers() {
		numOfPassengers++;
	}

	private void decrementNumOfPassengers() {
		numOfPassengers--;
	}

	private boolean markSeatMap(int row, char seat) {
		// if seat is not available, return false
		if (checkAvailability(row, seat) == false)
			return false;
		seatMap[row][seat - 'A'] = 'X';
		return true;
	}

	private boolean unmarkSeatMap(int row, char seat) {
		// if seat is not available, unmark and return true
		if (checkAvailability(row, seat) == false) {
			seatMap[row][seat - 'A'] = 'O';
			return true;
		}
		return false;
	}

//////////////////////////////////////////////////////
////Getters and Setters
	public int getNumOfPassengers() {
		return numOfPassengers;
	}

	public void setNumOfPassengers(int numOfPassengers) {
		this.numOfPassengers = numOfPassengers;
	}

	public int getNumOfRows() {
		return numOfRows;
	}

	public int getNumOfColumns() {
		return seatMap[0].length;
	}
}
