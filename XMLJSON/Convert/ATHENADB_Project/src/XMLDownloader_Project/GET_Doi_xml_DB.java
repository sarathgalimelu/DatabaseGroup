package XMLDownloader_Project;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class GET_Doi_xml_DB 
   {
	
	public void GET_Doi_xml_DBmthod(String strIds2)
	  {
		String filename="";  
	
	String PMCURL = " http://www.pubmedcentral.nih.gov/utils/idconv/v1.0/?ids="+strIds2+"&format=xml";
    System.out.println("The URL to Pubmed Central database is"+PMCURL);
  	 	        	
  	 URL urlnew;
	try {
		urlnew = new URL(PMCURL);
	    
       HttpURLConnection conn = (HttpURLConnection) urlnew.openConnection(); 
       
		conn.setRequestMethod("GET");
	
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) 
		{
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));
		
		 DocumentBuilderFactory dbFactory1 = DocumentBuilderFactory.newInstance();
       DocumentBuilder dBuilder1;
	
		dBuilder1 = dbFactory1.newDocumentBuilder();
	
       Document doc1;
	
		doc1 = dBuilder1.parse(new InputSource(br));
	
       doc1.getDocumentElement().normalize();
       // record all <record /> element in the node list Idlist1
       NodeList IdList1 = doc1.getElementsByTagName("record");
       //System.out.println("No of record nodes"+IdList1.getLength());
       // got through the nodellist and get attribute with value doi
       for(int i=0; i<IdList1.getLength() ; i++) 
         {
      	  
      	 Node aNode = IdList1.item(i);
      	 NamedNodeMap attributes = aNode.getAttributes();// get all attributes of the node itemi "record"
      	 Node n = attributes.getNamedItem("doi"); // get the attribute with attribute name " doi"
      	 
      	 //------------------------------------------------------------//
	         // Step 1:  Get the DOIs if they are not null                                   //
	         //------------------------------------------------------------//
      	 
           if(n!=null)
             {
      		 
  			 String doi=		 n.getNodeValue();// get the value the DOI attribute if it is nt null
	        	 
  		        	 
  	       //------------------------------------------------------------//
             // Step 2:  Create the URL to download article                                  //
             //------------------------------------------------------------//       	
  	 
  	         String FrontierURL = "http://journal.frontiersin.org/Journal/"+doi+"/xml";
  	         // System.out.println(FrontierURL);
  	
  	         // management of the file name
  	         filename = doi.substring(8);// extraction of the characters from position 8 up to the end of the DOI to do the file name with
  	 
  	        //------------------------------------------------------------//
              // Step 3:  If the DOI format is correct,download the article                                 //
              //------------------------------------------------------------// 
  	  
  	          //String DOIcheck =filename.substring(0,4); // extraction of character of the filename variable from 0 to the 4th characters
  	        
  	         saveUrl(filename,FrontierURL);
			// a static method of this class is called to save the content returned by frontierurl in to file name
	           System.out.println("The doi for dowloaded article is " +doi+",");
	           	          
  	       /* if(DOIcheck.startsWith("fn"))
  	        {
  		 
  	          
				saveUrl(filename,FrontierURL);
			// a static method of this class is called to save the content returned by frontierurl in to file name
  	           System.out.println("The doi for dowloaded article is " +doi+",");
  	           	          
      	      
  	         }// end if start with fn
  	          //------------------------------------------------------------//
                // Step 8:  Print the DOIS with different format                                 //
               //------------------------------------------------------------//
  	       else // if the doi doesn't start with "fn"
  	         {
  		      System.out.println("The DOIs in different format is "+filename);
  	         }
  	        */ 
            }// end if !null
      	 //------------------------------------------------------------//
	         // Step 5:  Print the pmids with null DOIs                                  //
	         //------------------------------------------------------------//
         else{
      		 Node pmid=attributes.getNamedItem("pmid");
      		 String pmidVal=		 pmid.getNodeValue();
	        	 
	        	 System.out.println("The pmid that could not be converted to DOI are " +pmidVal+",");
            }
          }// end for
       			        	 	        
          	
     		 conn.disconnect();
     		System.out.println(" ");
	        System.out.println("THE XML VERSION OF ARTICLE WITH THE CRITERIA HAVE BEEN DOWNLOADED");
           
        	
   
	} catch (MalformedURLException e1)//catch exception when using URL()
    {
	 // TODO Auto-generated catch block
	 e1.printStackTrace();
   } catch (ProtocolException e)// catch exception when using SetRequestMethod("GET")
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}catch (IOException e)// catch exception on saveurl()
	 {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	catch (ParserConfigurationException e)// catch exception on newDocumentbuilder
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	catch (SAXException e)// catch excpetion  when using dBuilder1.parse(new InputSource(br))
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}   
	
}// end method

public static void saveUrl(final String filename, final String urlString)// this void save the content of xml downloaded from frontiers into a new xml file
        throws MalformedURLException, IOException
          {
    BufferedInputStream in = null;
    FileOutputStream fout = null;
    try {
        in = new BufferedInputStream(new URL(urlString).openStream());// convert the Url to the frontiers xml articles into an url readable for java, and a bufferedinputstream is open on it to read the content an copy
       
        
        File file = new File(filename+".xml");
        
        fout = new FileOutputStream(file);//a fileoutputstream is open on the destination file for write the content read in it

        final byte data[] = new byte[1024];
        int count;
        while ((count = in.read(data, 0, 1024)) != -1) {// while it can read from the xml intitial file it 
            fout.write(data, 0, count); // write it in the new xml file
        }
    } finally {
        if (in != null) {
            in.close();
        }
        if (fout != null) {
            fout.close();
        }
    }
 }// end saveurl()


}// end class


