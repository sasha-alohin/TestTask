package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser {
	private static final String REGEX = "[a-zA-Z]+";
	private static final String WARNING_MASSAGE = "Can't read data form file";

	public List<String> getAllMatching(List<String> input) {
		List<String> mgroups = new ArrayList<>();
		Pattern pattern = Pattern.compile(REGEX);
		for (String line : input) {
			Matcher matcher = pattern.matcher(line);
			while (matcher.find()) {
				String mgroup = matcher.group(0);
				mgroups.add(mgroup);
			}
		}
		return mgroups;
	}

	public void parseFile(String path, Integer wordCount) {
		List<String> lines = new ArrayList<>();
		try {
			lines = Files.readAllLines(Paths.get(path));
		} catch (IOException e) {
			throw new RuntimeException(WARNING_MASSAGE);
		}
		List<String> wordsList = getAllMatching(lines);
		List<WordCount> wordCountList = new ArrayList<>();
		wordsList.forEach(word->{
			boolean contains = false;
			for (WordCount wordCount1 : wordCountList) {
				if (wordCount1.getName().equals(word)) {
					wordCount1.setCount(wordCount1.getCount() + 1);
					contains = true;
					break;
				} else
					contains = false;
			}
			if (!contains)
				wordCountList.add(new WordCount(word, 1));
		});
		sortCollection(wordCountList);
		wordCountList.forEach(word -> {
			System.out.println(word.getName() + "=" + word.getCount());
		});

	}

	public void sortCollection(List<WordCount> wordCountList) {
		wordCountList.sort((word1, word2) -> {
			if (word1.getCount() < word2.getCount())
				return 1;
			else if (word1.getCount() > word2.getCount())
				return -1;
			else
				return word1.getName().compareTo(word2.getName());
		});
	}

}
