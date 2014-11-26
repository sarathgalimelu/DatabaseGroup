package loadXML;

import java.io.BufferedInputStream;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

//import java.util.ArrayList;
//import java.util.List;







import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
//import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLDownloader_PLOS {

	public static void main(String[] args) 
    {
	 
  
 
      URL u;
      InputStream is = null;
      String filename = "";
      int count = 0;
      int null_doi=0;
      
      
      String keyword;
      
      String[] doi = new String[5000];
      int k=0;
    	      
      
      //------------------------------------------------------------//
      // Step 1: Scanning the search keyword and the journal name           //
      //------------------------------------------------------------//
      
      
      System.out.println("You entered string "+args[0]);
      keyword = args[0];
      try {
 
         
       
    	  StringBuilder url = new StringBuilder ("http://api.plos.org/search/?q="+keyword+"&fl=id&fq=doc_type:full&rows=250&start=0&api_key=ZujkgeGCnz4nSoyjJp5");
    	 
             // url would content a xml file contain tag among which idlist tag with id as child , which are the pubmedID
    	 u = new URL(url.toString());
    	 
    	 System.out.println("url to download the articles in PLOS is: \n "+url);
 
         //----------------------------------------------//
         // Step 3:  Open an input stream from the url.  //
         //----------------------------------------------//
 
         is = u.openConnection().getInputStream();// a connection is open to the URL and an InputStream is return for reading from that connection 
         // throws an IOException
         // the url is parse as Doc, 
         
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();// get an instance of a factory that can give us a document builder
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();// create the new doc builder
         
             // using of  the documentBuilder to parse  as a document the inputstream return when open a connection to read the content of URL
             Document doc = dBuilder.parse(is);
         
             
             NodeList docu = doc.getElementsByTagName("str");
             NodeList result = doc.getElementsByTagName("result");
             String valnumFound= ((Element)result.item(0)).getAttribute("numFound");
             
             
             int numFound=Integer.parseInt(valnumFound);
            
             for(int i=0; i< docu.getLength();i++) 
              {
            	 if (((Element) docu.item(i)).hasAttribute("name") &&  ((Element)docu.item(i)).getAttribute("name").equals("id"))// check if the elt <str> has attribute called name with value "id",
            		                                                                                                             //in order for the nodelist.item(i) to use method hasattribute and getattribute , the nodelist item need to be cast into type Element
            	  {
            		
            		 
            		 
            		doi[k]= docu.item(i).getTextContent();
            		k=k+1;
            
            		
            		
            		
            	 }
              }
              
             // reading of the content of doi array
            int size_k = doi.length;
             int z=0;
             for (int j=0; j<size_k;j++)
             {
            	 System.out.println("DOI is: "+doi[j]); 
            	 
            	 if(doi[j]!=null)
            	 {
            	 String PLOSURL = "http://www.plosone.org/article/fetchObjectAttachment.action?uri=info%3Adoi%2F"+doi[j]+"&representation=XML";
            	 filename = doi[j].substring(8);
            	 String DOIcheck =filename;
            	 if(DOIcheck.startsWith("journal.pone"))
      	         {
      
      	         
    				count = count+1;
    			// a static method of this class is called to save the content returned by PLOSurl in to file name
      	          System.out.println("The doi for dowloaded article is " +doi[j]+",");
      	         
            	 saveUrl(filename,PLOSURL);
            	 z=z+1;
      	         }
            	 } 
             }
             System.out.println("amount of DOI  for this page is: "+z);
             System.out.println("but we have a total of : " + numFound+ " articles with this criteria");
             
             int i = 0;
             for(i=250;i<numFound;i=i+250)
             {
            	 StringBuilder url1 = new StringBuilder ("http://api.plos.org/search/?q="+keyword+"&fl=id&fq=doc_type:full&rows=250&start="+i+"&api_key=ZujkgeGCnz4nSoyjJp5");
            	 
                     
            	 u = new URL(url1.toString());
            	 
            	 System.out.println("url to download the articles in PLOS is: \n "+url1);
         
                 //----------------------------------------------//
                 // Step 3:  Open an input stream from the url.  //
                 //----------------------------------------------//
         
                 is = u.openConnection().getInputStream();// a connection is open to the URL and an InputStream is return for reading from that connection 
                 // throws an IOException
                 // the url is parse as Doc, 
                 
                 DocumentBuilderFactory dbFactory1 = DocumentBuilderFactory.newInstance();// get an instance of a factory that can give us a document builder
                 DocumentBuilder dBuilder1 = dbFactory1.newDocumentBuilder();// create the new doc builder
                 
                     // using of  the documentBuilder to parse  as a document the inputstream return when open a connection to read the content of URL
                     Document doc1 = dBuilder1.parse(is);
                 
                     
                     NodeList docu1 = doc1.getElementsByTagName("str");
                     NodeList result1 = doc1.getElementsByTagName("result");
                     String valnumFound1= ((Element)result1.item(0)).getAttribute("numFound");
                     
                     
                     int numFound1=Integer.parseInt(valnumFound1);
                     k=i;
                     for(int i1=0; i1< docu1.getLength();i1++) 
                      {
                    	
                    	 if (((Element) docu1.item(i1)).hasAttribute("name") &&  ((Element)docu1.item(i1)).getAttribute("name").equals("id"))// check if the elt <str> has attribute called name with value "id",
                    		                                                                                                             //in order for the nodelist.item(i) to use method hasattribute and getattribute , the nodelist item need to be cast into type Element
                    	  {
                    		
                    	    
                    	   
                    		doi[k]= docu1.item(i1).getTextContent();
                    		k=k+1;
                    		
                    		
                    		
                    		
                    	   
                    	    
                    		
                    	 }
                      }
                      
                     // reading of the content of doi array
                     int size_k1 = doi.length;
                     int z1=0;
                     for (int j=i; j<size_k1;j++)
                     {
                    	 System.out.println("DOI is: "+doi[j]); 
                    	 
                    	 if(doi[j]!=null)
                    	 {
                    	 String PLOSURL = "http://www.plosone.org/article/fetchObjectAttachment.action?uri=info%3Adoi%2F"+doi[j]+"&representation=XML";
                    	 filename = doi[j].substring(8);
                    	 String DOIcheck =filename;
                    	 if(DOIcheck.startsWith("journal.pone"))
              	         {
              
              	         count = count+1;
            				
            			
              	          System.out.println("The doi for dowloaded article is " +doi[j]+",");
              	         
                    	 saveUrl(filename,PLOSURL);
                    	 z1=z1+1;
              	         }
                     }
                     }
                     
                     
                     System.out.println("amount of DOI  for this page is: "+z1);
                     System.out.println("but we have a total of : " + numFound1+ " articles with this criteria");
                     
             }
             
             
            } catch (MalformedURLException mue) {
 
         System.out.println("Ouch - a MalformedURLException happened.");
         mue.printStackTrace();
         System.exit(1);
         
    	 
 
      } 
      
      
      
      catch (IOException ioe) {
 
         System.out.println("Oops- an IOException happened.");
         ioe.printStackTrace();
         System.exit(1);
 
      } catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
 
         //---------------------------------//
         // Step 6:  Close the InputStream  //
         //---------------------------------//
 
         try {
            is.close();
         } catch (IOException ioe) {
            
         }
 
      } // end of 'finally' clause

  
  System.out.println("Total number of articles downloaded:"+count);
  System.out.println("Total number of articles without doi:"+null_doi);
	
    }// end of main
	
	public static void saveUrl(final String filename, final String urlString)// this void save the content of xml downloaded from PLOS into a new xml file
	        throws MalformedURLException, IOException
	          {
	    BufferedInputStream in = null;
	    FileOutputStream fout = null;
	    try {
	        in = new BufferedInputStream(new URL(urlString).openStream());// convert the Url to the PLOS xml articles into an url readable for java, and a bufferedinputstream is open on it to read the content an copy
	       
	        
	        File file = new File(filename+".xml");
	       
	        fout = new FileOutputStream(file);//a fileoutputstream is open on the destination file for write the content read in it

	        final byte data[] = new byte[1024];
	        int count;
	        while ((count = in.read(data, 0, 1024)) != -1) {
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
	 }



}
