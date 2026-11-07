package com.nextline.formatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class GuiPageTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void servesGuiAtRoot() throws Exception {
		mockMvc.perform(get("/index.html"))
				.andExpect(status().isOk())
				.andExpect(content().string(org.hamcrest.Matchers.containsString("id=\"input\"")))
				.andExpect(content().string(org.hamcrest.Matchers.containsString("fetch('/api/v1/format'")));
	}

	@Test
	void rootRedirectsToGui() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().is3xxRedirection())
				.andExpect(header().string("Location", "/index.html"));
	}

}
