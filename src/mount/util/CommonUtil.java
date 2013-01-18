package mount.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CommonUtil {

	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static Date parseDate(String dateString, String dateFormat)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		return format.parse(dateString);
	}

	public static String formatDate(Date date, String dateFormat) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		return format.format(date);
	}

	/**
	 * test if str is null or empty
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmptyString(String str) {
		return str == null || str.trim().equals("");
	}

	/**
	 * generate the parameters that prepare statement needs.
	 * 
	 * @param objects
	 *            the objects type must be like 1,obj,2,obj...
	 * @return map
	 */
	public static Map<Integer, Object> generateParamMap(Object... objects) {

		if (objects.length % 2 != 0) {
			// throw an exception??
			return null;
		}

		Map<Integer, Object> pMap = new HashMap<Integer, Object>();
		for (int i = 0; i < objects.length; i = i + 2) {
			pMap.put((Integer) objects[i], objects[i + 1]);
		}
		return pMap;
	}
}
