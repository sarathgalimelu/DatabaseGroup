package XMLConverter_Project;

import java.io.File;

public class List_File {
	
	public void List_File_Mthd(String path)
	{
		String filename="";
		int count=0;// count number of time got a xml file
		File folder=new File(path);
		File[] listofFile = folder.listFiles();// listFile() return the file in Path, listofFile is an array containing all content of the path
		
		for (File file:listofFile)// for each file in array of file call listofFile
		{
			if (file.isFile() && file.getName().endsWith(".xml"))// if it is a file and it end with .xml
			{
				System.out.println(file.getName());
				count=count+1;
				//------------------------------------------------------------//
                // Step 1:  Call convertXSLT method in XSLTJava class to convert the XML article in the path to simplified form.              //
                //------------------------------------------------------------// 
  	             // remove extension .xml on file name before using to convert because the ConvertXslT function take file name without extension
								
				     
				  filename= file.getName().toString(); 
				   System.out.println(filename);
				    XSLTJava xsl = new XSLTJava();
                    xsl.convertXSLT(filename,path);// the xml file is converted into another xml.xml file using xslt, convertXLT function is a XSLTJava class
			    // }
			}//end if file.isFile()
		}// end for
		if (count==0)
		{
			System.out.println("There is no XML file in this directory");
		}	
		
		
	}

}
