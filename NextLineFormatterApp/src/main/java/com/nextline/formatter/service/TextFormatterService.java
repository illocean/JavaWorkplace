package com.nextline.formatter.service;

import org.springframework.stereotype.Service;

@Service
public class TextFormatterService {

	public String format(String text) {
		if (text == null || text.isEmpty()) {
			return "";
		}
		int length = text.length();
		StringBuilder out = new StringBuilder(length + (length >> 3) + 16);
		// A punctuation run stays pending so "..." receives exactly one break.
		boolean pendingBreak = false;
		// After an *inserted* LF only, skip the leading whitespace run that
		// would otherwise indent the wrapped line (e.g. "hi! phillip" ->
		// "hi!\nphillip"). Existing line breaks from the input are untouched,
		// so their indentation and any subsequent whitespace are preserved.
		boolean skipLeadingWs = false;
		for (int i = 0; i < length; i++) {
			char c = text.charAt(i);
			boolean delimiter = isDelimiter(c);
			if (!delimiter && pendingBreak) {
				if (c != '\n' && c != '\r') {
					out.append('\n');
					skipLeadingWs = true;
				}
				pendingBreak = false;
			}
			if (c == '\n' || c == '\r') {
				out.append(c);
				skipLeadingWs = false;
			} else if (skipLeadingWs && (c == ' ' || c == '\t')) {
				// swallow only the leading whitespace immediately after the
				// inserted line break; stop once real text begins
				continue;
			} else {
				if (skipLeadingWs) {
					skipLeadingWs = false;
				}
				out.append(c);
			}
			if (delimiter) {
				pendingBreak = true;
			}
		}
		return out.toString();
	}

	private static boolean isDelimiter(char c) {
		// Comma, period, colon, exclamation, question, semicolon,
		// apostrophe, and double quote each terminate a line before the next text.
		return c == ',' || c == '.' || c == ':'
			|| c == '!' || c == '?' || c == ';'
			|| c == '\'' || c == '"';
	}

}
