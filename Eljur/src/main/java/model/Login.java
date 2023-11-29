package model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Login {
	@NotEmpty(message="AdminName can not be empty")
	@Size(min=5, max=25, message="AdminName must be between 5 to 25 characters")
	private String adminName;
	@NotEmpty(message="Password can not be empty")
	@Size(min=7, max=25, message="Password must be between 7 to 25 characters")
	private String password;
	
	public String getAdminName() {
		return adminName;
	}
	
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
