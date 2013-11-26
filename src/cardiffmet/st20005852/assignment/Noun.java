package cardiffmet.st20005852.assignment;


import java.io.FileNotFoundException;
import java.io.IOException;

import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.Synset;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

public class Noun {
	
	Xpath myXpath = new Xpath();

	IndexWord showInput(IndexWord noun)
	{
		//System.out.println(noun);
		return noun;
	}

	String getIsa(IndexWord noun, String id, String token, String type) throws ParserConfigurationException, FileNotFoundException, SAXException, IOException, XPathExpressionException
	{
		
		//System.out.println(noun);
		
		if (noun != null)
		{
			for (Synset synset : noun.getSenses())
			{
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
	          	
	          	String offset = "n#"+zeros+synset.getOffset();
	          	
	          	//System.out.println(offset);
	          	
	          	
	          	myXpath.getXpath(offset, token, type);
	          	
	          	
	          }
		}
		
		//System.out.println(id);
		
		if (id != null)
		{
          	myXpath.getXpath(id, token, type);
		}
		
		
		return null;
		
	}
	
}
