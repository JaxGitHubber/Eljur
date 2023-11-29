package model;

public class Timetable {
	private int weekday;
	private String date;
	private String lessons;
	private String hw;
	
	public int getWeekday() {
		return weekday;
	}
	
	public void setWeekday(int weekday) {
		this.weekday = weekday;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getLessons() {
		return lessons;
	}
	
	public void setLessons(String lessons) {
		this.lessons = lessons;
	}
	
	public String getHw() {
		return hw;
	}
	
	public void setHw(String homework) {
		this.hw = homework;
	}
}
