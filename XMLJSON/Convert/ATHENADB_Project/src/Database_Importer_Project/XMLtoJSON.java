package Database_Importer_Project;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;



//import to save json string into file
import java.io.FileWriter;



public class XMLtoJSON {
    private static final int PRETTY_PRINT_INDENT_FACTOR = 0;
	
    private InputStream inputStream = null;   
    public String getXMLfromJson(String filename,String srcpath, String destpath) 
      {
    	String jsonPrettyPrintString ="";
        try{
        	
        	FileInputStream  file =new FileInputStream (srcpath+"/"+filename);
     
            String xml = IOUtils.toString(file);// allow to get the content of file which is an input stream as a string, here is an xml string
            try
            {
            
            JSONObject xmlJSONObj = XML.toJSONObject(xml);// take the xml string convert into jsonObject
            jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);// take that jsonobject and return the Json string version of the original xml file
            
            
            filename=filename.substring(0,16);                             
            System.out.println("the json test: "+jsonPrettyPrintString); 
            
              
                          
            // save the json file in local
            try {
            	 
        		FileWriter filejson = new FileWriter(destpath+"/"+filename+".json");
        		filejson.write(jsonPrettyPrintString);
        		filejson.flush();
        		filejson.close();
        		System.out.println("Successfully Copied JSON Object to File...");
         
        	} catch (IOException e) {
        		e.printStackTrace();
        	}
     
            
            
            
            
            }catch (JSONException je) {
                System.out.println(je.toString()); }
            
            
        }catch(Exception e){
            e.printStackTrace();
                
        }
        
        finally{
     try {
                if (inputStream != null) {
                    inputStream.close();
                }
               
            } catch (IOException ex) {}
        }
        return jsonPrettyPrintString;
    }
   
}


