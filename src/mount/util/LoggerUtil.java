package mount.util;

import org.slf4j.Logger;

public class LoggerUtil {

	public static void trace(Logger logger, String str) {
		if (logger.isTraceEnabled()) {
			logger.trace(str);
		}
	}

	public static void debug(Logger logger, String str) {
		if (logger.isDebugEnabled()) {
			logger.debug(str);
		}
	}

	public static void warn(Logger logger, String str) {
		if (logger.isWarnEnabled()) {
			logger.warn(str);
		}
	}

	public static void info(Logger logger, String str) {
		if (logger.isInfoEnabled()) {
			logger.info(str);
		}
	}

	public static void error(Logger logger, String str) {
		if (logger.isErrorEnabled()) {
			logger.error(str);
		}
	}

}
