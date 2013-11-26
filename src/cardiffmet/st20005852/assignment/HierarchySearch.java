package cardiffmet.st20005852.assignment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class HierarchySearch {
	
DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
	
	XPathFactory xpathFactory = XPathFactory.newInstance();
	XPath xpath = xpathFactory.newXPath();
	
	ArrayList<String> hierarchyNameArray = new ArrayList<String>();
	ArrayList<String> hierarchyIsaArray = new ArrayList<String>();
	
	void buildHeirarchy() throws FileNotFoundException, SAXException, IOException, ParserConfigurationException, XPathExpressionException
	{
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document document = builder.parse(new FileInputStream("C:/Users/Brendan/Documents/University/OO Sys 2/wn-domains-3.2/wn-affect-1.1/a-hierarchy.xml"));
		
		 NodeList heirarchyList = (NodeList) xpath.evaluate("/categ-list/categ", document, XPathConstants.NODESET);
		    System.out.println("Found " + heirarchyList.getLength() + " elements.");

		    for (int i = 0; i < heirarchyList.getLength(); i++) {
		    	Node item = heirarchyList.item(i);
		    	if (!item.getAttributes().getNamedItem("name").getNodeValue().equals("root"))
		    	{
		    		hierarchyNameArray.add(item.getAttributes().getNamedItem("name").getNodeValue());
		    		hierarchyIsaArray.add(item.getAttributes().getNamedItem("isa").getNodeValue());
		    	}
		    }
	}
	
	String getIsa(String name)
	{
	int exist = -1;
		for(int i=0;i<hierarchyNameArray.size();i++){
			//System.out.println(nounOffsetArray.get(i) + " " + offset);
	        if(hierarchyNameArray.get(i).equals(name)){
	            exist=i;
	            break;
	        }
		}
		
	
		if(exist != -1 ) {
		    return hierarchyIsaArray.get(exist);
		} else {
		    return null;
		}
	}

}
