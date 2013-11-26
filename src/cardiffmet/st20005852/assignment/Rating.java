package cardiffmet.st20005852.assignment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Rating {
	
	BufferedReader myInput = new BufferedReader(new InputStreamReader(System.in));
	
	public double rating = 0;
	
	public ArrayList<String> matchesArray = new ArrayList<String>();
	public ArrayList<String> wordArray = new ArrayList<String>();
	
	public static ArrayList<String> verbArray = new ArrayList<String>();
	public static ArrayList<String> adjectiveArray = new ArrayList<String>();
	public static ArrayList<String> nounArray = new ArrayList<String>();
	public static ArrayList<String> adverbArray = new ArrayList<String>();
	
	public int matchCount;
	
	static void saveWord(String word, String type)
	{
		if (type.equals("verb"))
		{
			verbArray.add(word);
		} else
		if (type.equals("adverb"))
		{
			adverbArray.add(word);
		} else
		if (type.equals("adjective"))
		{
			adjectiveArray.add(word);
		} else
		if (type.equals("noun"))
		{
			nounArray.add(word);
		}
	}
	
	void updateRating(String emotion, String token, String type)
	{
		if (!matchesArray.isEmpty()) {
			matchCount = matchesArray.size()+1;
		} else { matchCount = 1; }
		String lastToken = null;
		if (!wordArray.isEmpty() && !matchesArray.isEmpty()) {
			  lastToken = wordArray.get(wordArray.size()-1);
			  //System.out.println(lastToken);
			}
			if (emotion.equals("positive-emotion") && !token.equals(lastToken))
			{
				rating = rating + 1;
				matchesArray.add(token+" ~ Positive ~ Current Rating: "+ (double)Math.round((rating/matchCount) * 100000) / 100000);
				wordArray.add(token);
				Rating.saveWord(token, type);
			}
			else
			if (emotion.equals("negative-emotion") && !token.equals(lastToken))
			{
				rating = rating - 1;	
				matchesArray.add(token+" ~ Negative ~ Current Rating: "+ (double)Math.round((rating/matchCount) * 100000) / 100000);
				wordArray.add(token);
				Rating.saveWord(token, type);
			}
			
	}
	
	void getRating() throws IOException
	{
		System.out.println();
		System.out.println("*********************************************************************************");
		System.out.println();
		if (!matchesArray.isEmpty()) {
			matchCount = matchesArray.size();
		} else { matchCount = 1; }
		rating = (rating/matchCount);
		rating = (double)Math.round(rating * 100000) / 100000;
		if (rating > 0.8)
		{
			System.out.println("The Text Was Very Positive ~ Rating: "+(rating)+" ~ Matches: "+matchCount);
		}
		else if (rating > 0.3)
		{
			System.out.println("The Text Was Positive ~ Rating: "+(rating)+" ~ Matches: "+matchCount);
		}
		else if (rating > 0)
		{
			System.out.println("The Text Was Slightly Positive ~ Rating: "+(rating)+" ~ Matches: "+matchCount);
		}
		else if (rating < 0)
		{
			System.out.println("The Text Was Slightly Negative ~ Rating: "+(rating)+" ~ Matches: "+matchCount);
		}
		else if (rating < -0.3)
		{
			System.out.println("The Text Was Negative ~ Rating: "+(rating)+" ~ Matches: "+matchCount);
		}
		else if (rating < -0.8)
		{
			System.out.println("The Text Was Very Negative ~ Rating: "+(rating)+" ~ Matches: "+matchCount);
		}
		else
		{
			System.out.println("The Text Was Neutral ~ Rating: "+(rating)+" ~ Total Matches: "+matchCount);
		}
		System.out.println();
		System.out.println("Verbs: "+verbArray.size()+" ~ Nouns: "+nounArray.size()+" ~ Adjectives: "+adjectiveArray.size()+" ~ Adverbs: "+adverbArray.size());
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
