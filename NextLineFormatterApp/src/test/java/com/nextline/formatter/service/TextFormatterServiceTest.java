package com.nextline.formatter.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.Test;

class TextFormatterServiceTest {

	private final TextFormatterService service = new TextFormatterService();

	@Test
	void insertsBreakAfterComma() {
		assertEquals("a,\nb", service.format("a,b"));
	}

	@Test
	void insertsBreakAfterPeriod() {
		assertEquals("a.\nb", service.format("a.b"));
	}

	@Test
	void insertsBreakAfterColon() {
		assertEquals("a:\nb", service.format("a:b"));
	}

	@Test
	void insertsOneBreakAfterConsecutivePunctuationGroup() {
		assertEquals("a,.\nb", service.format("a,.b"));
		assertEquals("Wait...\n what?!", service.format("Wait... what?!"));
		assertEquals(",.:", service.format(",.:"));
	}

	@Test
	void doesNotDuplicateExistingLineBreaks() {
		assertEquals("a,\nb", service.format("a,\nb"));
		assertEquals("a.\r\nb", service.format("a.\r\nb"));
		assertEquals("a:\r\nb", service.format("a:\r\nb"));
		assertEquals("a,.\r\nb", service.format("a,.\r\nb"));
		assertEquals("a,\rb", service.format("a,\rb"));
	}

	@Test
	void breaksAfterLeadingPunctuation() {
		assertEquals(",\na", service.format(",a"));
	}

	@Test
	void doesNotAppendTrailingNewline() {
		assertEquals("a,", service.format("a,"));
		assertEquals("done.", service.format("done."));
	}

	@Test
	void breaksAfterPunctuationBeforeSpace() {
		assertEquals("a,\n ", service.format("a, "));
	}

	@Test
	void handlesEmptyNullAndPlainText() {
		assertEquals("", service.format(""));
		assertEquals("", service.format(null));
		assertEquals("no punctuation here", service.format("no punctuation here"));
		assertEquals("   \t ", service.format("   \t "));
	}

	@Test
	void preservesWhitespace() {
		assertEquals("\t a,\n\t b", service.format("\t a,\t b"));
	}

	@Test
	void preservesUnicodeAndSurrogatePairs() {
		assertEquals("héllo,\n wörld 🌍.\n naïve:\n café 🚀",
				service.format("héllo, wörld 🌍. naïve: café 🚀"));
		assertEquals("👍🏽,\nok", service.format("👍🏽,ok"));
	}

	@Test
	void preservesEveryOriginalCharacter() {
		String input = "abc,def.ghi:jkl";
		String formatted = service.format(input);

		assertEquals(input.length() + 3, formatted.length());
		assertEquals(input, formatted.replace("\n", ""));
	}

	@Test
	void formatsSpecExample() {
		assertEquals("Hello,\n world.\n Welcome:\n this is a formatter.",
				service.format("Hello, world. Welcome: this is a formatter."));
	}

	@Test
	void formatsLargeInput() {
		String input = "word, ".repeat(200_000);
		String expected = "word,\n ".repeat(200_000);

		assertEquals(expected, service.format(input));
	}

	@Test
	void isThreadSafeUnderConcurrentUse() throws Exception {
		String[] inputs = {
				"Hello, world. Welcome: this is a formatter.",
				"a,.b",
				"a,\nb",
				"👍🏽,ok",
				"no punctuation"
		};
		String[] expected = {
				"Hello,\n world.\n Welcome:\n this is a formatter.",
				"a,.\nb",
				"a,\nb",
				"👍🏽,\nok",
				"no punctuation"
		};
		ExecutorService executor = Executors.newFixedThreadPool(16);
		try {
			List<Future<String>> futures = new ArrayList<>(1_000);
			for (int i = 0; i < 1_000; i++) {
				int index = i % inputs.length;
				futures.add(executor.submit(() -> service.format(inputs[index])));
			}
			for (int i = 0; i < futures.size(); i++) {
				assertEquals(expected[i % expected.length], futures.get(i).get());
			}
		}
		finally {
			executor.shutdown();
		}
	}

}
