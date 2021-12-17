package constants;

import java.io.File;

public class GetDirectory {
	
	public static String[] getAllFileName(String pathname) {

		File f = new File(pathname);

		return f.list();
	}

}
