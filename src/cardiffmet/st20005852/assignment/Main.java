package cardiffmet.st20005852.assignment;

import java.io.FileInputStream;
import java.io.IOException;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.data.Synset;
import net.sf.extjwnl.dictionary.Dictionary;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import java.util.concurrent.TimeUnit;


public class Main {
	
	public static SynsetSearch MySynset = new SynsetSearch();
	public static HierarchySearch MyHierarchy = new HierarchySearch();
	
	static Noun MyNoun = new Noun();

	public static void main(String[] args) throws IOException, JWNLException, ParserConfigurationException, SAXException, XPathExpressionException{
		
		MySynset.buildNoun();
		MySynset.buildAdverb();
		MySynset.buildVerb();
		MySynset.buildAdjective();
		
		MyHierarchy.buildHeirarchy();
		
		final long startTime = System.currentTimeMillis();
		
		String brFile = "C:/Users/Brendan/Documents/University/OO Sys 2/workspace/Assignment/res/review6.txt";
		
		Tokens MyTokenizer = new Tokens();
		MyTokenizer.runTokenizer(brFile);
		
		System.out.println("Text File Read");
		System.out.println();
		System.out.println("Beginning Analysis");
		
		final long xpathStartTime = System.currentTimeMillis();
		
		double tokenCount = 0.0;
		double lastpercent = 0;
		System.out.println("Progress: 0%");
		
		// Initialize the WordNet Dictionary.
        String propsFile = "C:/Users/Brendan/Documents/University/OO Sys 2/workspace/Assignment/res/file_properties.xml";
        Dictionary dictionary = Dictionary.getInstance(new FileInputStream(propsFile));
		         
		        		for (String a : MyTokenizer.tokenArray)
		        		{
		        			String token = a;
		        			
		        			tokenCount = tokenCount+1;
		        			
		        			double progress = tokenCount/MyTokenizer.tokenArray.size();
		        			int percent = (int) Math.round(progress*100.0f);
		        			
		        			if (percent != lastpercent)
		        			{  
		        				double timeRemainingPercent = (100.0/percent)-1;
		        				final long currTime = System.currentTimeMillis();
		        				long timeElapsed = (currTime - xpathStartTime);
		        				
		        				double timeRemaining = (timeElapsed*timeRemainingPercent);
		        				
		        				String timeRemainingReadable = String.format("%d min, %d sec", 
		        					    TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining),
		        					    TimeUnit.MILLISECONDS.toSeconds((long) timeRemaining) - 
		        					    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining))
		        					);
		        				
		        				System.out.println("Progress: " + percent + "% - Est Time Remaining: " + timeRemainingReadable);
		
