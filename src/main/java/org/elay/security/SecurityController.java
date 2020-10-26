package org.elay.security;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	
	@GetMapping(path="/notAutorized")
	public String errer() {
		return "notAutorized";
	}
	
	@GetMapping(path="/login")
	public String login() {
		return "login";
	}
	
	@GetMapping(path="/logout")
	public String logout(HttpServletRequest request) throws ServletException {
		request.logout();
		return "redirect:/login";
	}


}
