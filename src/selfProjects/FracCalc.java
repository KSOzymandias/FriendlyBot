package selfProjects;

import java.util.*;
import javax.swing.*;
 
public class FracCalc {
	public static void main(String[] args) {
 
        
		Scanner scan = new Scanner(System.in);
 
        // read a LINE of user input and check if == "quit"
        //System.out.println("Enter your equation...");
        String control = JOptionPane.showInputDialog(null, "Enter your equation in this format: \n Numerator1/Denominator1 + Numerator2/Denominator2\n Example: 1/2 + 1/2"); 
 
        // declare string variables to store tokens read from scanner
        String frac1 = "";
        String op = "";
        String frac2 = "";
        String reducedExpression = "";
 
        // command loop - continue until user enters quit
        try{
        while (!control.equalsIgnoreCase("quit")) {
 
            // now use a new Scanner on the control string to parse frac1, op & frac2 (uses whitespace to get tokens)
            Scanner input = new Scanner(control);
 
            // test to see if there is a second token - the operator, else ERROR
            if (input.hasNext()) {
                frac1 = input.next();
                if (!IsFraction(frac1)) {
                    frac1 = "";
                }
            }  
 
            while (frac1.length() > 0 && input.hasNext()) {
 
                // test to see if there is a second token - the operator, else ERROR
                if (input.hasNext()) {
                    op = input.next();
                    if (!IsOperator(op)) {
                        op = "";
                    }
                }
 
                // test to see is there is a third token - frac2, else ERROR
                if (input.hasNext()) {
                    frac2 = input.next();
                    if (!IsFraction(frac2)) {
                        frac2 = "";
                    }
                }
 
                if (op.length() < 1 || frac2.length() < 1 || (op.equals("/") && frac2.equals("0"))) {
                    // input error - reset variables
                    frac1 = "";
                    op = "";
                    frac1 = "";
                } else {
                    frac1 = GetImproperFraction(frac1);
                    frac2 = GetImproperFraction(frac2);
 
                    // if op is * or / evaluate and add result to reducedExpression for deferred evaluation
                    if (frac2.length() > 0 && op.length() > 0 && (op.equals("*") || op.equals("/"))) {
                        // 3/4 * 2/3
                        frac1 = DoMath(frac1, op, frac2);
                    } else {
                        // 1/2 +
                        reducedExpression += frac1 + " " + op + " ";
                        frac1 = frac2;
                    }
                }
            }
 
            if (frac1.length() > 0) {
                reducedExpression += frac1;
                input = new Scanner(reducedExpression);
                frac1 = input.next();
                while (frac1.length() > 0 && input.hasNext()) {
                    frac1 = GetImproperFraction(frac1);
                    op = input.next();
                    frac2 = input.next();
                    frac2 = GetImproperFraction(frac2);
                    frac1 = DoMath(frac1, op, frac2);
                }
                 JOptionPane.showMessageDialog(null, frac1);
            } else {
                JOptionPane.showMessageDialog(null,"There was an error in your input!");
            }
 
            // reset variables for the next line of input
            frac1 = "";
            op = "";
            frac2 = "";
            reducedExpression = "";
 
            // read a LINE of user input and check if == "quit"
           control = JOptionPane.showInputDialog(null,"Enter your equation"); 
        }
        }catch(Exception e){}
        // user entered "quit"
         JOptionPane.showMessageDialog(null, "Done!"); 
    }
 
    // returns true if parameter frac is in the form of mixed "1_1/3", or fraction "1/2" or whole "2"
    public static boolean IsFraction(String frac) {
        return frac.matches("^(-*)(\\d+_\\d+/\\d+|\\d+/\\d+|\\d+)$");
    }
 
    // returns true if the parameter op is one of the 4 valid operators +, -, / or *
    public static boolean IsOperator(String op) {
        return op.equals("+") || op.equals("-") || op.equals("/") || op.equals("*");
    }
 
