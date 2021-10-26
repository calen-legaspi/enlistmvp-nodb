package com.orangeandbronze.enlistment.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.orangeandbronze.enlistment.*;

@Controller
@RequestMapping("sections")
@SessionAttributes("admin")
class SectionsController {

	private SubjectRepository subjectRepo = new SubjectRepository();
	private AdminRepository adminRepo = new AdminRepository();
	private final RoomRepository roomRepo = new RoomRepository();
	private final SectionRepository sectionRepo = new SectionRepository();

	@ModelAttribute("admin")
	Admin admin(@RequestParam Integer id) {
		if (id == null) {
			id = 1; // default student
		}
		return adminRepo.findBy(id);
	}

	@GetMapping
	String showPage(Model model, Integer id) {
		Admin admin = id == null ? (Admin) model.getAttribute("admin") : adminRepo.findBy(id);
		model.addAttribute("admin", admin);
		model.addAttribute("subjects", subjectRepo.findAll());
		model.addAttribute("rooms", roomRepo.findAll());
		model.addAttribute("sections", sectionRepo.findAll());
		return "sections";
	}

	@PostMapping
	String createSection(@RequestParam String sectionId, @RequestParam String subjectId, @RequestParam String days,
			@RequestParam String period, @RequestParam String roomName, RedirectAttributes redirectAttrs) {
		sectionRepo.save(Section.of(sectionId, subjectId, days, period, roomName));
		redirectAttrs.addFlashAttribute("sectionSuccessMessage", "Successfully created new section " + sectionId);
		return "redirect:sections";
	}
	
	@ExceptionHandler(EnlistmentException.class)
	String handleException(RedirectAttributes redirectAttrs, EnlistmentException e) {
		redirectAttrs.addFlashAttribute("sectionExceptionMessage", e.getMessage());
		return "redirect:sections";
	}

	void setSubjectRepo(SubjectRepository subjectRepo) {
		this.subjectRepo = subjectRepo;
	}

	void setAdminRepo(AdminRepository adminRepo) {
		this.adminRepo = adminRepo;
	}

}
