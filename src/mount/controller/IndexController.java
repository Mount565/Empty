package mount.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mount.annotation.Controller;
import mount.annotation.Request;
import mount.mail.MailSenderHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class IndexController {

	final static Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Request(name = "/index.html")
	public void initIndex(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	@Request(name = "/sendMail.html")
	public void sendMail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.debug("entered method handleSendMailRequest");

		String recipient = request.getParameter("recipient");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");

		MailSenderHelper.sendMail(content, subject, null, recipient);
		request.setAttribute("msg", "mail added to send mail qune");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
