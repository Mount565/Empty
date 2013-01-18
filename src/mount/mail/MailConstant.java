package mount.mail;

import java.util.Properties;

public class MailConstant {
 

	public static final String MAIL_USER_NAME = "businesscardalbum";

	public static final String MAIL_PASSWORD = "9268f857b3b3d2503b3b296444e2dfe9";

	 

	public static Properties getHotmailProperties() {
		Properties ps = new Properties();
		// Hotmail config
		ps.setProperty("mail.transport.protocol", "smtp");
		ps.setProperty("mail.host", "smtp.live.com");
		ps.put("mail.smtp.starttls.enable", "true");
		ps.put("mail.smtp.auth", "true");
		ps.put("mail.smtp.port", "587");
		ps.put("mail.smtp.socketFactory.port", "587");
		ps.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		ps.put("smtp.starttls.enable", "true");
		ps.put("mail.smtp.socketFactory.fallback", "false");
		ps.setProperty("mail.smtp.quitwait", "false");

		return ps;

	}

	public static Properties getGmailProperties() {
		Properties ps = new Properties();
		ps.put("mail.host", "smtp.gmail.com");
		ps.put("mail.smtp.starttls.enable", "true");
		ps.put("mail.smtp.port", "587");
		ps.put("mail.smtp.auth", "true");
		return ps;
	}
}
