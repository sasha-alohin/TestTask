package com.textparser;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.textparser.entity.WordCount;
import com.textparser.service.TextParserService;

public class TextParserServiceTest {

	private TextParserService textParserService;
	private String path;
	private Integer wordCount;
	private final String data = "Hark. How the bells, sweet silver bells\n" + "\n"
			+ "All seem to say, Throw cares away.\n" + "\n" + "Christmas is here, bringing good cheer\n" + "\n"
			+ "To young and old, meek and the bold\n" + "\n" + "Ding, dong, ding, dong, that is their song,\n" + "\n"
			+ "With joyful ring, all caroling\n" + "\n" + "One seems to hear words of good cheer\n" + "\n"
			+ "From everywhere, filling the air\n" + "\n" + "Oh, how they pound, raising their sound\n" + "\n"
			+ "Over hill and dale, telling their tale";

	@Before
	public void init() throws IOException {
		textParserService = new TextParserService();
		path = "test.txt";
		Files.write(Paths.get(path), data.getBytes());
		wordCount = 10;
	}

	@Test
	public void parseFileTest() {
		List<WordCount> words = textParserService.parseFile(path, wordCount);
		assertNotNull(words);
	}
	
	@After
	public void destroy() throws IOException{
		Files.delete(Paths.get(path));
	}

}
