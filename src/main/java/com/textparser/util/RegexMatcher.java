package com.textparser.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatcher {
	private static final String REGEX = "[a-zA-Z]+";
	
	public static List<String> getAllMatching(List<String> input) {
		List<String> matchingStrings = new ArrayList<>();
		Pattern pattern = Pattern.compile(REGEX);
		for (String line : input) {
			Matcher matcher = pattern.matcher(line);
			while (matcher.find()) {
				String matchesString = matcher.group(0);
				matchingStrings.add(matchesString);
			}
		}
		return matchingStrings;
	}
}
