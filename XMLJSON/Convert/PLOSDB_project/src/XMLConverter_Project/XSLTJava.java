package XMLConverter_Project;
import javax.xml.transform.*;

import java.io.*;


public class XSLTJava {
	public void convertXSLT(String filename, String path) {
		  try {

				
			     String s=path+"/"+filename;
			     System.out.println(s);		 
	            File inputFile = new File(s);// a file with .xml is create with each doi as name in the current directory
	            File tempFile = new File(path+"/"+"tempFile.xml");// a tempfile is also created

	             BufferedReader reader = new BufferedReader(new FileReader(inputFile)); 
	             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile)); 
	             String lineToRemove = "<!DOCTYPE article PUBLIC";
	             
	           
	             String currentLine;

	            while((currentLine = reader.readLine()) != null)// readline return Null or a string
	             {
	            	 //------------------------------------------------------------//
			         //  Comment the DOCTYPE line in Article to convert to simplified XML              //
			         //------------------------------------------------------------// 
		        	 
	                 if(currentLine.contains(lineToRemove)) 
	                 {
	                	 // replace doctype line by a comment line because we don't handle the DTD
	                	 currentLine = currentLine.replaceAll("!DOCTYPE", "!--DOCTYPE");
	                	 currentLine = currentLine.replaceAll(">", "-->");
	                	 writer.write(currentLine);
	                 }
	                 //the current line read from filename is written in tempfile 
	                 writer.write(currentLine);
	             }   
	           
			  writer.close();
			  reader.close(); // added 
			 
			// creation of the xsl file 
			  TransformerFactory tFactory = TransformerFactory.newInstance();
			  Transformer transformer =
		      tFactory.newTransformer
		         (new javax.xml.transform.stream.StreamSource
		            (path+"/"+"front.xsl"));
		    
		    //creation of the The simplified XML file name
			  
		    File file1 = new File(path+"/ReduceXML/"+filename+"XML.xml");
		    
		    //transformation of filename into new xml file using the XSLT file
		    transformer.transform
		      (new javax.xml.transform.stream.StreamSource
		            (path+"/tempFile.xml"),
		       new javax.xml.transform.stream.StreamResult
		            ( new FileOutputStream(file1)));
		            		    }
		  catch (Exception e) {
		    e.printStackTrace( );
		    }
		  
		  }
}
