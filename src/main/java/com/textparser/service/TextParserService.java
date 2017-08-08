package com.textparser.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.textparser.entity.WordCount;
import com.textparser.util.RegexMatcher;

public class TextParserService {

	private static final String WARNING_MASSAGE = "Can't read data form file";

	public List<WordCount> parseFile(String path, Integer count) {
		List<String> lines = new ArrayList<>();
		try {
			lines = Files.readAllLines(Paths.get(path));
		} catch (IOException e) {
			throw new RuntimeException(WARNING_MASSAGE);
		}
		List<String> wordsList = RegexMatcher.getAllMatching(lines);
		List<WordCount> wordCountList = new ArrayList<>();
		checkWordsForList(wordsList, wordCountList);
		sortCollection(wordCountList);
		
		for(int i = 0; i < wordCountList.size() && i < count; i++) {
			System.out.println(wordCountList.get(i).getName() + "=" + wordCountList.get(i).getCount());
		}
		return wordCountList;
	}

	private void checkWordsForList(List<String> wordsList, List<WordCount> resultList) {
		wordsList.forEach(word -> {
			if (resultList.stream().noneMatch(wordFromList -> wordFromList.getName().equals(word))) {
				resultList.add(new WordCount(word, 1));
			} else {
				resultList.stream().filter(wordFromList -> wordFromList.getName().equals(word))
						.forEach(wordFromList -> {
							wordFromList.setCount(wordFromList.getCount() + 1);
						});
			}

		});
	}

	private void sortCollection(List<WordCount> wordCountList) {
		wordCountList.sort((word1, word2) -> {
			if (word1.getCount() < word2.getCount()) {
				return 1;
			} else if (word1.getCount() > word2.getCount()) {
				return -1;
			} else {
				return word1.getName().compareTo(word2.getName());
			}
		});
	}

}
