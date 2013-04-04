package is.illuminati.block.hephaestus.util;

import is.illuminati.block.hephaestus.data.LogHeader;
import is.illuminati.block.hephaestus.data.Well;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.idega.util.IWTimestamp;

@Service("hephaestusUtil")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class HephaestusUtil {

	public static String concat(String arg1, String arg2) {
		return arg1.concat(arg2);
	}

	public static String formatDate(Date date, String pattern) {
		if (date != null) {
			return new IWTimestamp(date).getDateString(pattern);
		}
		return "";
	}

	public static String escapeXML(String string) {
		string = StringEscapeUtils.unescapeHtml(string.replaceAll("\\<[^>]*>",
				""));
		return string;
	}

	public static boolean contains(List<?> objects, Object object) {
		boolean contains = objects.contains(object);
		return contains;
	}

	@SuppressWarnings("unused")
	public static String getImageForWell(Well well, List<LogHeader> headers) {
		try {
			ProcessBuilder pb = new ProcessBuilder("/bin/ksh", 
					"/home/andri/goramyndir/goramyndir.sh", "-s "
							+ well.getName());
			pb.directory(new File("/home/ubuntu/images"));
			Process p = pb.start();
			try {  
	            int shellExitStatus = p.waitFor();  
	            if (shellExitStatus != 0) {  
	            	System.out.println("Successfully executed the shell script");  
	            }  
	        } catch (InterruptedException ex) {  
	        	System.out.println("Shell Script preocess is interrupted");  
	        }  
			System.out.println("Process done");
			///usr/lib/gmt/bin/ps2raster
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}
	
/*	public static String getImageForWell(String wellname, List<Long> headerID) {
		try {
			ProcessBuilder pb = new ProcessBuilder("/home/andri/goramyndir/goramyndir.sh", "-s "
							+ wellname);
			pb.directory(new File("/home/ubuntu/images"));
			Process p = pb.start();
			try {  
	            int shellExitStatus = p.waitFor();  
	            if (shellExitStatus != 0) {  
	            	System.out.println("Successfully executed the shell script");  
	            }  
	        } catch (InterruptedException ex) {  
	        	System.out.println("Shell Script preocess is interrupted");  
	        }  
			System.out.println("Process done");
			
			///usr/lib/gmt/bin/ps2raster
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return "";		
	}*/

	public static String getImageForWell(String wellname, List<Long> headerID) {
		String line = "/home/andri/goramyndir/goramyndir.sh -s " + wellname;
		CommandLine cmdLine = CommandLine.parse(line);
		DefaultExecutor executor = new DefaultExecutor();
		try {
			int exitValue = executor.execute(cmdLine);
			System.out.println("exitValue = " + exitValue);
		} catch (ExecuteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		line = "/usr/lib/gmt/bin/ps2raster plot_gmt.ps -Tg -Fpalli.png";
		cmdLine = CommandLine.parse(line);
		try {
			int exitValue = executor.execute(cmdLine);
			System.out.println("exitValue = " + exitValue);
		} catch (ExecuteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";		
	}

	public static void main(String args[]) {
		System.out.println("test");
		String test = getImageForWell("23-33", null);
		System.out.println("test = " + test);
	}
}