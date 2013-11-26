package cardiffmet.st20005852.assignment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;




//import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class Tokens {
	
	ArrayList<String> tokenArray = new ArrayList<String>();
	
	void runTokenizer(String brFile) throws IOException
	{
BufferedReader br = new BufferedReader(new FileReader(brFile));
		
		
		
		System.out.println("Reading Text File");
		
		try {
	        String line = br.readLine();
	        InputStream is = new FileInputStream("C:/Users/Brendan/Documents/University/OO Sys 2/workspace/Sentiment-Barometer/res/en-token.bin");
    		TokenizerModel model = new TokenizerModel(is);
    		TokenizerME tokenizer = new TokenizerME(model);
    		
			while (line != null) {
					                
					            if (line != null)
					        	{
					        		String tokens[] = tokenizer.tokenize(line);
					         
					        		for (String a : tokens)
					        		{
					        			tokenArray.add(a);
					        		}
					        	}
					            
					            line = br.readLine();
					         
			}
		}
		 finally {
		        br.close();
		    }
}
	
}
