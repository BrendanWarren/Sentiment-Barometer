package cardiffmet.st20005852.assignment;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.SAXException;

public class Xpath {
	
	public static Rating textRating = new Rating();
	
	static void getRating() throws IOException
	{
		textRating.getRating();
	}
	


	public static XPathFactory xpathFactory = XPathFactory.newInstance();
	public static XPath xpath = xpathFactory.newXPath();
	
	
	
		
	String getXpath(String offset, String token, String type) throws FileNotFoundException, SAXException, IOException, XPathExpressionException, ParserConfigurationException
	{
		
		
      	//System.out.println(offset);
      	String categ = Main.MySynset.getCateg(offset);
      	//System.out.println(theNode);
      	if (categ != null)
      	{
      		//String categ = theNode.getAttributes().getNamedItem("categ").getNodeValue();
      		//System.out.println("********** Offset "+synset.getOffset()+" has category " + categ +" **********");
      		
      		//categ = "//*[@name='"+categ+"']";
      		
      		String isa = Main.MyHierarchy.getIsa(categ);
      		//System.out.println(heirarchyNode);
				if (isa != null)
      		{
					//String isa = heirarchyNode.getAttributes().getNamedItem("isa").getNodeValue();
					//System.out.println("********** Category "+categ+" has ISA " + isa +" **********");
					
					//System.out.println(isa);
							
						while (!isa.equals("positive-emotion") && !isa.equals("negative-emotion") && !isa.equals("neutral-emotion") && !isa.equals("ambiguous-emotion") && !isa.equals("root"))
						{
							//String isasearch = "//*[@name='"+isa+"']";
								
								String isa2 = Main.MyHierarchy.getIsa(isa);
		                		//System.out.println(heirarchyNode2);
								//String isa2 = null;
								//if (heirarchyNode2 != null)
		                		//{
									//System.out.println(heirarchyNode2);
									//isa2 = heirarchyNode2.getAttributes().getNamedItem("isa").getNodeValue();
									//System.out.println("********** ISA "+isa+" has ISA " + isa2 +" **********");
									
									
		                		//}
								
								isa = isa2;
								
								if (isa == null) isa = "neutral-emotion";
								if (isa.equals("root")) isa = "neutral-emotion";
						}
							
					
					if (isa.equals("positive-emotion") || isa.equals("negative-emotion") || isa.equals("neutral-emotion") || isa.equals("ambiguous-emotion"))
					{
						textRating.updateRating(isa, token, type);
						
					}
					
      		}
      		
      	}
      	return null;
	}

}
