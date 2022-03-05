package QRCode;

import java.awt.image.BufferedImage;
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.IOException;  
import java.util.HashMap;
import java.util.List;
import java.util.Map;  
import javax.imageio.ImageIO;  
import com.google.zxing.BinaryBitmap;  
import com.google.zxing.EncodeHintType;  
import com.google.zxing.MultiFormatReader;  
import com.google.zxing.NotFoundException;  
import com.google.zxing.Result;  
import com.google.zxing.WriterException;  
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;  
import com.google.zxing.common.HybridBinarizer;  
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;  

import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper; 
import org.apache.pdfbox.text.PDFTextStripperByArea;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



public class ReadFile   
{  
	public static void main(String args[]) throws Exception 
		{

		//These are the codes for the all the differnt files times
		List<String> fileIDs = List.of("PEO074","DP0002","phb011", "W-4");
		
		//This location of the file we want to check(might  need to change if file si arg[])
		String fileName = "C:\\Users\\kjzay\\OneDrive\\Desktop\\PayChex\\DD1.pdf";
		File file = new File(fileName);
		
		//document object from apache package that has methods to ana;lyze pdf file
		PDDocument document = Loader.loadPDF(file);
		String text = "";
		
		
		if (!document.isEncrypted()) {
		    PDFTextStripper stripper = new PDFTextStripper();

			//gets the text from the pdf file
		    text = stripper.getText(document).toLowerCase();

			//prints ou the pdf file
		    System.out.println("Text:" + text);  
		}
		
		
		document.close();
		//check for language

		List<String> englishCommons = List.of(" the "," be ","to ",  " of ", " and ", " a "," in ", " that ", " have ", " i ");
		List<String> spanishCommons = List.of(" de " ," la "," que ", " el ", " en ",  " y ", " a ",  " los ", " se ", " del ");

		int englishWords = 0;
		int spanishWords = 0;
			
		for (int x = 0; x <= 9; x++){

			spanishWords += (text.length() - text.replaceAll(spanishCommons.get(x),"").length()) / spanishCommons.get(x).length();
			englishWords += (text.length() - text.replaceAll(englishCommons.get(x),"").length()) / englishCommons.get(x).length();
			
		} 

		//System.out.println(englishWords + " x " + spanishWords);

		String language = "";

		if(spanishWords > englishWords){
			language = "Spanish";
		} 

		if(spanishWords < englishWords){
			language = "English";
		} 

		if(spanishWords == englishWords){
			language = "undecided";
		} 

			
		//check which id is in the was in the pdf string

		String PDFID = "";
		for (String i : fileIDs) { 
			if (text.contains(i.toLowerCase())) {
				PDFID = i;	
			}
			
		}

		
		//if the file is scanned back to us, there will not be text in the meta data
		//the pdf file will basically be an image
		//... since the file was scanned to us,  we assume it was signed
		//then send it to the function to check for QRCode and Signature
		//or/
		// send it to function to past a qr code

		if(text.length() == 0){
			//if this tree send to function to check for sig. and qrc
			System.out.println("Returning file. Looking for signature.");
			status(List.of(PDFID, language, fileName));
	
		} else {

			System.out.println("Returning file. Looking for signature.");
			//status(List.of(PDFID, language, fileName));

		}
		
	}
	
	//This is the Status function
	//checks weather the file is signed or not

	//**Plan */
	//Turn the file to an image then check the pixels of the area markings

	public static void status( List<String> info) throws Exception 
	{

		// Loader.loadPDF("C:\\Users\\azeem\\Pictures\\DD1.pdf")
		String file = info.get(2);

		PDDocument document =  Loader.loadPDF(new File(file));
		
		PDFRenderer pdfRenderer = new PDFRenderer(document);
		for (int page = 0; page < document.getNumberOfPages(); ++page)
		{ 
		    BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);

		    // suffix in filename will be used as the file format
		    ImageIOUtil.writeImage(bim, "C:\\Users\\azeem\\Pictures\\jj" + "-" + (page+1) + ".png", 300);
		}
		document.close();
	    

	}

		
		
		
}