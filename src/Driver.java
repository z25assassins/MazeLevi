//Levi Allery
//GID: -02246666
//3.15.2016

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
	////////////////////////////////////////////////////////////////////////////////////
	//Two mazes. One to display and change and one to move on//////////////////////////
	static char[][] maze = new char[12][12];
	static char[][] printMaze = new char[12][12];
	// mouse location variables
	static int currentX;
	static int currentY;
	static int currentHx;
	static int currentHy;
	// direction variables
	static boolean north;
	static boolean east;
	static boolean south;
	static boolean west;
	static int facingX;
	static int facingY;
/////////////////////////////////////////////////////////////////////////////////////
	//DRIVER////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) throws FileNotFoundException {
		// read in the file
		File inFile = new File("C:/Users/zkiam/Documents/MSU/Basic data structures and algorithims/MazeLevi/src/maze.txt");//Hard coded in right now
		Scanner sc = new Scanner(inFile);
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				maze[i][j] = sc.next().trim().charAt(0);//Deletes spaces and collects characters instead of strings
			}
		}
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				printMaze[i][j] = maze[i][j];
			}
		}
		sc.close();// close the scanner
		print();
		currentX = 0;
		currentY = 2;
		currentHx = 0;
		currentHy = 3;
		printMaze[currentY][currentX] = 'X';
		print();
		check(currentX, currentY, currentHx, currentHy);
	}
//////////////////////////////////////////////////////////////////////////////////
	//The main method that checks where the player is and makes moves accordingly

	public static void check(int x, int y, int hx, int hy) {
		north = false;
		east = false;
		south = false;
		west = false;
		if (hx < x) {
			// facing south
			facingX = x;
			facingY = y + 1;
			south = true;
		} 
		else if (hx > x) {
			// facing north
			facingX = x;
			facingY = y - 1;
			north = true;
		} 
		else if (hy < y) {
			// facing west
			facingX = x - 1;
			facingY = y;
			west = true;
		} 
		else {
			// facing east
			facingX = x + 1;
			facingY = y;
			east = true;
		}
		////////////////////////////////////////////
		if (maze[facingY][facingX] == 'F') {
			printMaze[currentY][currentX] = 'O';
			printMaze[facingY][facingX]='X';
			print();
			System.out.println("Hey you found it you lucky bastard.");
			return;
		}
		///////////////////////////////////////////
		if (maze[hy][hx] == '.') {
			System.out.println("Hand is on a dot. Moving there now");
			printMaze[currentY][currentX] = 'O';
			// move onto that dot
			currentX = hx;
			currentY = hy;
			////////////////////////////
			if (north) {
				currentHx = x + 1;
				currentHy = y + 1;
			}
			else if (east) {
				currentHx = x - 1;
				currentHy = y + 1;
			} 
			else if (south) {
				currentHx = x - 1;
				currentHy = y - 1;
			}
			else {// west
				currentHx = x + 1;
				currentHy = y - 1;
			}
			printMaze[currentY][currentX] = 'X';
			print();
			check(currentX, currentY, currentHx, currentHy);//Recursive call
		} 
		else if (maze[facingY][facingX] == '#') {
			System.out.println("I am facing a wall. Turning now");
			currentHx = facingX;
			currentHy = facingY;
			check(currentX, currentY, currentHx, currentHy);
		} 
		else if (maze[facingY][facingX] == '.') {
			System.out.println("I am facing a dot. Moving there now");
			// move onto that dot
			// set hand location according to direction facing
			if (north) {
				move(x, (y - 1), "north");
			} else if (east) {
				move((x + 1), y, "east");
			} else if (south) {
				move(x, (y + 1), "south");
			} else {// west
				move((x - 1), y, "west");
			}
			check(currentX, currentY, currentHx, currentHy);//Recursive call
		}
	}
///////////////////////////////////////////////////////////////////////////////
	//Moves to new location and sets hand location based on direction facing
	public static void move(int newX, int newY, String direction) {
		printMaze[currentY][currentX] = 'O';
		currentX = newX;
		currentY = newY;
		switch (direction) {
		case "south":
			currentHx = newX - 1;
			currentHy = newY;
			break;
		case "north":
			currentHx = newX + 1;
			currentHy = newY;
			break;
		case "east":
			currentHx = newX;
			currentHy = newY + 1;
			break;
		case "west":
			currentHx = newX;
			currentHy = newY - 1;
			break;
		default:
			//This does nothing during normal operation
			System.out.println("Something has gone wrong. Program failed");
		}
		printMaze[currentY][currentX] = 'X';
		print();
	}

	//Easy way to print out the maze
	public static void print() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				System.out.print(printMaze[i][j]);
			}
			System.out.print("\n");

		}
		System.out.println("\n");
	}

}
