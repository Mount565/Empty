package mount.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class JMailAuthenticator extends Authenticator {

	private String userName;
	private String password;

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(this.getUserName(), this
				.getPassword());
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
