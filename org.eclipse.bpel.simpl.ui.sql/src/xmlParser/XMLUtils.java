package xmlParser;

import java.io.IOException;
import java.net.URL;

import org.eclipse.bpel.simpl.ui.sql.Activator;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

public class XMLUtils {
		
	public static String getURLFromPath(String path){
		Bundle bundle = Activator.getDefault().getBundle();
		URL url = bundle.getEntry(path);
		URL realUrl = null;
			try {
				realUrl = Platform.resolve(url);
				System.out.println("real URL: " + realUrl.getPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		if (realUrl != null){
			return realUrl.getPath();
		}else {
			return "";
		}
	}
}
