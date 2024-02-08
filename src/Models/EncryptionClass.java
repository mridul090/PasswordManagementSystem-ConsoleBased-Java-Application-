package Models;

import java.io.Serializable;

public class EncryptionClass implements Serializable{
	private String originalPassword;
	private String encryptedPassword;
	private String passwordTypeName;
	
	private static EncryptionClass enryp;
	
	public static EncryptionClass getEncryption(String originalPassword, String encryptedPassword, String passwordTypeName) {
		if (enryp == null) {
			enryp = new EncryptionClass();
		}
		enryp.originalPassword = originalPassword;
		enryp.encryptedPassword = encryptedPassword;
		enryp.passwordTypeName = passwordTypeName;
		
		return enryp;
	}

	public String getOriginalPassword() {
		return originalPassword;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public String getPasswordTypeName() {
		return passwordTypeName;
	}

	public void setOriginalPassword(String originalPassword) {
		this.originalPassword = originalPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public void setPasswordTypeName(String passwordTypeName) {
		this.passwordTypeName = passwordTypeName;
	}
	
	
}
