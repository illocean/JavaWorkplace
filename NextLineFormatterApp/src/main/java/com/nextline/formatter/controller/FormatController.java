package com.nextline.formatter.controller;

import com.nextline.formatter.service.TextFormatterService;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class FormatController {

	private final TextFormatterService textFormatterService;

	public FormatController(TextFormatterService textFormatterService) {
		this.textFormatterService = textFormatterService;
	}

	@PostMapping(path = "/format", consumes = MediaType.TEXT_PLAIN_VALUE, produces = "text/plain;charset=UTF-8")
	public String format(@RequestBody(required = false) String text) {
		return textFormatterService.format(text);
	}

}
