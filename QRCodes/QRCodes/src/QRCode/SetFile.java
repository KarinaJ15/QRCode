package QRCode;

import java.io.File;  
import java.io.IOException;  
import java.util.HashMap;  
import java.util.Map;  
import com.google.zxing.BarcodeFormat;  
import com.google.zxing.EncodeHintType;  
import com.google.zxing.MultiFormatWriter;  
import com.google.zxing.NotFoundException;  
import com.google.zxing.WriterException;  
import com.google.zxing.client.j2se.MatrixToImageWriter;  
import com.google.zxing.common.BitMatrix;  
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;  

public class SetFile    
{ 
	//main() method  
	public static void main(String args[]) throws WriterException, IOException, NotFoundException  
		{  
		String str = "whats up";  
		String path = "C:\\Users\\kjzay\\OneDrive\\Desktop\\PayChex\\abc.png";  
		String charset = "UTF-8";  
		
		String status = generateQRcode(str, path, charset);
		System.out.println(status);  

	
		}  
	
	//static function that creates QR Code  
	public static String generateQRcode(String data, String path, String charset) throws WriterException, IOException  
		{  
		//the BitMatrix class represents the 2D matrix of bits  
		//MultiFormatWriter is a factory class that finds the appropriate Writer subclass 
		//for the BarcodeFormat requested and encodes the bar-code with the supplied contents.  
		BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, 100, 100);  
		MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path)); 
		
		return "QR CODE HAS BEEN CREATED";
		
		}  
	
	public static void placeOnPDF() {
		
	}

}  



/*package getSetQRCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.ByteArrayOutputStream;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;


public class SetFile {

	public static void main( String[] args) throws Exception {
		
		// call func
		// call other func
		//
		//
	
		//payroll("hh");
		print();
		
	}
	
	public static void payroll(String string) {
		
	
		// function to find uniqueID created from date time
		

		String IdentifyingText = "";
		String UniqueID = "";
		String FileName = "";
		String DateTime = ""; 
		String DateStampedWithQR = "";
		int NumberOfPages = 0;
		String InfoOnQRCode = "Unique ID : " + UniqueID + ", Identifying Text : " +
				IdentifyingText + ", File Name : " + FileName + NumberOfPages;
		
		System.out.println(InfoOnQRCode);
		
	
	}
	
	public static void print() throws Exception {
		String x = "MY name iss Azeem Cole";
		ByteArrayOutputStream out = QRCode.from(x).to(ImageType.JPG).stream();
		
		File f = new File("C:\\Users\\azeem\\Pictures\\MyChannel.jpeg");
		
		FileOutputStream fos = new FileOutputStream(f);
		
		fos.write(out.toByteArray());
		//fos.flush();
		fos.close();
		
		//System.out.println("jj");
		
		
	}

	
	
	
}*/
