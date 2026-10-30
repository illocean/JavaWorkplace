package com.nextline.formatter.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.nextline.formatter.service.TextFormatterService;

@WebMvcTest(FormatController.class)
@Import(TextFormatterService.class)
class FormatControllerTest {

	private static final MediaType TEXT_PLAIN_UTF8 = MediaType.parseMediaType("text/plain;charset=UTF-8");

	@Autowired
	private MockMvc mockMvc;

	@Test
	void formatsPlainText() throws Exception {
		mockMvc.perform(post("/api/v1/format")
						.contentType(TEXT_PLAIN_UTF8)
						.content("Hello, world. Welcome: this is a formatter."))
				.andExpect(status().isOk())
				.andExpect(content().contentType(TEXT_PLAIN_UTF8))
				.andExpect(content().string("Hello,\n world.\n Welcome:\n this is a formatter."));
	}

	@Test
	void acceptsEmptyBody() throws Exception {
		mockMvc.perform(post("/api/v1/format")
						.contentType(MediaType.TEXT_PLAIN)
						.content(""))
				.andExpect(status().isOk())
				.andExpect(content().contentType(TEXT_PLAIN_UTF8))
				.andExpect(content().string(""));
	}

	@Test
	void roundTripsUnicode() throws Exception {
		mockMvc.perform(post("/api/v1/format")
						.contentType(TEXT_PLAIN_UTF8)
						.content("héllo, wörld 🌍"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(TEXT_PLAIN_UTF8))
				.andExpect(content().string("héllo,\n wörld 🌍"));
	}

	@Test
	void roundTripsUnicodeWithoutRequestCharset() throws Exception {
		mockMvc.perform(post("/api/v1/format")
						.contentType(MediaType.TEXT_PLAIN)
						.content("héllo, wörld 🌍"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(TEXT_PLAIN_UTF8))
				.andExpect(content().string("héllo,\n wörld 🌍"));
	}

	@Test
	void rejectsNonPlainTextContentType() throws Exception {
		mockMvc.perform(post("/api/v1/format")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{}"))
				.andExpect(status().isUnsupportedMediaType());
	}

	@Test
	void rejectsGetRequests() throws Exception {
		mockMvc.perform(get("/api/v1/format"))
				.andExpect(status().isMethodNotAllowed());
	}

	@Test
	void doesNotAppendNewlineAfterTrailingPunctuation() throws Exception {
		mockMvc.perform(post("/api/v1/format")
						.contentType(MediaType.TEXT_PLAIN)
						.content("done."))
				.andExpect(status().isOk())
				.andExpect(content().string("done."));
	}

}
