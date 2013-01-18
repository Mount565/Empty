package mount.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mount.annotation.Controller;
import mount.annotation.Request;
import mount.dao.LiteDao;
import mount.util.SqlPropertiesUtil;

@Controller
public class UserController {

	@Request(name = "/showUser.html")
	public void showUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		LiteDao liteDao = new LiteDao();
		List<Map<String, Object>> list = liteDao.doSelectList(SqlPropertiesUtil
				.getProperty("select.user.all"));

		request.setAttribute("userList", list);

		request.getRequestDispatcher("WEB-INF/page/user.jsp").forward(request,
				response);

	}
}