    // get improper fraction from "1_2/3" - should return "5/3"
    public static String GetImproperFraction(String frac) {
        int whole = 0;
        int numerator = 0;
        int denominator = 1;
 
        // get index of '_' and convert everything before to int whole
        int index = frac.indexOf('_');
        int fracIndex = frac.indexOf('/');
 
        if (index > 0) {
            whole = Integer.parseInt(frac.substring(0, index));
            if (fracIndex > 0) {
                numerator = Integer.parseInt(frac.substring(index + 1, fracIndex));
                denominator = Integer.parseInt(frac.substring(fracIndex + 1, frac.length()));
            }
        } else if (fracIndex > 0) {
            numerator = Integer.parseInt(frac.substring(index + 1, fracIndex));
            denominator = Integer.parseInt(frac.substring(fracIndex + 1, frac.length()));
        } else {
            whole = Integer.parseInt(frac);
        }           
 
        if (whole < 0)  {
            numerator = (whole * denominator) - numerator;
        } else {
            numerator = (whole * denominator) + numerator;
        }
        return numerator + "/" + denominator;
    }       
 
    // get the integer numerator from an improper fraction "5/3" should return 5
    public static int GetNumerator(String frac) {
        int numerator = 0;
        int index = frac.indexOf('/');
        if (index > 0) {
            numerator = Integer.parseInt(frac.substring(0, index));
        }
        return numerator;
    }
 
    // get the integer denominator from an improper fraction "5/3" should return 3
    public static int GetDenominator(String frac) {
        int denominator = 0;
        int index = frac.indexOf('/');
        if (index > 0) {
            denominator = Integer.parseInt(frac.substring(index + 1, frac.length()));
        }
        return denominator;
    }   
 
    // takes 2 improper fractions and performs the operation specified by the op parameter: +, - , /, or *
    public static String DoMath(String frac1, String op, String frac2) {
        String result = "";
        // get the numerators and denominators from the 2 fraction parameters
        int top1 = GetNumerator(frac1);
        int top2 = GetNumerator(frac2);
        int bot1 = GetDenominator(frac1);
        int bot2 = GetDenominator(frac2);
 
        if (op.equals("+")) {
            // do addition and set the return value as an improper fraction
            result = ((top1 * bot2) + (top2 * bot1)) + "/" + Math.abs(bot1 * bot2);
        } else if (op.equals("-")) {
            // do subtraction
            result = ((top1 * bot2) - (top2 * bot1)) + "/" + Math.abs(bot1 * bot2);
        } else if (op.equals("*")) {
            // do multiplication
            result = (top1 * top2) + "/" + (bot1 * bot2);
        } else if (op.equals("/")) {
            // do division
            if (bot1 < 0 || top2 < 0) {
                result = -(top1 * bot2) + "/" + Math.abs(bot1 * top2);
            } else {
                result = (top1 * bot2) + "/" + Math.abs(bot1 * top2);
            }
        } else {
            result = "";
        }   
 
        return GetReducedResult(result);
    }
 
    // takes an improper fraction and returns a reduces proper fraction, i.e. "3/2" returns "1_1/2"
    public static String GetReducedResult(String frac) {
        int numerator = GetNumerator(frac);
        int denominator = GetDenominator(frac);
        int whole = numerator / denominator;
 
        // get GCF for reduction
        int gcf = Math.abs(GreatestCommonDivisor(numerator, denominator));
        numerator = numerator / gcf;
        denominator = denominator / gcf;
 
        numerator = numerator % denominator;
 
        if (numerator == 0) {
            return "" + whole;
        }
        else if (whole != 0) {
            return whole + "_" + Math.abs(numerator) + "/" + denominator;
        } else {
            return numerator + "/" + denominator;
        }
    }    
 
    public static int GreatestCommonDivisor(int first, int second) {
        int temp;
        while (second != 0) {
            temp = second;
            second = first % second;
            first = temp;
        }
        return first;
    }
 
    
    
	public static int LeastCommonMultiple(int first, int second) {
        return first * (second / GreatestCommonDivisor(first, second));
    }
}