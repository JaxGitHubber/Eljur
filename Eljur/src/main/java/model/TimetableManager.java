package model;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import model.dao.TimetableDAO;

@Component
public class TimetableManager {
	@Autowired
	private TimetableDAO timetableDAO;
	
	public Timetable getTimetable(LocalDate date, boolean direction) {
		Timetable timetable;
		if(date == null) {
		timetable = find(LocalDate.now(), direction);
		} else {
		timetable = find(date, direction);
		}
		return timetable;
	}
	
	private Timetable find(LocalDate date, boolean direction) {
		Timetable timetable;
		if(direction) {
		  timetable = findForRightMoving(date);
		} else {
			timetable = findForLeftMoving(date);
		}
		return timetable;
	}
	
	private Timetable findForRightMoving(LocalDate date) {
		date = getValidDateForRightMoving(date);
		  if(!timetableDAO.isExists(date.toString())) {
				LocalDate dayOfWorkWeek = date.minusDays(date.getDayOfWeek().ordinal());
				while(dayOfWorkWeek.getDayOfWeek() != DayOfWeek.SATURDAY) {
					timetableDAO.addTimetable(dayOfWorkWeek);
					dayOfWorkWeek = dayOfWorkWeek.plusDays(1);
				}
			}
			return timetableDAO.getTimetable(date.toString());
	}
	
	private LocalDate getValidDateForRightMoving(LocalDate date)  {
		if(date.getDayOfWeek() == DayOfWeek.SATURDAY) {
			date = date.plusDays(2);
		  } else if(date.getDayOfWeek() == DayOfWeek.SUNDAY) {
			date = date.plusDays(1);
		  }
		return date;
	}
	
	private Timetable findForLeftMoving(LocalDate date) {
	 date = getValidDateForLeftMoving(date);
	  if(!timetableDAO.isExists(date.toString())) {
		  return find(date.plusDays(1), true);
	  }
	    return timetableDAO.getTimetable(date.toString());
	}
	
	private LocalDate getValidDateForLeftMoving(LocalDate date)  {
		if(date.getDayOfWeek() == DayOfWeek.SUNDAY) {
			date = date.minusDays(2);
	  }
		return date;
	}
}
