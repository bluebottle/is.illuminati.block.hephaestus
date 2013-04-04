package is.illuminati.block.hephaestus.util;

import is.illuminati.block.hephaestus.data.LogHeader;
import is.illuminati.block.hephaestus.data.Well;

import java.io.File;
import java.util.Date;
import java.util.List;

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

	public static String getImageForWell(Well well, List<LogHeader> headers) {
		try {
			ProcessBuilder pb = new ProcessBuilder(
					"/home/andri/goramyndir/goramyndir.sh", "-s "
							+ well.getName());
			pb.directory(new File("/home/ubuntu/images"));
			Process p = pb.start();
			int exit = p.exitValue();
			
			///usr/lib/gmt/bin/ps2raster
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}
}