package com.theexercisetracker.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloController {
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "hello";
	}
}

// TODO:  CRUDRepository vs extend SimpleJPARepository
// TODO:  Add service class for parsing
// TODO:  Render results of single file to page
// TODO:  Design database
// TODO:  Add page showing aggregate information