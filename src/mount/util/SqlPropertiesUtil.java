package mount.util;

import java.util.Properties;

public class SqlPropertiesUtil {


	private static Properties p;

	public static void setProperties(Properties pro) {
		p = pro;
	}

	public static String getProperty(String pName) {
		return p.getProperty(pName);
	}
}
