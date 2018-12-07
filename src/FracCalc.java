import java.util.Scanner;

public class FracCalc {

    /**
     * Prompts user for input, passes that input to produceAnswer, then outputs the result.
     * @param args - unused
     */
    public static void main(String[] args) 
    {	
    	//Prints instructions for use
    	System.out.println("Instructions:");
    	System.out.println("1. Enter your values like: 15 or 2_3/4");
    	System.out.println("2. Put a space on either side of the operand");
    	
    	System.out.println();
    	//Creates a scanner for input
    	Scanner console = new Scanner(System.in);
    	String input = "";
    	
    	//Loops for multiple input
    	while (!input.equalsIgnoreCase("quit")) {
    		System.out.print("Enter a fraction problem (\"quit\" to stop): ");
    		input = console.nextLine();
    		
    		//Sentinel value to quit program
    		if (input.equalsIgnoreCase("quit")) {
    			break;
    		}
    		System.out.println(produceAnswer(input));
    	}
    }
    
    /**
     * produceAnswer - This function takes a String 'input' and produces the result.
     * @param input - A fraction string that needs to be evaluated.  For your program, this will be the user input.
     *      Example: input ==> "1/2 + 3/4"
     * @return the result of the fraction after it has been calculated.
     *      Example: return ==> "1_1/4"
     */
    public static String produceAnswer(String input)
    {
        // TODO: Implement this function to produce the solution to the input
        // Checkpoint 3: Evaluate the formula and return the result as a fraction.
        //               Example "4/5 * 1_2/4" returns "6/5".
        //               Note: Answer does not need to be reduced, but it must be correct.
        // Final project: All answers must be reduced.
        //               Example "4/5 * 1_2/4" returns "1_1/5".
    	
    	//Variable declaration
    	int spaceCount = 0;
    	String secondOperand = "0";
    	String secondWhole = "0";
    	String secondNum = "0";
    	String secondDen = "0";
    	
    	//Goes through input and identifies second operand using the second space character
        for (int i = 0; i < input.length() - 1; i++) {
        	if (input.charAt(i) == ' ') {
        		spaceCount++;
        	}
        	//Puts the second operand into a second string
        	if (spaceCount == 2) {
        		secondOperand = input.substring(i + 1);
        		spaceCount = 0;
        		}
        	}
        
        //Goes trough second operand and breaks it up
        for (int i = 0; i < secondOperand.length(); i++) {
        	//Identifies whole number
        	if (secondOperand.charAt(i) == '_') {
        		secondWhole = secondOperand.substring(0, i);
        	}
        	//Identifies numerator and denominator
        	if (secondOperand.charAt(i) == '/') {
        		secondNum = secondOperand.substring(secondOperand.indexOf('_') + 1, i);
        		secondDen = secondOperand.substring(secondOperand.indexOf('/') + 1);
        	}
        }
        
        //Sets values for special cases of input
        if (secondNum.equals("0")) {
        	secondWhole = secondOperand;
        	secondDen = "1";
        }
        return "whole:" + secondWhole + " numerator:" + secondNum + " denominator:" + secondDen;
    }

    // TODO: Fill in the space below with helper methods
    
    /**
     * greatestCommonDivisor - Find the largest integer that evenly divides two integers.
     *      Use this helper method in the Final Checkpoint to reduce fractions.
     *      Note: There is a different (recursive) implementation in BJP Chapter 12.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The GCD.
     */
    public static int greatestCommonDivisor(int a, int b)
    {
        a = Math.abs(a);
        b = Math.abs(b);
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        while (min != 0) {
            int tmp = min;
            min = max % min;
            max = tmp;
        }
        return max;
    }
    
    /**
     * leastCommonMultiple - Find the smallest integer that can be evenly divided by two integers.
     *      Use this helper method in Checkpoint 3 to evaluate expressions.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The LCM.
     */
    public static int leastCommonMultiple(int a, int b)
    {
        int gcd = greatestCommonDivisor(a, b);
        return (a*b)/gcd;
    }
}
