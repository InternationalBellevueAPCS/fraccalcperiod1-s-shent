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
    	int spaceCount1 = 0;
    	int spaceCount2 = 0;
    	String firstOperand = "0";
    	String firstWhole = "0";
    	String firstNum = "0";
    	String firstDen = "0";
    	char operator = ' ';
    	String secondOperand = "0";
    	String secondWhole = "0";
    	String secondNum = "0";
    	String secondDen = "0";
    	//Goes through input and identifies second operand using the second space character
        for (int i = 0; i < input.length() - 1; i++) {
        	if (input.charAt(i) == ' ') {
        		spaceCount1++;
        		spaceCount2++;
        	}
        	//Puts the first operand into a string
        	if (spaceCount1 == 1 ) {
        		firstOperand = input.substring(0, i);
        		operator = input.charAt(i + 1);
        		spaceCount1 = 2;
        	}
        	//Puts the second operand into a second string
        	if (spaceCount2 == 2) {
        		secondOperand = input.substring(i + 1);
        		spaceCount2 = 0;
        		}
        	}
        
        //Goes through second operand and breaks it up
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
        
        //Goes through first operand and breaks it up
        for (int i = 0; i < firstOperand.length(); i++) {
        	//Identifies whole number
        	if (firstOperand.charAt(i) == '_') {
        		firstWhole = firstOperand.substring(0, i);
        	}
        	//Identifies numerator and denominator
        	if (firstOperand.charAt(i) == '/') {
        		firstNum = firstOperand.substring(firstOperand.indexOf('_') + 1, i);
        		firstDen = firstOperand.substring(firstOperand.indexOf('/') + 1);
        	}
        }
        //Sets values for special cases of input
        
        if (firstNum.equals("0")) {
        	firstWhole = firstOperand;
        	firstDen = "1";
        }
        
        //Convert Strings into ints
        int fW = Integer.parseInt(firstWhole);
        int fN = Integer.parseInt(firstNum);
        int fD = Integer.parseInt(firstDen);
        int sW = Integer.parseInt(secondWhole);
        int sN = Integer.parseInt(secondNum);
        int sD = Integer.parseInt(secondDen);
        //Correctly sets up whole numbers for conversion to mixed
        boolean fNeg = false;
        boolean sNeg = false;
        if (fW < 0 || fN < 0) {
        	fNeg = true;
        }
        if (sW < 0 || sW < 0) {
        	sNeg = true;
        }
        //Turns mixed numbers into like improper fractions
        fN = (Math.abs(fW) * fD) + Math.abs(fN);
        if (fNeg) {
        	fN *= -1;
        }
        sN = (Math.abs(sW) * sD) + Math.abs(sN);
        if (sNeg) {
        	sN *= -1;
        }
        fN *= sD;
        sN *= fD;
        fD *= sD;
        sD *= fD/sD;
        
        //Declare answer variables
        int answerNum = 0;
        int answerDen = fD;
        //Perform fraction operations
        if (operator == '+') {
        	answerNum = fN + sN;
        }
        else if (operator == '-') {
        	answerNum = fN - sN;
        }
        else if (operator == '*') {
        	answerNum = fN * sN;
        	answerDen = fD * sD;
        }
        else if (operator == '/') {
        	int temp = sN;
        	sN = sD;
        	sD = temp;
        	answerNum = fN * sN;
        	answerDen = fD * sD;
        }
        return simplify(answerNum, answerDen);
    }

    //Simplifies the final fraction
    public static String simplify(int num, int den) {
    	for (int i = 2; i < den; i++) {
    		if (num % i == 0 && den % i == 0) {
    			num /= i;
    			den /= i;
    			i = 2;
    		}
    	}
    	if (Math.abs(num) > den) {
    		int whole = num/den;
    		if (Math.abs(num) - Math.abs(whole) * den == 0) {
    			return String.valueOf(whole);
    		}
    		if (den < 0) {
    			den = Math.abs(den);
    			whole *= -1;
    		}
    		return (whole + "_" + (Math.abs(num) - (Math.abs(whole) * Math.abs(den)) + "/" + den));
    	}
    	if (num == den) {
    		return ("1");
    	}
    	if (-num == den) {
    		return ("-1");
    	}
    	if (num == 0) {
    		return "0";
    	}
    	if (den == 1) {
    		return String.valueOf(num);
    	}
    	if (den == -1) {
    		return String.valueOf(-num);
    	}
    	return (num + "/" + den);
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
    //Finds the greatest common divisor for the leastCommonMultiple method
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
    //Finds the least common multiple of the denominators
    public static int leastCommonMultiple(int a, int b)
    {
        int gcd = greatestCommonDivisor(a, b);
        return (a*b)/gcd;
    }
}