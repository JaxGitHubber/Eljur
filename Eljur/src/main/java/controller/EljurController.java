package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import model.DayOfWeekConverter;
import model.Homework;
import model.IPasswordEncoder;
import model.ISpliter;
import model.Login;
import model.Timetable;
import model.TimetableManager;
import model.dao.LoginDAO;
import model.dao.TimetableDAO;

import java.time.LocalDate;
import jakarta.validation.Valid;


@Controller
public class EljurController {
	@Autowired
	private LoginDAO loginDAO;
	@Autowired
	private TimetableDAO timetableDAO;
	@Autowired
	private TimetableManager timetableManager;
	@Autowired
	@Qualifier("lessonsSpliter")
	private ISpliter lessonsSpliting;
	@Autowired
	private IPasswordEncoder passwordEncoder;
	
	private static Timetable timetable;
	
    @GetMapping("/")
    public String getCurrentTimetable(Model model) {
    	timetable = timetableManager.getTimetable(null, true);
    	model.addAttribute("timetable", timetable);
    	model.addAttribute("lessons", lessonsSpliting.split(timetable.getLessons()));
    	model.addAttribute("dayOfWeek", new DayOfWeekConverter().getDayOfWeek(timetable.getWeekday()));
    	return "timetable";
    }
    
    @PutMapping("/previous")
    public String getPreviousTimetable(Model model) {
    	timetable = timetableManager.getTimetable(LocalDate.parse(timetable.getDate()).minusDays(1), false);
    	model.addAttribute("timetable", timetable);
    	model.addAttribute("lessons", lessonsSpliting.split(timetable.getLessons()));
    	model.addAttribute("dayOfWeek", new DayOfWeekConverter().getDayOfWeek(timetable.getWeekday()));
    	return "timetable";
    }
    
    @PutMapping("/next")
    public String getNextTimetable(Model model) {
    	timetable = timetableManager.getTimetable(LocalDate.parse(timetable.getDate()).plusDays(1), true);
    	model.addAttribute("timetable", timetable);
    	model.addAttribute("lessons", lessonsSpliting.split(timetable.getLessons()));
    	model.addAttribute("dayOfWeek", new DayOfWeekConverter().getDayOfWeek(timetable.getWeekday()));
    	return "timetable";
    }
    
    @GetMapping("/login")
    public String getLoginForm(Model model) {
    	model.addAttribute("login", new Login());
    	return "login";
    }
    
    @PatchMapping("/login")
    public String processLogin(@ModelAttribute("login") @Valid Login login, BindingResult bindingResult, Model model) {
    	if(bindingResult.hasErrors()) {
    		return "login";
    	}
    	login.setPassword(passwordEncoder.encode(login.getPassword()));
    	if(!loginDAO.isExists(login)) {
    		return "login";
    	}
    	model.addAttribute("timetable", timetable);
    	return "homework";
    }
    
    @PatchMapping("/homework")
    public String addHomework(@ModelAttribute("homework") Homework homework) {
    	timetableDAO.addHomework(homework.getHomework(), timetable.getDate());
    return "redirect:/";    	
    }
    
    @ModelAttribute
    public Homework homework() {
    	return new Homework();
    }
}
