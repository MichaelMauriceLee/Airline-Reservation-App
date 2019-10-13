
/**
 * <h1>ENSF 593 Assignment 2, Task Exercise 3</h1>
 * <p>Main function and frontend of airline reservation app.</p>
 * 
 * 
 * The {@code main} is the main function of the airline reservation app.
 * 
 * @author Michael Lee
 * @version 1.0 2019/09/24
 * 
 */

import java.util.Scanner;

public class AirlineReservationApp {

/////////////////////////////////////////////
////Class Variable
	private static Scanner userInput = new Scanner(System.in);

/////////////////////////////////////////////
////Main Function
	public static void main(String[] args) {

		Airplane a1 = new Airplane(4);

		while (true) {
			String selection = printMainMenu();
			switch (selection) {
			case "1":
				addPassengerMenu(a1);
				break;
			case "2":
				removePassengerMenu(a1);
				break;
			case "3":
				searchPassengerMenu(a1);
				break;
			case "4":
				showSeatMapMenu(a1);
				break;
			case "5":
				showPassArrayMenu(a1);
				break;
			case "6":
				showFlightCapacityMenu(a1);
				break;
			case "7":
				showExitMessage();
				userInput.close();
				System.exit(0);
			default:
				showErrorMessage();
				break;
			}
		}
	}

///////////////////////////////////////////////////////
///Helper methods
	private static String printMainMenu() {
		// output welcome messages and accepts user input and saves as a string
		System.out.println(
				"\nWelcome to the Official Air Michael Airline Booking Reservation App!\nPlease make a selection:");
		System.out.println("1: Add passenger to the flight");
		System.out.println("2: Remove passenger from the flight");
		System.out.println("3: Search for passenger by name");
		System.out.println("4: Show seat map for this flight");
		System.out.println("5: Show list of passengers on this flight");
		System.out.println("6: Show current and max capacity for this flight");
		System.out.println("7: Exit menu");
		String selection = userInput.nextLine();
		return selection;
	}

	private static void addPassengerMenu(Airplane a1) {
		// prompts and accepts user input and tries to add passenger to seat
		// displays success or failure messages after trying to add the passenger
		System.out.println("What is the name of the passenger to be added?");
		String passName = userInput.nextLine();
		int highestRowNumber = a1.getNumOfRows() - 1;
		System.out.println("Which row (0-" + highestRowNumber + ")?");
		int row = receiveRowNumberInput(userInput);
		System.out.println("Which seat (A-E)?");
		String temp = userInput.nextLine();
		temp = temp.toUpperCase();
		char seat = temp.charAt(0);
		if (a1.addPassenger(passName, row, seat) == true) {
			System.out.println("Successfully added passenger to the flight");
		} else {
			System.out.println("Invalid entry or requested seat has already been occupied.  "
					+ "Please check inputs and/or choose a different seat");
		}
	}

	private static void removePassengerMenu(Airplane a1) {
		// prompts and accepts user input and tries to remove passenger
		// displays success or failure messages after trying to remove the passenger
		System.out.println("What is the name of the passenger you would like to remove?");
		String passName = userInput.nextLine();
		if (a1.removePassenger(passName) == true) {
			System.out.println(passName + " is removed");
		} else {
			System.out.println(
					"Unable to remove.  Please double check spelling and ensure this person is on the flight.");
		}
	}

	private static void searchPassengerMenu(Airplane a1) {
		// prompts and accepts user input and tries to find the passenger
		// displays success or failure messages
		System.out.println("What is the name of the passenger you would like to look for?");
		String passName = userInput.nextLine();
		if (a1.searchForPassenger(passName) == true) {
			System.out.println(passName + " is on this flight");
		} else {
			System.out.println(passName + " is not on this flight");
		}
	}

	private static void showPassArrayMenu(Airplane a1) {
		// output elements in the array of Passengers
		System.out.println(a1);
	}

	private static void showFlightCapacityMenu(Airplane a1) {
		// displays the current and max capacity of the flight
		System.out.println("The current number of passengers: " + a1.getNumOfPassengers());
		System.out.println("Flight max capacity: " + (a1.getNumOfRows() * a1.getNumOfColumns()));
	}

	private static void showSeatMapMenu(Airplane a1) {
		// display the available and non available seats in the console
		a1.showSeatMap();
	}

	private static void showExitMessage() {
		// output message when exiting program
		System.out.println("Thank you for using the App!  Have a good day!");
	}

	private static void showErrorMessage() {
		// output error message from invalid menu selection
		System.out.println("Invalid Input.  Please enter a valid number.");
	}

	private static int receiveRowNumberInput(Scanner userInput) {
		// helper method to continue to prompt the user if he/she does not enter an
		// proper input
		int row = -1;
		while (true) {
			try {
				String temp = userInput.nextLine();
				row = Integer.parseInt(temp);
				break;
			} catch (NumberFormatException e) {
				System.out.println("Invalid Input.  Please enter a valid number.");
				continue;
			}

		}
		return row;
	}
}
