package com.textparser;

import com.textparser.service.TextParserService;

public class Main {
	public static void main(String[] args) {
		TextParserService textParser = new TextParserService();
		textParser.parseFile(args[0],
				Integer.valueOf(args[1]));
	}
}
