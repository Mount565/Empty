package mount.mail;

/**
 * Client only has to use this helper class to send mails.
 * 
 * @author mount
 * 
 */
public class MailSenderHelper {

	public static void sendMail(String content, String subject,
			String contentType, String... recipients) {
		MailSender ms = new MailSender();
		ms.setContentType(contentType);
		ms.setMailContent(content);
		ms.setRecipient(recipients);
		ms.setSubject(subject);
		new Thread(ms).start();
	}
}
