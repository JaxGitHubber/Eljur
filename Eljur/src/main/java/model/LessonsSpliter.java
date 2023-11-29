package model;

import org.springframework.stereotype.Component;

@Component
public class LessonsSpliter implements ISpliter {
	public String[] split(String lessons) {
		return lessons.split(";");
	}
}
