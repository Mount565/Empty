package mount.mail;


public interface MailService {

	public String sendTo(String content, String subject, String contentType,
			String... recipients);
	
	 
}
