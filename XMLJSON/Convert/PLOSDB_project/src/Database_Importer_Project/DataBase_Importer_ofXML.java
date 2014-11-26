package Database_Importer_Project;

import java.io.File;

public class DataBase_Importer_ofXML {

    public static void main(String[] args)
    {
    	String xmlextension;
    	String srcpath=args[0];// path where to get the  reduce xml file
    	
    	String destpath=args[1];
    	// path where to save the  json file
    	
    	String filename="";
    	File folder=new File(srcpath);
    	File[] listoffile=folder.listFiles();
    	System.out.println(listoffile.length);
    	int i=0;
    	for (File file:listoffile)
    	{
         i++;
         
    	  xmlextension=file.getName().substring(file.getName().lastIndexOf(".")+1);
         if (file.isFile())
         {
        	// make sure is .xml extension before converting them into json
        	 xmlextension=file.getName().substring(file.getName().lastIndexOf(".")+1);
        	 
        	 if (xmlextension.equals("xml"))
        	 {	 
        		 System.out.println("i="+i);
        	 System.out.println(file.getName());
          
             filename =file.getName();
	         //filename=file.getName().substring(0, file.getName().lastIndexOf("."));// the name of the file would be from the 1st caracter to the caracter before 
	         System.out.println(filename);
	      
	         XMLtoJSON xx = new XMLtoJSON();
	        xx.getXMLfromJson(filename,srcpath,destpath);// a function of class XMLtoJSon, save a json string version of the xml file filename in a local file
	      
        	 }// end if
        	}// end if
    	 }// end for
    	//System.out.println("successfully save xml file as json file");
    	}// end main



}
