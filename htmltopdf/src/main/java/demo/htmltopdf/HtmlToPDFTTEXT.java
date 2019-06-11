package demo.htmltopdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfWriter;

public class HtmlToPDFTTEXT {
	// itextpdf-5.4.1.jar http://sourceforge.net/projects/itext/files/iText/
	public static void main(String... args) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("<$key3$>", "value3");
		map.put("<$key4$>", "value4");
		map.put("<$key5$>", "/home/narendrac/dev/POC/codes/htmltopdf/src/resources/flamingo.jpg");

		String htmlFileAsString = readFileAsString("/home/narendrac/dev/POC/codes/htmltopdf/src/resources/testhtml2.html");
		String outputPath = "/home/narendrac/dev/POC/codes/htmltopdf/src/resources/testpdfX.pdf";

		genaratePDF(map, outputPath, htmlFileAsString);

	}

	private static void genaratePDF(HashMap<String, String> pdfData, String outputPath, String htmlFileAsString) {
		try {
			String htmlWithData = replaceTokensWithData(pdfData, htmlFileAsString);
			Document document = getDocumentWithDetails(outputPath);
			HTMLWorker htmlWorker = new HTMLWorker(document);
			htmlWorker.parse(new StringReader(htmlWithData));
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Document getDocumentWithDetails(String outputPath) throws FileNotFoundException, DocumentException {
		Document document = new Document(PageSize.LETTER);
		PdfWriter.getInstance(document, new FileOutputStream(outputPath));
		document.open();
		document.addAuthor("Narendra Chaudhary");
		document.addCreator("nn");
		document.addSubject("Just for time pass");
		document.addCreationDate();
		document.addTitle("This is Title");
		return document;
	}

	private static String readFileAsString(String fileName) throws Exception {
		String data = "";
		data = new String(Files.readAllBytes(Paths.get(fileName)));
		return data;
	}

	private static String replaceTokensWithData(HashMap<String, String> map, String str) {
		Iterator<Map.Entry<String, String>> itr = map.entrySet().iterator();
		while (itr.hasNext()) {
			Map.Entry<String, String> entry = itr.next();
			str = str.replace(entry.getKey().trim(), entry.getValue());
			//System.out.println(str);
		}
		return str;
	}

}