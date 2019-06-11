package demo.htmltopdf;

import com.lowagie.text.DocumentException;
import org.apache.commons.io.FileUtils;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AlternativePDFGeneration {

	public static void main(String... args) throws Exception {
		
    	HashMap<String, String> map = new HashMap<String, String>();
    	
    	map.put("<$key3$>", "value3");
    	map.put("<$key4$>", "value4");
    	map.put("<$key5$>", "/home/narendrac/dev/POC/codes/htmltopdf/src/resources/flamingo.jpg");

		String str = readFileAsString("/home/narendrac/dev/POC/codes/htmltopdf/src/resources/testhtml2.html");
		str = replaceTokensWithData(map, str);
		// FileUtils.writeByteArrayToFile(new File("So4712641.pdf"), toPdf("<b>You gotta
		// walk and don't look back</b>"));

		FileUtils.writeByteArrayToFile(new File("/home/narendrac/dev/POC/codes/htmltopdf/src/resources/alternative.pdf"), toPdf(str));
	}


	private static byte[] toPdf(String html) throws DocumentException, IOException {
		final ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(html);
		renderer.layout();
		try (ByteArrayOutputStream fos = new ByteArrayOutputStream(html.length())) {
			renderer.createPDF(fos);
			return fos.toByteArray();
		}
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
			System.out.println(str);
		}
		return str;
	}

}