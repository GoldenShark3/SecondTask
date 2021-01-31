package com.epam.jwd.text_handling.service;

import com.epam.jwd.text_handling.handler.TextHandler;
import com.epam.jwd.text_handling.handler.impl.ParagraphHandler;
import com.epam.jwd.text_handling.model.impl.Composite;
import com.epam.jwd.text_handling.reader.TextReader;
import org.junit.BeforeClass;
import org.junit.Test;

public class TextServiceTest {
    private static TextService textService;
    private static Composite parsedText;

    @BeforeClass
    public static void setUpBeforeTest() {
        TextHandler textHandler = ParagraphHandler.INSTANCE;
        TextReader textReader = TextReader.getInstance();

        textService = TextService.getInstance();
        String text = textReader.receiveTextFromFile("text");
        parsedText = textHandler.handleRequest(text);
    }

    @Test
    public void printText_printTextOnConsole_always() {
        textService.printText(parsedText);
    }

    @Test
    public void sortParagraphsByAmountOfSentence_printSortedTextOnConsole_always() {
        parsedText = textService.sortParagraphsByAmountOfSentence(parsedText);
        textService.printText(parsedText);
    }

    @Test
    public void findSentenceWithLongestWord_printSentenceWithLongestWordOnConsole_always() {
        System.out.println(textService.findSentenceWithLongestWord(parsedText));
    }

    @Test
    public void removeSentencesWithLessSize_printTextWithoutSentences_always() {
        parsedText = textService.removeSentencesWithLessSize(parsedText, 20);
        textService.printText(parsedText);
    }

}