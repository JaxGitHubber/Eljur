package model;

public class DayOfWeekConverter {
	public String getDayOfWeek(int dayOfWeek) {
		return switch(dayOfWeek) {
		case 1 -> "Monday";
		case 2 -> "Tuesday";
		case 3 -> "Wednesday";
		case 4 -> "Thursday";
		case 5 -> "Friday";
		default -> null;
		};
	}
}
