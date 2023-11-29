package model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import model.Login;

@Component
public class LoginDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public boolean isExists(Login login) {
		List<Login> admins = jdbcTemplate.query("SELECT * FROM admins WHERE adminName = ? AND password = ?", new BeanPropertyRowMapper<>(Login.class), login.getAdminName(), login.getPassword());
		boolean result = false;
		if(admins.size() != 0) {
			result = true;
		}
		return result;
	}
}
