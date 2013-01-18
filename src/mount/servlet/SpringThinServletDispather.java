package mount.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mount.listener.SpringThinContextListener;
import mount.util.LoggerUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class SpringThinServletDispather
 */
public class SpringThinServletDispather extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = LoggerFactory
			.getLogger(SpringThinServletDispather.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SpringThinServletDispather() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String rUri = request.getRequestURI();
		rUri = rUri.substring(rUri.lastIndexOf("/"));
		LoggerUtil.debug(logger, "request uri is:" + rUri);

		String cl = SpringThinContextListener.CLASS_MAP.get(rUri);
		String mth = SpringThinContextListener.METHOD_MAP.get(rUri);

		if (cl == null || mth == null) {
			return;
		}
		Class<?>[] c = new Class[2];
		c[0] = HttpServletRequest.class;
		c[1] = HttpServletResponse.class;

		try {
			Class<?> lc = Class.forName(cl);
			Method m = lc.getMethod(mth, c);
			m.invoke(lc.newInstance(), request, response);

		} catch (ClassNotFoundException e) {
			LoggerUtil.error(logger, "exception occurred: " + e.getMessage());

		} catch (NoSuchMethodException e) {
			LoggerUtil.error(logger, "exception occurred: " + e.getMessage());

		} catch (SecurityException e) {
			LoggerUtil.error(logger, "exception occurred: " + e.getMessage());

		} catch (IllegalAccessException e) {
			LoggerUtil.error(logger, "exception occurred: " + e.getMessage());

		} catch (IllegalArgumentException e) {
			LoggerUtil.error(logger, "exception occurred: " + e.getMessage());

		} catch (InvocationTargetException e) {
			LoggerUtil.error(logger, "exception occurred: " + e.getMessage());

		} catch (InstantiationException e) {
			LoggerUtil.error(logger, "exception occurred: " + e.getMessage());

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
