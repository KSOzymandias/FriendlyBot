package fracCalc;

import java.util.Scanner;

public class FracCalc {

    public static void main(String[] args) 
    {
        // Read the input from the user and call produceAnswer with an equation
		Scanner input = new Scanner(System.in);
    	System.out.println("Enter fraction(s): ");
    	String Equation = input.nextLine();
    	
    	//If input is quit end the program
    	if(Equation .equalsIgnoreCase("quit"))
    	{
    	System.out.println(EndProgram(Equation));
    	}
    	//If input is not quit continue the program
    	else
    	{
    		System.out.println(produceAnswer(Equation));
    	}
    	
    		//Same as above
    		while (!(Equation .equalsIgnoreCase("quit")))
    		{
    			if(Equation .equalsIgnoreCase("quit"))
    			{
    				System.out.println(EndProgram(Equation));
    				break;
    			}
    			
    			else
    			{
    		System.out.println("Enter fraction(s): ");
    	    String Equation2 = input.nextLine();
    	    
    	    if(Equation2 .equalsIgnoreCase("quit"))
    	    {
    	    	System.out.println(EndProgram(Equation2));
    	    	break;
    	    }
    	    else
    	    {
        	System.out.println(produceAnswer(Equation2));
    	    }
    			}
    		}
    		input.close();
    	
    }
    
    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //        
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
   
    
    public static String produceAnswer(String input)
    {
    	//Splits fractions based on mixed number or improper and stores them.
    	String frac1 = GetImproperFraction(SplitFraction1(input));
    	String operator = SplitOperand(input);
    	String frac2 = GetImproperFraction(SplitFraction2(input));
    	
    	
    	//Does math on the two fractions based on the operator (+ - * /)
    	return Math(frac1, operator, frac2);
    	
    }
    public static String Math(String Fraction, String Operator, String Fraction2)
    { 
    	String result = "";
        //Retrieves the numerator and denominator of both fractions
        int top1 = GetNumerator(Fraction);
        int top2 = GetNumerator(Fraction2);
        int bot1 = GetDenominator(Fraction);
        int bot2 = GetDenominator(Fraction2);
        
 
        if (Operator.equals("+")) 
        {
            // Does addition and set the return value as an improper fraction
            result = ((top1 * bot2) + (top2 * bot1)) + "/" + Math.abs(bot1 * bot2);
        } 
        else if (Operator.equals("-")) 
        {
            // Do subtraction
            result = ((top1 * bot2) - (top2 * bot1)) + "/" + Math.abs(bot1 * bot2);
        } 
        else if (Operator.equals("*")) 
        {
            // Do multiplication
            result = (top1 * top2) + "/" + (bot1 * bot2);
        } 
        else if (Operator.equals("/")) 
        {
            // Do division
            if (bot1 < 0 || top2 < 0) 
            {
                result = -(top1 * bot2) + "/" + Math.abs(bot1 * top2);
            } 
            else 
            {
                result = (top1 * bot2) + "/" + Math.abs(bot1 * top2);
            }
        } else 
        {
            result = "";
        }   
        
        //Divide by zero error
        if (bot1 == 0 || bot2 == 0)
        {
        	return "Error: Denominator cannot be zero";
        }
        
        return GetReducedResult(result);
    }
    
	
    	
    	
        	
       
        
    	 
    // TODO: Fill in the space below with any helper methods that you think you will need
   
    //Ends Program
    public static String EndProgram(String input)
    {
    	
    		return "Thank you for using the calculator!";
    	
    }
    
		

    //Splits the first fraction from Equation
    public static String SplitFraction1(String input)
    {
    	
    	String[] array = input.split(" ");
    	
    	String frac1 = array[0];
    	
		return frac1;
    	
		
    }
    
    //Splits the operator from Equation
    public static String SplitOperand(String input)
    {
    	
    	String[] array = input.split(" ");
    	
    	String op = array[1];
    	
		return op;
    	
    }
    
    //Splits the second fraction from Equation
    public static String SplitFraction2(String input)
    {
    	
    	String[] array = input.split(" ");
    	
    	String frac2 = array[2];
    	
		return frac2;
    	
    }
    
    //Retrieves input and turns converts to an improper fraction
    public static String GetImproperFraction(String input) {
        int whole = 0;
        int numerator = 0;
        int denominator = 1;
 
        // get index of '_' and convert everything before to int whole
        //get index of '/' and parse numerator and denominator
        
        int index = input.indexOf('_');
        int fracIndex = input.indexOf('/');
 
        if (index > 0) 
        {
            whole = Integer.parseInt(input.substring(0, index));
            if (fracIndex > 0) 
            {
                numerator = Integer.parseInt(input.substring(index + 1, fracIndex));
                denominator = Integer.parseInt(input.substring(fracIndex + 1, input.length()));
            }
        } else if (fracIndex > 0) 
        {
            numerator = Integer.parseInt(input.substring(index + 1, fracIndex));
            denominator = Integer.parseInt(input.substring(fracIndex + 1, input.length()));
        } else 
        {
        	
        	{
            whole = Integer.parseInt(input);
        	}
        }           
 
        if (whole < 0)  
        {
            numerator = (whole * denominator) - numerator;
        } else 
        {
            numerator = (whole * denominator) + numerator;
        }
        return numerator + "/" + denominator;
    }       
    
    // get the integer numerator from an improper fraction "5/3" should return 5
    public static int GetNumerator(String frac) 
    {
    	//Assigns the numerator a default value and parses int to equal numerator
        int numerator = 0;
        int index = frac.indexOf('/');
        if (index > 0) 
        {
            numerator = Integer.parseInt(frac.substring(0, index));
        }
        return numerator;
    }
 
    // get the integer denominator from an improper fraction "5/3" should return 3
    public static int GetDenominator(String input) 
    {
        int denominator = 0;
        int index = input.indexOf('/');
        if (index > 0) 
        {
            denominator = Integer.parseInt(input.substring(index + 1, input.length()));
        }
        return denominator;
    }   
	
    
  //Reduces the answer to lowest terms by calling the GCD method.
public static String GetReducedResult(String input) 
{
	
	
	
    int numerator = GetNumerator(input);
    int denominator = GetDenominator(input);
    int whole = numerator / denominator;

    // get GCF for reduction
    int gcf = Math.abs(GCD(numerator, denominator));
    numerator = numerator / gcf;
    denominator = denominator / gcf;

    numerator = numerator % denominator;

    if (numerator == 0) 
    {
        return "" + whole;
    }
    else if (whole != 0) 
    {
        return whole + "_" + Math.abs(numerator) + "/" + denominator;
    } else 
    {
        return numerator + "/" + denominator;
    }
}    

//Non-recursive GCD method
public static int GCD(int first, int second) 
{
    int temp;
    while (second != 0) 
    {
        temp = second;
        second = first % second;
        first = temp;
    }
    return first;
}

    
    
}
