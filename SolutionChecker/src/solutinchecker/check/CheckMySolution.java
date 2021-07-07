/* Created by Adam Jost on 07/06/2021 */

package solutinchecker.check;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CheckMySolution {
	
	public static void main(String[] args) throws IOException {
		// Solution to be checked.
		FileInputStream mySolution = new FileInputStream("my_output.txt");
		Scanner scanner = new Scanner(mySolution);
		
		// The correct solution used to compare.
		FileInputStream correctSolution = new FileInputStream("correct_output.txt");
		Scanner otherScanner = new Scanner(correctSolution);
		
		String mine = null, theirs = null;
		int line = 1, errors = 0;
		
		// Parse solutions. 
		while (scanner.hasNext()) {
			try {
				mine = scanner.nextLine();
				theirs = otherScanner.nextLine();
			} catch (NoSuchElementException e) {
				// The solution being checked contains more data then the correct
				// solution so we print a notification to the console. 
				System.out.println("Your solution is incorrect and contains too much data.");
				System.exit(-1);
			} 
			// If the line in the solutions do not match.
			// Print a notification that the line is 
			// incorrect and show the two lines where
			// the inconsistency was found.
			if (!mine.equals(theirs)) { 
				errors++;
				System.out.printf("Line %d is incorrect: \n", line);
				System.out.printf("Yours: %s \nTheirs: %s \n\n", mine, theirs); 
			}
			// Increment the current line being analyzed.
			line++;
		}
		
		// If the end of your solution has been reached but the 
		// correct solution still contains data then your solution
		// is not correct and contains too little data. 
		if (otherScanner.hasNext()) { 
			
			errors++; 
			// Print that the solution is incorrect to the console and why.
			System.out.println("Your solution has less data than the correct solution.\n\n Missing data: \n");
			
			// Print each missing line to the console. 
			while (otherScanner.hasNext()) {
				String missing_line = otherScanner.nextLine();
				System.out.println(missing_line);
			}			
		}
		// If no errors were found then the solution being checked is
		// correct so we print that to the console. 
		if (errors == 0) { System.out.println("Your solution is correct."); }
		
		// Close streams.
		scanner.close();
		otherScanner.close();
		mySolution.close();
		correctSolution.close();
		
	}

}
