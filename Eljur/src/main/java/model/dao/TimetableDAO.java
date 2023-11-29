package model.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import model.Timetable;

@Component
public class TimetableDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public boolean isExists(String currentDate) {
		List<Timetable> currentTimetable = jdbcTemplate.query("SELECT date FROM homework WHERE date = ?", new BeanPropertyRowMapper<>(Timetable.class), currentDate);
		boolean result = false;
		if(currentTimetable.size() != 0) {
			result = true;
		}
		return result;
	}
	
	public Timetable getTimetable(String currentDate) {
		List<Timetable> timetables = jdbcTemplate.query("SELECT homework.weekday, homework.date, timetable.lessons, homework.hw FROM homework LEFT JOIN timetable ON homework.weekday = timetable.weekday WHERE date = ?", new BeanPropertyRowMapper<>(Timetable.class), currentDate);
		return timetables.get(0);
	}
	
	public void addTimetable(LocalDate dayOfWorkWeek) {
		jdbcTemplate.update("INSERT INTO homework (weekday, date) VALUES (?, ?)", (dayOfWorkWeek.getDayOfWeek().ordinal()+1), dayOfWorkWeek.toString());
	}
	
	public void addHomework(String homework, String date) {
		jdbcTemplate.update("UPDATE homework SET hw = ? WHERE date = ?", homework, date);
	}
}
