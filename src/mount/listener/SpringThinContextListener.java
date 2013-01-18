package mount.listener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import mount.annotation.Controller;
import mount.annotation.Request;
import mount.util.LoggerUtil;
import mount.util.SqlPropertiesUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Application Lifecycle Listener implementation class
 * ServletContextListenerImpl
 * 
 */
public class SpringThinContextListener implements ServletContextListener {
	final static Logger logger = LoggerFactory
			.getLogger(SpringThinContextListener.class);

	public static final Map<String, String> CLASS_MAP = new HashMap<String, String>();
	public static final Map<String, String> METHOD_MAP = new HashMap<String, String>();

	/**
	 * Default constructor.
	 */
	public SpringThinContextListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		LoggerUtil.debug(logger, "Enter contextInitialized...");
		this.loadSQL(arg0);
		LoggerUtil.debug(logger,
				"Load SQL finished.bgeing loading controller...");
		this.loadController(arg0);
		LoggerUtil.debug(logger, "Load controller finished...");

	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	/**
	 * Load the SQL properties file in to cache.
	 * 
	 * @param ctxt
	 *            ServletContextEvent
	 */
	private void loadSQL(ServletContextEvent ctxt) {
		ServletContext sc = ctxt.getServletContext();

		String realPath = sc.getRealPath(sc
				.getInitParameter("sqlPropertisFile"));
		try {
			LoggerUtil.debug(logger, "SQL properties Real Path:" + realPath);
			FileInputStream fin = new FileInputStream(realPath);
			Properties p = new Properties();
			p.load(fin);
			SqlPropertiesUtil.setProperties(p);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadController(ServletContextEvent ctxt) {
		URL u = Thread.currentThread().getContextClassLoader().getResource("");
		this.scanClass(u.getPath(), u);

	}

	private void scanClass(String path, URL u) {
		File dir = new File(path);
		File[] files = dir.listFiles();

		if (files == null)
			return;
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				scanClass(files[i].getAbsolutePath(), u);
			} else {
				String strFileName = files[i].getAbsolutePath();
				if (strFileName.endsWith(".class")) {
					String className = this.getFullClassName(strFileName, u);
					this.fillRequestMap(className);
				}

			}
		}
	}

	private void fillRequestMap(String className) {
		Class<?> c;
		try {
			c = Class.forName(className);
			if (c.isAnnotationPresent(Controller.class)) {
				Method[] ms = c.getMethods();
				for (int i = 0; i < ms.length; i++) {
					Method m = ms[i];
					if (m.isAnnotationPresent(Request.class)) {
						Request r = m.getAnnotation(Request.class);
						String rname = r.name().trim();
						METHOD_MAP.put(rname, m.getName());
						CLASS_MAP.put(rname, className);
					}
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String getFullClassName(String fname, URL u) {
		File f = new File(u.getPath());
		String tmp = fname.replace(f.getAbsolutePath() + File.separator, "");
		return tmp.replace("\\", ".").replace(".class", "");

	}

}
