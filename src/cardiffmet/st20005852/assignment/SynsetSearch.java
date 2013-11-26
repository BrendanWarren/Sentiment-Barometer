package cardiffmet.st20005852.assignment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.xpath.XPath;



public class SynsetSearch {
	
	DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
	
	XPathFactory xpathFactory = XPathFactory.newInstance();
	XPath xpath = xpathFactory.newXPath();
	
	ArrayList<String> nounOffsetArray = new ArrayList<String>();
	ArrayList<String> nounCategArray = new ArrayList<String>();
	
	ArrayList<String> adverbOffsetArray = new ArrayList<String>();
	ArrayList<String> adverbIdArray = new ArrayList<String>();
	
	ArrayList<String> verbOffsetArray = new ArrayList<String>();
	ArrayList<String> verbIdArray = new ArrayList<String>();
	
	ArrayList<String> adjectiveOffsetArray = new ArrayList<String>();
	ArrayList<String> adjectiveIdArray = new ArrayList<String>();
	
	void buildNoun() throws FileNotFoundException, SAXException, IOException, ParserConfigurationException, XPathExpressionException
	{
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document document = builder.parse(new FileInputStream("C:/Users/Brendan/Documents/University/OO Sys 2/wn-domains-3.2/wn-affect-1.1/a-synsets.xml"));
		
		 NodeList nounSynsets = (NodeList) xpath.evaluate("/syn-list/noun-syn-list/noun-syn", document, XPathConstants.NODESET);
		    System.out.println("Found " + nounSynsets.getLength() + " elements.");

		    for (int i = 0; i < nounSynsets.getLength(); i++) {
		    	Node item = nounSynsets.item(i);
		    	nounOffsetArray.add(item.getAttributes().getNamedItem("id").getNodeValue());
		    	nounCategArray.add(item.getAttributes().getNamedItem("categ").getNodeValue());
		    }
	}
	
	void buildAdverb() throws FileNotFoundException, SAXException, IOException, ParserConfigurationException, XPathExpressionException
	{
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document document = builder.parse(new FileInputStream("C:/Users/Brendan/Documents/University/OO Sys 2/wn-domains-3.2/wn-affect-1.1/a-synsets.xml"));
		
		
		
		 NodeList adverbSynsets = (NodeList) xpath.evaluate("/syn-list/adv-syn-list/adv-syn", document, XPathConstants.NODESET);
		    System.out.println("Found " + adverbSynsets.getLength() + " elements.");

		    for (int i = 0; i < adverbSynsets.getLength(); i++) {
		    	Node item = adverbSynsets.item(i);
		    	adverbOffsetArray.add(item.getAttributes().getNamedItem("id").getNodeValue());
		    	adverbIdArray.add(item.getAttributes().getNamedItem("noun-id").getNodeValue());
		    }
	}
	
	void buildVerb() throws FileNotFoundException, SAXException, IOException, ParserConfigurationException, XPathExpressionException
	{
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document document = builder.parse(new FileInputStream("C:/Users/Brendan/Documents/University/OO Sys 2/wn-domains-3.2/wn-affect-1.1/a-synsets.xml"));
		
		
		
		 NodeList verbSynsets = (NodeList) xpath.evaluate("/syn-list/verb-syn-list/verb-syn", document, XPathConstants.NODESET);
		    System.out.println("Found " + verbSynsets.getLength() + " elements.");

		    for (int i = 0; i < verbSynsets.getLength(); i++) {
		    	Node item = verbSynsets.item(i);
		    	verbOffsetArray.add(item.getAttributes().getNamedItem("id").getNodeValue());
		    	verbIdArray.add(item.getAttributes().getNamedItem("noun-id").getNodeValue());
		    }
	}
	
	void buildAdjective() throws FileNotFoundException, SAXException, IOException, ParserConfigurationException, XPathExpressionException
	{
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document document = builder.parse(new FileInputStream("C:/Users/Brendan/Documents/University/OO Sys 2/wn-domains-3.2/wn-affect-1.1/a-synsets.xml"));
		
		
		
		 NodeList adjectiveSynsets = (NodeList) xpath.evaluate("/syn-list/adj-syn-list/adj-syn", document, XPathConstants.NODESET);
		    System.out.println("Found " + adjectiveSynsets.getLength() + " elements.");

		    for (int i = 0; i < adjectiveSynsets.getLength(); i++) {
		    	Node item = adjectiveSynsets.item(i);
		    	adjectiveOffsetArray.add(item.getAttributes().getNamedItem("id").getNodeValue());
		    	adjectiveIdArray.add(item.getAttributes().getNamedItem("noun-id").getNodeValue());
		    }
	}
	
	
	String getCateg(String offset)
	{
	int exist = -1;
		for(int i=0;i<nounOffsetArray.size();i++){
			//System.out.println(nounOffsetArray.get(i) + " " + offset);
	        if(nounOffsetArray.get(i).equals(offset)){
	            exist=i;
	            break;
	        }
		}
		
	
		if(exist != -1 ) {
		    return nounCategArray.get(exist);
		} else {
		    return null;
		}
	}
	
	String getAdverbId(String offset)
	{
	int exist = -1;
		for(int i=0;i<adverbOffsetArray.size();i++){
			//System.out.println(nounOffsetArray.get(i) + " " + offset);
	        if(adverbOffsetArray.get(i).equals(offset)){
	            exist=i;
	            break;
	        }
		}
		
	
		if(exist != -1 ) {
		    return adverbIdArray.get(exist);
		} else {
		    return null;
		}
	}
	
	String getVerbId(String offset)
	{
	int exist = -1;
		for(int i=0;i<verbOffsetArray.size();i++){
			//System.out.println(nounOffsetArray.get(i) + " " + offset);
	        if(verbOffsetArray.get(i).equals(offset)){
	            exist=i;
	            break;
	        }
		}
		
	
		if(exist != -1 ) {
		    return verbIdArray.get(exist);
		} else {
		    return null;
		}
	}
	
	String getAdjectiveId(String offset)
	{
	int exist = -1;
		for(int i=0;i<adjectiveOffsetArray.size();i++){
			//System.out.println(nounOffsetArray.get(i) + " " + offset);
	        if(adjectiveOffsetArray.get(i).equals(offset)){
	            exist=i;
	            break;
	        }
		}
		
	
		if(exist != -1 ) {
		    return adjectiveIdArray.get(exist);
		} else {
		    return null;
		}
	}

}
