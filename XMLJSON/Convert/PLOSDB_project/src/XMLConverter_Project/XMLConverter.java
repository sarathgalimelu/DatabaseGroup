package XMLConverter_Project;



public class XMLConverter {

	public static void main(String[] args) 
    {
	 List_File ListF=new List_File();
	 //the path where xml file are is given as argument to the java program,
	 String path=args[0];
	 ListF.List_File_Mthd(path);
	 
	 System.out.println("");
     System.out.println("XML convertion of articles done!!!");
 
   	 }// end of main




}