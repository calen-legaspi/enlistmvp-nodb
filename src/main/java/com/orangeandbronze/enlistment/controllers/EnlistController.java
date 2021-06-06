package com.orangeandbronze.enlistment.controllers;

import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.orangeandbronze.enlistment.*;

@Controller
@RequestMapping("enlist")
@SessionAttributes("student")
class EnlistController {

	private SectionRepository sectionRepo = new SectionRepository();
	private StudentRepository studentRepo = new StudentRepository();

	@ModelAttribute("student")
	Student student(@RequestParam Integer studentNumber) {
		if (studentNumber == null) {
			studentNumber = 1; // default student
		}
		return studentRepo.findBy(studentNumber);
	}

	@GetMapping
	String showSections(Model model, Integer studentNumber) {
		Student student = studentNumber == null ? (Student) model.getAttribute("student")
				: studentRepo.findBy(studentNumber);
		model.addAttribute("student", student);
		var enlistedSections = student.getSections();
		model.addAttribute("enlistedSections", enlistedSections);
		model.addAttribute("availableSections", sectionRepo.findAll().stream()
				.filter(sec -> !enlistedSections.contains(sec)).collect(Collectors.toList()));
		return "enlist";
	}

	@PostMapping()
	String enlist(@ModelAttribute Student student, @RequestParam String sectionId,
			@RequestParam String userAction) {
		Section section = sectionRepo.findBy(sectionId).get();

		switch (userAction) {
		case "enlist":
			student.enlist(section);
			break;
		case "cancel":
			student.cancel(section);
			break;
		}
		studentRepo.save(student);
		sectionRepo.save(section);
		return "redirect:enlist";
	}
	
	@ExceptionHandler(EnlistmentException.class)
	String handleException(RedirectAttributes redirectAttrs, EnlistmentException e) {
		System.out.println("handleException");
		redirectAttrs.addFlashAttribute("enlistmentExceptionMessage", e.getMessage());
		return "redirect:enlist";
	}
	

	void setSectionRepo(SectionRepository sectionRepo) {
		this.sectionRepo = sectionRepo;
	}

	void setStudentRepo(StudentRepository studentRepo) {
		this.studentRepo = studentRepo;
	}

}
