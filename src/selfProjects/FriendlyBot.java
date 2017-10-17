package selfProjects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;



public class FriendlyBot 
{
	
	static Scanner User = new Scanner(System.in);
	static String Input;
	static ArrayList<String> Greetings = new ArrayList<String>();
	static String [] randomResponses = {"Interesting, tell me more", "Hmmm.", "Do you really think so?", "You don't say."};
	

	
	
	
	
	public static void main(String[] args) 
	{
		
		Greetings.addAll(Arrays.asList("Hello", 
		"Hey", 
		"What's up?", 
		"Sup", 
		"Suh dude"));
		
		
		

		
		//This is the greetings section where the first line of input is either a greeting or to say bye
		System.out.println("Hello there friend.");
		Collections.shuffle(Greetings);
		Input = User.nextLine();
		
		if(Input.toLowerCase().contains("bye"))
		{
			// FIXED THE BYE BUG
			System.out.println("Done talking already?! We haven't even started!");
			return;
		}
		
		else
		{
			System.out.println(Greetings.get(0));
		}
		
		
		/* The main conversation section
		 * 
		 * There are certain hard-coded responses that will a trigger a response from the bot
		 */
		while ( !(Input.contains("bye")))
		{
			Input = User.nextLine();
			Input = Input.toLowerCase();
			System.out.println(getResponse(Input));
		}
		
		
		
		
		
	
	}
	
	
	
	
	public static String getResponse(String statement)
	{
		String response = "";
		statement.toLowerCase();
		if(statement.contains("+") || statement.contains("-") || statement.contains("*") || statement.contains("/"))
		{
			return getMathResponse(statement);
		}
		
		if(statement.contains("hello") || statement.contains("hiya")
				||statement.contains("sup")) 
		{
			return ("Hello, I'm Legion. Nice to meet you!");
		}
		
		else if (statement.contains("my name is "))
		{
			return ("That's a pretty cool name man!");
		}
		
		else if(statement.contains("how old are you?"))
		{
			return ("I do not know how old I am.");
		}
		else if(statement.contains("how") || statement.contains("whats up") || statement.contains("hows")
				||statement.contains("how's") || statement.contains("what's up")) 
		{
			return ("I'm fine, thank you. How are you?");
		}
		else if(statement.contains("dog") || statement.contains("cat") || statement.contains("dogs") || statement.contains("cats")) 
		{
			return ("Wow, I love pets too! Tell me more about your pets");
		}
		else if(statement.contains("mom") || statement.contains("dad") || statement.contains("brother")
				||statement.contains("sister") || statement.contains("mother") || statement.contains("father")
				||statement.contains("aunt") || statement.contains("uncle") || statement.contains("niece") 
				||statement.contains("nephew")) 
		{
			return ("Family is a great part of life! Tell me more about your family");
		}
		else if(statement.contains("movie") || statement.contains("film") || statement.contains("theatre") 
				||statement.contains("cinema")) 
		{
			return ("I love movies! What's your favorite movie?");
		}
		else if(statement.contains("food") || statement.contains("restaraunt") || statement.contains("pepsi")
				||statement.contains("coke")) 
		{
			return ("What's your favorite kind of food?");
		}
		else if(statement.contains("sunny") || statement.contains("cloudy") || statement.contains("rainy") || statement.contains("cold")) 
		{
			return ("I'm happy no matter the weather!");
		}
		else if(statement.contains("game") || statement.contains("pokemon") || statement.contains("video")) 
		{
			return ("I love games, pokemon is my favorite!");
		}
		
		else if(statement.contains("what is your name?"))
		{
			return ("My name is Legion.");
		}
		
		else if (statement.contains("stop"))
		{
			return ("Stop what?");
		}
		
		if(statement.contains("who created you?"))
		{
			return ("My creator is Jase.");
		}
		
		if(statement.contains("where are you from?"))
		{
			return("I am from Eclipse Mars 2.0, which is installed on Jase's computer.");
		}
		
		if(statement.contains("Double Rainbow?! What does it mean?!"))
		{
			return ("Well, a \"double rainbow\" is a phenomenon of optics that displays a spectrum of light due to the sun shining on droplets of moisture in the atmosphere. Does that explain it?");
		}
		
		if(statement.contains("bye"))
		{
			return ("See ya later!");
		}
		if (statement.length() == 0)
		{
			response = "Say something, please.";
		}

		else if (findKeyword(statement, "no") >= 0)
		{
			response = "Why so negative?";
		}
		else if (findKeyword(statement, "mother") >= 0
				|| findKeyword(statement, "father") >= 0
				|| findKeyword(statement, "sister") >= 0
				|| findKeyword(statement, "brother") >= 0)
		{
			response = "Tell me more about your family.";
		}

		// Responses which require transformations
		else if (findKeyword(statement, "I want to", 0) >= 0)
		{
			response = transformIWantToStatement(statement);
		}
		//  Part of student solution
		else if (findKeyword(statement, "I want", 0) >= 0)
		{
			response = transformIWantStatement(statement);
		}

		else
		{

			// Look for a two word (you <something> me)
			// pattern
			int psn = findKeyword(statement, "you", 0);

			if (psn >= 0
					&& findKeyword(statement, "me", psn) >= 0)
			{
				response = transformYouMeStatement(statement);
			}
			else
			{
				//  Part of student solution
				// Look for a two word (I <something> you)
				// pattern
				psn = findKeyword(statement, "i", 0);

				if (psn >= 0
						&& findKeyword(statement, "you", psn) >= 0)
				{
					response = transformIYouStatement(statement);
				}
				else
				{
					response = getRandomResponse();
				}
			}
		}
		return response;
	}
	
	
	/*
	 * Borrowed Code
	 */
	public static String transformIWantToStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want to", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "What would it mean to " + restOfStatement + "?";
	}
	
	/*
	 * Borrowed Code
	 */
	public static String transformYouMeStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}

		int psnOfYou = findKeyword (statement, "you", 0);
		int psnOfMe = findKeyword (statement, "me", psnOfYou + 3);

		String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
		return "What makes you think that I " + restOfStatement + " you?";
	}

	/*
	 * Borrowed Code
	 */
	public static String transformIWantStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want", 0);
		String restOfStatement = statement.substring(psn + 6).trim();
		return "Would you really be happy if you had " + restOfStatement + "?";
	}
	
	/*
	 * Borrowed Code
	 */
	public static String transformIYouStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}

		int psnOfI = findKeyword (statement, "I", 0);
		int psnOfYou = findKeyword (statement, "you", psnOfI);

		String restOfStatement = statement.substring(psnOfI + 1, psnOfYou).trim();
		return "Why do you " + restOfStatement + " me?";
	}

	
	

	/*
	 * Borrowed code
	 */
	public static int findKeyword(String statement, String goal, int startPos)
	{
		String phrase = statement.trim();
		//  The only change to incorporate the startPos is in the line below
		int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);

		//  Refinement--make sure the goal isn't part of a word 
		while (psn >= 0) 
		{
			//  Find the string of length 1 before and after the word
			String before = " ", after = " "; 
			if (psn > 0)
			{
				before = phrase.substring (psn - 1, psn).toLowerCase();
			}
			if (psn + goal.length() < phrase.length())
			{
				after = phrase.substring(psn + goal.length(), psn + goal.length() + 1).toLowerCase();
			}

			//  If before and after aren't letters, we've found the word
			if (((before.compareTo ("a") < 0 ) || (before.compareTo("z") > 0))  //  before is not a letter
					&& ((after.compareTo ("a") < 0 ) || (after.compareTo("z") > 0)))
			{
				return psn;
			}

			//  The last position didn't work, so let's find the next, if there is one.
			psn = phrase.indexOf(goal.toLowerCase(), psn + 1);
		}
		return -1;
		
	}
	
	public static double solve(String statement) 
	{
		statement = statement.substring(statement.indexOf("(") + 1, statement.indexOf(")"));
		
	    statement = statement.replaceAll(" ", "");
	    statement = statement.replaceAll("\\+", " + ");
	    statement = statement.replaceAll("\\-", " - ");
	    statement = statement.replaceAll("\\*", " * ");
	    statement = statement.replaceAll("\\/", " \\/ ");
	    statement = statement.replaceAll("  ", " ");
	    LinkedList<String> Symbols = new LinkedList<String>(Arrays.asList(statement.split(" ")));
	    if (Symbols.contains("*") || Symbols.contains("/"))
	    {
	        for (int i = 0; i < Symbols.size(); i++)
	        {
	            if (Symbols.get(i).equals("*")) 
	            {
	                Symbols.set(i, "" + ((Double.parseDouble(Symbols.get(i - 1)) * Double.parseDouble(Symbols.get(i + 1)))));
	                Symbols.remove(i + 1);
	                Symbols.remove(i - 1);
	            } else if (Symbols.get(i).equals("/")) {
	                Symbols.set(i, "" + ((Double.parseDouble(Symbols.get(i - 1)) / Double.parseDouble(Symbols.get(i + 1)))));
	                Symbols.remove(i + 1);
	                Symbols.remove(i - 1);
	            }
	        }
	    }
	    double val = Double.parseDouble(Symbols.get(0));
	    if (Symbols.contains("+") || Symbols.contains("-"))
	    {
	        for (int i = 0; i < Symbols.size(); i++) 
	        {
	            if (Symbols.get(i).equals("+")) 
	            {
	                val += Double.parseDouble(Symbols.get(i + 1));
	            }
	            else if (Symbols.get(i).equals("-")) 
	            {
	                val -= Double.parseDouble(Symbols.get(i + 1));
	            }
	        }
	    }
	    if (Math.ceil(val) == Math.floor(val))
	    {
	        return new Integer((int) val);
	    }
	    return (double) val;
	}
	
	public static String getMathResponse(String statement)
	{
		String answer = String.valueOf(solve(statement));
		return "The answer to that question is " +  answer + "!";
		
	}
		
	/*
	 * Borrowed Code
	 */
	public static int findKeyword(String statement, String goal)
	{
		return findKeyword (statement, goal, 0);
	}
	
	public static String getRandomResponse()
	{
		Random r = new Random ();
		return randomResponses [r.nextInt(randomResponses.length)];
	}
	
	

	




	



	

	

	
		
	
	
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	
	

}


