package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder implements IPasswordEncoder {
	public String encode(final String password) {
		try {
		MessageDigest message = MessageDigest.getInstance("SHA-256");
		StringBuilder securePassword = new StringBuilder();
		for(byte encodedChar : message.digest(password.getBytes())) {
			securePassword.append(encodedChar);
		}
		return securePassword.toString();
		} catch(NoSuchAlgorithmException e) {
			return null;
		}
	}
}
