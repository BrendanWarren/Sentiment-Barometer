package cardiffmet.st20005852.assignment;

import java.io.FileNotFoundException;
import java.io.IOException;

import net.sf.extjwnl.JWNLException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

public class Main
{	
	/*
	 * 	Main.java
	 * 
	 * 	Runs SynsetSearch and HierarchySearch to prepare the arrays for later. Passes the text file into the Tokenizer to begin processing
	 * 	and finally calls getRating in HierarchyManager to display the results.
	 * 
	 */
	
	static SynsetManager MySynsetManager = new SynsetManager();
	static Tokens MyTokenizer = new Tokens();
	
	public static void main(String[] args) throws FileNotFoundException, XPathExpressionException, ParserConfigurationException, SAXException, IOException, JWNLException
	{	
		SynsetSearch.getSynsetInstance();
		HierarchySearch.getHierarchyInstance();
		MyTokenizer.runTokenizer("C:/Users/Brendan/Documents/University/OO Sys 2/workspace/Sentiment-Barometer/res/review6.txt");         
	    HierarchyManager.getRating();
	}
}