package solutinchecker.check;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CheckMySolution {
	
	public static void main(String[] args) throws IOException {
		
		FileInputStream mySolution = new FileInputStream("my_output.txt");
		Scanner scanner = new Scanner(mySolution);
		
		FileInputStream correctSolution = new FileInputStream("correct_output.txt");
		Scanner otherScanner = new Scanner(correctSolution);
		
		String mine = null, theirs = null;
		int errors = 0;
		
		while (scanner.hasNext()) {
			
			try {
				mine = scanner.nextLine();
				theirs = otherScanner.nextLine();
			} catch (NoSuchElementException e) {
				System.out.println("Your output is incorrect and contains too much data.");
				System.exit(-1);
			} 
			
			if (!mine.equals(theirs)) { 
				errors++;
				System.out.println("Incorrect line in your output: \n");
				System.out.printf("Yours: %s \nTheirs: %s \n\n", mine, theirs); 
			}
			
			
		}
		
		if (otherScanner.hasNext()) { 
			
			errors++; 
			
			System.out.println("Your output has less data than the correct solution.\n\n Missing data: \n");
			
			while (otherScanner.hasNext()) {
				
				String missing_line = otherScanner.nextLine();
				System.out.println(missing_line);
				
			}
			
			
		}
		
		if (errors == 0) { System.out.println("Your output is correct."); }
		
		scanner.close();
		otherScanner.close();
		mySolution.close();
		correctSolution.close();
		
	}

}
