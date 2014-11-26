package XMLDownloader_Project;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLDownloader {

	public static void main(String[] args) 
    {
	 
  
 
      URL u;
      InputStream is = null;
   
      //String ss;
      
      //String filename="";
      String keyword;
      int retstart=0;
      List<Node> listIdList=new ArrayList<Node>();
    	      
      //Scanner in = new Scanner(System.in);
      //------------------------------------------------------------//
      // Step 1: Scanning the search keyword and the journal name           //
      //------------------------------------------------------------//
      
      //System.out.println("Enter a string");
      //ss = in.nextLine();
      System.out.println("You entered string "+args[0]);
      keyword = args[0];
      try {
 
         //------------------------------------------------------------//
         // Step 2:  Create the URL to access pubmed database for Pubmed Ids.                                   //
         //------------------------------------------------------------//
       // use a eUtils function of pubmed to get the 200 articles from the database Pubmed using the term enter as ss 
    	 StringBuilder url = new StringBuilder ("http://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi?db=pubmed&retstart=0&retmax=200&usehistory=n&retmode=xml&term="+keyword);
    	 
             // url would content a xml file containt tag among which idlist tag with id as child , which are the pubmedID
    	 u = new URL(url.toString());
    	 
    	 System.out.println("Url to pubmed is \n "+url);
 
         //----------------------------------------------//
         // Step 3:  Open an input stream from the url.  //
         //----------------------------------------------//
 
         is = u.openConnection().getInputStream();// a connection is open to the URL and an InputStream is return for reading from that connection 
         // throws an IOException
         // the url is parse as Doc, 
         
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();// get an instance of a factory that can give us a document builder
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();// create the new doc builder
         // the first 200 PMID would be store in a node
             // using of  the documentBuilder to parse  as a document the inputstream return when open a connection to read the content of URL
             Document doc = dBuilder.parse(is);
         
             // this return a nodelist with id element: the xml doc is parse and all element with name id are store in to idlist a nodelist, id is the PMID
             NodeList IdList = doc.getElementsByTagName("Id");
            
             Node root=doc.getDocumentElement();// get the root element ( <esearch>) of the xml file return by eutils functions 
             Node nodecount=root.getFirstChild();// access the first child(<count>) of the root element 
             String valnodecount=nodecount.getTextContent();
             System.out.println("value of count is : " + valnodecount);
                      
             NodeList noderetmaxlist=doc.getElementsByTagName("RetMax");
             String valnoderetmax = noderetmaxlist.item(0).getTextContent(); 
             System.out.println("value of retmax is  : " + valnoderetmax);
             
             // conversion of the value node of count and redmax into integer
             int count=Integer.parseInt(valnodecount);
             int total=Integer.parseInt(valnoderetmax);// a counter that would help us know when we have reached the end of our xml file, by incrementing it when getting the content
            if(count==0)
             {
              System.out.println("              ");
              System.out.println(" OUFFF!!! THERE IS NO ARTICLE WITH THIS CRITERIA.");	 
             }	
            else
            {	 
             System.out.println("the 1st itteration Url to get the PMID is"+url);
             // add the content of the first round node of PMID into the list of node
        	 for (int x=0; x<IdList.getLength();x++)
       	      {
       		   listIdList.add(IdList.item(x));
       	      }	 
             
            
             
            
            // because PUBMED web site can not accept request of more than 200 PMID on their site, itteration to get the rest of 200 PMID per round
        	 if (count>total)// look like we don't need this if, would see later
               {	 
            	 int reach=0;// kind of boolean to tell us we are at the end  
            	 int t=0;// an intermediate counter to manage the last round
                 int j=1;// a counter of number of itteration to get the url
            	 while (total<=count && reach==0)// we notice hat when retstart reach count , there is no PMID id , 
            		                                   // so no need to go through the process of geting PMID if retstart=count
            	 {
            		
            		//List<Node> nodes = new ArrayList<Node>();
            	 
            	    j=j+1;
            	    retstart=total;
            		total=total+200;// at the last round retmax>count, to avoid accessing a web content that doesn't exist we give to retmax, count at that last
            		
            	    url = new StringBuilder ("http://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi?db=pubmed&retstart="+retstart+"&retmax=200&usehistory=n&retmode=xml&term="+keyword); 
                    u= new URL(url.toString());
                    System.out.println("the "+j+"itteration Url to get the PMID is"+url);
                    is= u.openConnection().getInputStream();
                    dbFactory = DocumentBuilderFactory.newInstance();// get an instance of a factory that can give us a document builder
       	            dBuilder = dbFactory.newDocumentBuilder();// create the new doc builder
       	            doc = dBuilder.parse(is);
       	            // create a temporary nodelist to get the current 200 pmid from utils function , then add the nodelist in to the list of node list
       	            NodeList IdListtemp = doc.getElementsByTagName("Id");
       	            for (int x=0; x<IdListtemp.getLength();x++)
           	         {
           		      listIdList.add(IdListtemp.item(x));// we add from the last index of the pre
           	         }	 
           	            
       	         if ( count<total)/// at the last round retmax>count or total>count, to avoid accessing a web content that doesn't exist we give to retmax, count at that last
       	            {
       	                t= t+1;
       	               if (t==1)
       	               {    
       	            	count=total;
       	                reach=1;
       	               }
       	               else
       	               {
       	            	   reach=1;
       	            	   count=Integer.parseInt(valnodecount); // to put the real value of count in place
       	               }   
       	            }	 
       	            
            	 }// end while total<count
               }// end if 	count>total 
        	 else
        	 {
        		 // if count<total only one step of pmid extraction is done
        	 } 
         // the length of the nodelist idlist containing tag of the url doc are counted in numberIds
         int numberIds = listIdList.size();
         System.out.println("Total no of PMIds : " + numberIds);
         String strIds ="";
         
         
         
         //------------------------------------------------------------//
         // Step 3:  Get the Pubmed Ids                                   //
         //------------------------------------------------------------//
          int k=0;// counter to insure we reach the end of all pubmid ID to get the DOI	
          int round=0;// round counter of extraction of doi using pmid
          //System.out.println("2nd Total no of PMIds : " + numberIds);
          for(int t=0; t<listIdList.size(); t++)// because PUbmed central that we would access to use PMID to get DOI do not accept more tha 200 at one request
          {
        	 
        	 // get the content of the node(i) of the array list of node
                     strIds += listIdList.get(t).getTextContent()+ ","; 
        	 
          }
         
         System.out.println("all pubmed Ids are " +strIds);
         System.out.println("size listidlist is "+listIdList.size());
         
         //------------------------------------------------------------//
         // Step 3:  Create the URL to access Pubmed website for convertion of the 200 Pubmed Id to DOI                                  //
         //------------------------------------------------------------//
         
         // go thtough the array list up to the lenght of the nodelist 
         
         if(count<200)// case where there less than 200 PMID, everything is done once
         {
        	GET_Doi_xml_DB DoixmlDB=new GET_Doi_xml_DB(); 
        	DoixmlDB.GET_Doi_xml_DBmthod(strIds);
           
         }
        else// case that there more than 200 pmid, DOI would be generated in a round
         {
          
          while (k<listIdList.size())
           {	 
        	  String strIds2 ="";//reinitializationof the string used to concatenate all 200 pmid in order to use in url for extracting DOI
        	   // because PUBMEDcentral web site can not accept request of more than 200 DOI on their site, itteration to get  200 DOI per round up to total
             for(int i=k; i<k+200; i++)// because PUbmed central that we would access to use PMID to get DOI do not accept more tha 200 at one request
             {
        	 
        	   // get the content of the node(i) of the array list of node
               strIds2 += listIdList.get(i).getTextContent()+ ","; 
               // normally this for should stop at i=k+200 =count-1 because there not elemnt at the last index , but sometimes k+200 >count what is not normal cause
               //count is the last existing element, in order to not reach an unexisting index at the last for round because,we are going to change value of i to k+200 if k+200>count and i=count-1
        	   if(k+200>count && i==count-1)
        	   {
        		  i= k+200; 
        	   }   
        		   
               
             }
         
              System.out.println("The"+round+"round of pubmed Ids to get DOI are" +strIds2);
                         
              GET_Doi_xml_DB DoixmlDB2=new GET_Doi_xml_DB(); 
         	  DoixmlDB2.GET_Doi_xml_DBmthod(strIds2);
             k=k+200;
             round=round+1;
           }// end while(k<listIdListsize())
         } //end else of if (count<200)
         
       }//end else de if count==0
       System.out.println("              ");  
       System.out.println(" YOU HAVE REACHED THE END  OF THE DATA  EXTRACTION USING THESE CRITERIA");
         
            } catch (MalformedURLException mue) {
 
         System.out.println("Ouch - a MalformedURLException happened.");
         mue.printStackTrace();
         System.exit(1);
 
      } catch (IOException ioe) {
 
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
            // just going to ignore this one
         }
 
      } // end of 'finally' clause
 //return filename;
  
  
	
    }// end of main



}
