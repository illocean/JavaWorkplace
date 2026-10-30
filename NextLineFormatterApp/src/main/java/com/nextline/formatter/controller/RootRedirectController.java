package com.nextline.formatter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Maps GET / to the static index.html so the GUI opens at exactly
 * http://localhost:8080/ instead of requiring /index.html.
 */
@RestController
public class RootRedirectController {

	@RequestMapping("/")
	public RedirectView redirectToGui() {
		return new RedirectView("/index.html");
	}

}
