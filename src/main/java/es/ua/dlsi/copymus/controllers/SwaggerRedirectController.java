package es.ua.dlsi.grfia.copymus.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SwaggerRedirectController {

	@GetMapping
	protected String redirect() {
		return "redirect:/swagger-ui/index.html";
	}
}