		        				lastpercent = percent;
		        			}
		        			
		        			
			                
			                //System.out.println(dictionary);
			             
			                
			                // Look up the synset for a word.
			                //System.out.println(token);
			               
			                IndexWord noun = dictionary.getIndexWord(POS.NOUN, token);
			                IndexWord adjective = dictionary.getIndexWord(POS.ADJECTIVE, token);
			                IndexWord adverb = dictionary.getIndexWord(POS.ADVERB, token);
			                IndexWord verb = dictionary.getIndexWord(POS.VERB, token);

			                if(noun != null)
			                {
			                	MyNoun.getIsa(noun, null, token, "noun");
				                
			                }
		        			//System.out.println();
		        			
		        			if(adjective != null)
			                {
				                for (Synset synset : adjective.getSenses()) {
				                  //System.out.println(token + " = " + synset.getGloss());
				                  //System.out.println(synset.getOffset());
				                	
				                	int length = (int) Math.log10(synset.getOffset()) + 1;
				                	int numzeros = 8-length;
				                	
				                	String zeros = null;
				                	if (numzeros == 0) { zeros =  ""; }
				                	if (numzeros == 1) { zeros =  "0"; }
				                	if (numzeros == 2) { zeros =  "00"; }
				                	if (numzeros == 3) { zeros =  "000"; }
				                	if (numzeros == 4) { zeros =  "0000"; }
				                	if (numzeros == 5) { zeros =  "00000"; }
				                	
				                	String adjoffset = "a#"+zeros+synset.getOffset(); 
				                	
				                	//System.out.println(offset);
				                	
				                	String categ = Main.MySynset.getAdjectiveId(adjoffset);
				                	if (categ != null)
				                	{
				                		//String categ = theNode.getAttributes().getNamedItem("noun-id").getNodeValue();
				                		//System.out.println("********** Adjective Offset "+synset.getOffset()+" has category " + categ +" **********");
				                		
				                		
					                	MyNoun.getIsa(null, categ, token, "adjective");
				                	}
				                
				                }
				               
			                } 
		        			//System.out.println();
		        			
		        			if(adverb != null)
		        			{
				                for (Synset synset : adverb.getSenses()) {
				                  //System.out.println(token + " = " + synset.getGloss());
				                  //System.out.println(synset.getOffset());
				                	
				                	int length = (int) Math.log10(synset.getOffset()) + 1;
				                	int numzeros = 8-length;
				                	
				                	String zeros = null;
				                	if (numzeros == 0) { zeros =  ""; }
				                	if (numzeros == 1) { zeros =  "0"; }
				                	if (numzeros == 2) { zeros =  "00"; }
				                	if (numzeros == 3) { zeros =  "000"; }
				                	if (numzeros == 4) { zeros =  "0000"; }
				                	if (numzeros == 5) { zeros =  "00000"; }
				                	
				                	String advoffset = "r#"+zeros+synset.getOffset(); 
				                	
				                	//System.out.println(offset);
				                	
				                	String categ = Main.MySynset.getAdverbId(advoffset);
				                	if (categ != null)
				                	{
				                		//String categ = theNode.getAttributes().getNamedItem("noun-id").getNodeValue();
				                		//System.out.println("********** Adverb Offset "+synset.getOffset()+" has category " + categ +" **********");
					                	MyNoun.getIsa(null, categ, token, "adverb");
				                	}

				                }
				               
			                }
		        			//System.out.println();
		        			
		        			if(verb != null)
		        			{
				                for (Synset synset : verb.getSenses()) {
				                  //System.out.println(token + " = " + synset.getGloss());
				                  //System.out.println(synset.getOffset());
				                	
				                	int length = (int) Math.log10(synset.getOffset()) + 1;
				                	int numzeros = 8-length;
				                	
				                	String zeros = null;
				                	if (numzeros == 0) { zeros =  ""; }
				                	if (numzeros == 1) { zeros =  "0"; }
				                	if (numzeros == 2) { zeros =  "00"; }
				                	if (numzeros == 3) { zeros =  "000"; }
				                	if (numzeros == 4) { zeros =  "0000"; }
				                	if (numzeros == 5) { zeros =  "00000"; }
				                	
				                	String verboffset = "v#"+zeros+synset.getOffset(); 
				                	
				                	//System.out.println(offset);
				                	
				                	String categ = Main.MySynset.getVerbId(verboffset);
				                	if (categ != null)
				                	{
				                		//String categ = theNode.getAttributes().getNamedItem("noun-id").getNodeValue();
				                		//System.out.println("********** Verb Offset "+synset.getOffset()+" has category " + categ +" **********");
	
					                	MyNoun.getIsa(null, categ, token, "verb");
				                	}

				                }
				                
			                }
		        			//System.out.println();
		        		}
		         
	    
	    
	    final long endTime = System.currentTimeMillis();
	    
	    long timeElapsed = (endTime - startTime);
	    
	    String timeElapsedReadable = String.format("%d min, %d sec", 
			    TimeUnit.MILLISECONDS.toMinutes(timeElapsed),
			    TimeUnit.MILLISECONDS.toSeconds(timeElapsed) - 
			    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeElapsed))
			);
	    
	    System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("*********************************************************************************");
		System.out.println();
	    System.out.println("Time Taken: " + timeElapsedReadable);
	    
	    Xpath.getRating();
		

	}
	
}

