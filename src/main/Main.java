package main;

public class Main {
	public static void main(String[] args) {
		TextParser textParser = new TextParser();
		textParser.parseFile(args[0], Integer.valueOf(args[1]));
	}
}
