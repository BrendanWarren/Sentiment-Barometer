package cardiffmet.st20005852.assignment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Rating {
	
	/*
	 * 	Rating.java
	 * 
	 * 	Takes the final results from HierarchyManager.java and updates the rating & produces the final report at the end of the program
	 * 
	 */
	
	BufferedReader myInput = new BufferedReader(new InputStreamReader(System.in));
	public double rating = 0;
	public ArrayList<String> matchesArray = new ArrayList<String>();
	public ArrayList<String> wordArray = new ArrayList<String>();
	public static ArrayList<String> verbArray = new ArrayList<String>();
	public static ArrayList<String> adjectiveArray = new ArrayList<String>();
	public static ArrayList<String> nounArray = new ArrayList<String>();
	public static ArrayList<String> adverbArray = new ArrayList<String>();
	public int posCount = 0;
	public int negCount = 0;
	public int neutCount = 0;
	public int ambiCount = 0;
	public int matchCount;
	
	static void saveWord(String word, String type)
	{
		//saveWord is used to store any matches in the relevant array
		if (type.equals("verb")) { verbArray.add(word); }
		else if (type.equals("adverb")) { adverbArray.add(word); }
		else if (type.equals("adjective")) { adjectiveArray.add(word); }
		else if (type.equals("noun")) { nounArray.add(word); }
	}
	
	void updateRating(String emotion, String token, String type)
	{
		// updateRating handles all the rating and storing data in the relevant arrays
		if (!matchesArray.isEmpty()) {
			matchCount = matchesArray.size()+1;
		} else { matchCount = 1; }
		String lastToken = null;
		if (!wordArray.isEmpty() && !matchesArray.isEmpty())
		{
			// take note of the last token added to the array
			  lastToken = wordArray.get(wordArray.size()-1);
		}
			if (emotion.equals("positive-emotion") && !token.equals(lastToken))
			{
				// If the emotion is positive and the token isn't the same as the last save the token, its emotion and type. Calculate the rating
				rating = rating + 1;
				matchesArray.add(token+" ~ Positive ~ Current Rating: "+ (double)Math.round((rating/matchCount) * 100000) / 100000);
				wordArray.add(token);
				Rating.saveWord(token, type);
				posCount++;
			}
			else
			if (emotion.equals("negative-emotion") && !token.equals(lastToken))
			{
				// If the emotion is negative and the token isn't the same as the last save the token, its emotion and type. Calculate the rating
				rating = rating - 1;	
				matchesArray.add(token+" ~ Negative ~ Current Rating: "+ (double)Math.round((rating/matchCount) * 100000) / 100000);
				wordArray.add(token);
				Rating.saveWord(token, type);
				negCount++;
			}
			else
			if (emotion.equals("neutral-emotion") && !token.equals(lastToken))
			{
				// If the emotion is neutral and the token isn't the same as the last save the token and type
				neutCount++;
				Rating.saveWord(token, type);
			}
			else
			if (emotion.equals("ambiguous-emotion") && !token.equals(lastToken))
			{
				// If the emotion is neutral and the token isn't the same as the last save the token and type
				ambiCount++;
				Rating.saveWord(token, type);
			}
	}
	
	void getRating() throws IOException
	{
		// getRating is used to print out all the data collected during the analysis in a readable format
		Tokens.MyProgressKeeper.setProcessingEndTime(System.currentTimeMillis());
		for (int j = 0; j < 100; j++) { System.out.println(); }
		System.out.println("*********************************************************************************");
		System.out.println();
	    System.out.println("It took " + Tokens.MyProgressKeeper.getElapsedTime() + " to process " + Main.MyTokenizer.tokenArray.size() + " tokens.");
	    System.out.println();
	    System.out.println("In total " + (posCount+negCount+neutCount+ambiCount) + " words were matched which were split into 4 emotions:");
	    System.out.println("Positive Words: " + posCount + " ~ Negative Words: " + negCount + " ~ Neutral Words: " + neutCount + " ~ Ambiguous Count: " + ambiCount);
	    System.out.println("Verbs: "+verbArray.size()+" ~ Nouns: "+nounArray.size()+" ~ Adjectives: "+adjectiveArray.size()+" ~ Adverbs: "+adverbArray.size());
		System.out.println();
		System.out.println("*********************************************************************************");
		System.out.println();
		System.out.println("Looking at just the Positive & Negative words there were " + matchCount + " matches.");
		System.out.println();
		if (!matchesArray.isEmpty()) {
			matchCount = matchesArray.size();
		} else { matchCount = 1; }
		rating = (rating/matchCount);
		rating = (double)Math.round(rating * 100000) / 100000;
		if (rating > 0.8) { System.out.println("The Text Was Very Positive ~ Rating: "+(rating)); }
		else if (rating > 0.3) { System.out.println("The Text Was Positive ~ Rating: "+(rating)); }
		else if (rating > 0) { System.out.println("The Text Was Slightly Positive ~ Rating: "+(rating)); }
		else if (rating < 0) { System.out.println("The Text Was Slightly Negative ~ Rating: "+(rating)); }
		else if (rating < -0.3) { System.out.println("The Text Was Negative ~ Rating: "+(rating)); }
		else if (rating < -0.8) { System.out.println("The Text Was Very Negative ~ Rating: "+(rating)); }
		else { System.out.println("The Text Was Neutral ~ Rating: "+rating); }
		System.out.println();
		System.out.println("*********************************************************************************");
		System.out.println();
		System.out.println("Do you want to see the matching words? (y/n)");
		String input = myInput.readLine();
		
		if (input.equals("y"))
		{
			for (String s : matchesArray)
			{
				System.out.println(s);
			}
		}	
	}
}