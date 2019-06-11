package demo.htmltopdf;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker; // deprecated
import com.itextpdf.text.pdf.PdfWriter;

public class HtmlToPDF1 {
  // itextpdf-5.4.1.jar  http://sourceforge.net/projects/itext/files/iText/
  public static void main(String ... args ) {
    try {
    	
    	HashMap<String, String> map = new HashMap<String, String>();
    	//map.put("$key1", "value1");
    	//map.put("$key2", "value2");
    	map.put("<$key3$>", "value3");
    	map.put("<$key4$>", "value4");
    	map.put("<$key5$>", "/home/narendrac/dev/POC/PDF_generated/flamingo.jpg");
    	
    	
    	
    	
      Document document = new Document(PageSize.LETTER);
      PdfWriter.getInstance(document, new FileOutputStream("//home//narendrac//dev//POC//PDF_generated//testpdf.pdf"));
      document.open();
      document.addAuthor("Narendra Chaudhary");
      document.addCreator("nn");
      document.addSubject("Just for time pass");
      document.addCreationDate();
      document.addTitle("This is Title");

      HTMLWorker htmlWorker = new HTMLWorker(document);
      
      String str = readFileAsString("//home//narendrac//dev//POC//PDF_generated//testhtml2.html");
      
      String newStr = replaceTokensWithData(map, str);
      
      htmlWorker.parse(new StringReader(newStr));
      document.close();
      System.out.println("Done");
      }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  
	private static String readFileAsString(String fileName)throws Exception { 
    	String data = ""; 
	    data = new String(Files.readAllBytes(Paths.get(fileName))); 
	    return data; 
	}
	
    private static String  replaceTokensWithData(HashMap<String, String> map, String str) {
    	
    	
        Iterator<Map.Entry<String, String>> itr = map.entrySet().iterator(); 
          
        while(itr.hasNext()) 
        { 
             Map.Entry<String, String> entry = itr.next(); 
             
            // String tempstr = str.replaceAll(entry.getKey().trim(), entry.getValue());
             
             
           
             
             str = str.replace(entry.getKey().trim(), entry.getValue());
             
             System.out.println(str);
             
            // System.out.println("Key = " + entry.getKey() +    ", Value = " + entry.getValue()); 
        } 
    	
    	
    	
    	return str;
      } 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}