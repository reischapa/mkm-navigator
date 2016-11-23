
package sheetsoft.com.mkmnavigator.android.backend;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


public class ProgramOutput {

	public static boolean saveTextToFile(String source, File destination){
		try {
			destination.createNewFile();
			FileWriter fw = new FileWriter(destination);
			fw.write(source);
			fw.close();
			return true;
		} catch (IOException e) {}
		return false;
	}




	public static String allThingsOnline(String url, File root) {
		URL imgsource = null;
		InputStream is = null;
        int bufferLen = 512;
		byte[] b = new byte[bufferLen];
		FileOutputStream fout = null;
		String filename = "foo";//SearchPageMKM.returnImageExtension(url,"/");
		String filepath = root + File.separator + filename;
		File outputFile = new File(filepath);
		int count=0;
		try{
			imgsource = new URL(url);
		} catch (MalformedURLException e){
        }
		try {
			is =  imgsource.openStream();
			fout = new FileOutputStream(outputFile);
			while ((count = is.read(b, 0, bufferLen))!=-1){
				fout.write(b, 0, count);
			}
			fout.close();
			is.close();	
		} catch (IOException e) {
			return "void";
		}		
			return filename;
	}


	
	
}
