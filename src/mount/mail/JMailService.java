package mount.mail;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mount.util.DESCryptoUtility;

public class JMailService implements MailService {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final String THE_KEY = "AFFFEFDFWSEEFSJHKYUTYUU";

	private Properties ps = null;

	public JMailService() {
		// Gmail config
		ps = MailConstant.getGmailProperties();

	}

	@Override
	public String sendTo(String content, String subject, String contentType,
			String... recipients) {

		if (recipients == null) {
			return null;
		}
		String cType = "text/plain;charset=utf-8";
		if (contentType != null) {
			cType = contentType;
		}

		JMailAuthenticator au = new JMailAuthenticator();

		try {
			String p = DESCryptoUtility.decrypt(MailConstant.MAIL_PASSWORD,
					THE_KEY);
			logger.debug("password is :" + p);
			au.setPassword(p);
			au.setUserName(MailConstant.MAIL_USER_NAME);

			Session mc = Session.getDefaultInstance(ps, au);

			mc.setDebug(true);
			Address[] as = new InternetAddress[recipients.length];

			for (int i = 0; i < recipients.length; i++) {
				as[i] = new InternetAddress(recipients[i]);
			}
			MimeMessage msg = new MimeMessage(mc);

			msg.setContent(content, cType);
			msg.setRecipients(Message.RecipientType.TO, as);
			msg.setSubject(subject);
			logger.debug("Begin Transporting...");
			Transport.send(msg);
			logger.debug("Transporting end...");
			return "Y";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}

	}

}
