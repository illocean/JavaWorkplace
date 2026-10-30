package com.nextline.formatter.service;

import org.springframework.stereotype.Service;

@Service
public class TextFormatterService {

	public String format(String text) {
		if (text == null || text.isEmpty()) {
			return "";
		}
		int length = text.length();
		int capacity = (int) Math.min((long) length + (length >> 3) + 16, Integer.MAX_VALUE - 8);
		StringBuilder builder = new StringBuilder(capacity);
		// A punctuation run stays pending so "..." receives exactly one break.
		boolean pendingBreak = false;
		for (int i = 0; i < length; i++) {
			char current = text.charAt(i);
			boolean delimiter = isDelimiter(current);
			if (!delimiter && pendingBreak) {
				if (current != '\n' && current != '\r') {
					builder.append('\n');
				}
				pendingBreak = false;
			}
			builder.append(current);
			if (delimiter) {
				pendingBreak = true;
			}
		}
		return builder.toString();
	}

	private static boolean isDelimiter(char c) {
		return c == ',' || c == '.' || c == ':';
	}

}
