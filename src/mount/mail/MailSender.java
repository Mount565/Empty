package mount.mail;

import mount.util.LoggerUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * take charge of sending mail
 * 
 * @author bingwei Wang
 * 
 */
public class MailSender implements Runnable {

	/** Logger for this class and subclasses */
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MailService mailService;

	private String mailContent;
	private String subject;
	private String contentType;
	private String[] recipient;

	public MailSender() {
		mailService = new JMailService();

	}

	@Override
	public void run() {
		LoggerUtil.debug(logger, "started mail send thread.");
		String msg = mailService.sendTo(mailContent, subject, contentType,
				recipient);
		LoggerUtil.info(logger, "Got status:" + msg + " for email:" + recipient
				+ ". subject:" + subject);
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public MailService getMailService() {
		return mailService;
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String[] getRecipient() {
		return recipient;
	}

	public void setRecipient(String[] recipient) {
		this.recipient = recipient;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}
