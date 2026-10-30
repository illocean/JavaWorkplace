package com.nextline.formatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class FormatEndpointIntegrationTest {

	private static final MediaType TEXT_PLAIN_UTF8 = MediaType.parseMediaType("text/plain;charset=UTF-8");

	@Autowired
	private MockMvc mockMvc;

	@Test
	void formatsThroughFullApplicationContext() throws Exception {
		mockMvc.perform(post("/api/v1/format")
						.contentType(TEXT_PLAIN_UTF8)
						.content("Hello, world."))
				.andExpect(status().isOk())
				.andExpect(content().contentType(TEXT_PLAIN_UTF8))
				.andExpect(content().string("Hello,\n world."));
	}

	@Test
	void acceptsEmptyBodyThroughFullApplicationContext() throws Exception {
		mockMvc.perform(post("/api/v1/format")
						.contentType(MediaType.TEXT_PLAIN))
				.andExpect(status().isOk())
				.andExpect(content().contentType(TEXT_PLAIN_UTF8))
				.andExpect(content().string(""));
	}

}
